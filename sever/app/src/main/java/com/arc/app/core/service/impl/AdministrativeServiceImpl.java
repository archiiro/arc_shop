package com.arc.app.core.service.impl;

import com.arc.app.core.domain.Administrative;
import com.arc.app.core.dto.AdministrativeDto;
import com.arc.app.core.dto.AdministrativeImport;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.other.Constants;
import com.arc.app.core.repository.AdministrativeRepository;
import com.arc.app.core.service.AdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class AdministrativeServiceImpl implements AdministrativeService {
    @Autowired
    private AdministrativeRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<AdministrativeDto> getAllProvince() {
        List<AdministrativeDto> result = repository.getAllProvince();
        return result;
    }

    @Override
    public List<AdministrativeDto> getChildrenByParent(Long parentId) {
        if (parentId != null && this.find(parentId) != null) {
            AdministrativeDto dto = this.find(parentId);
            if(dto.getChildren() != null && dto.getChildren().size() > 0) {
                List<AdministrativeDto> result = (List<AdministrativeDto>) dto.getChildren();
                return result;
            }
        }
        return null;
    }

    @Override
    public AdministrativeDto find(Long id) {
        if(id != null) {
            Administrative result = repository.find(id);
            if(result != null) {
                return new AdministrativeDto(result, true);
            }
        }
        return null;
    }

    @Override
    public AdministrativeDto save(AdministrativeDto dto) {
        if(dto == null) {
            return null;
        }
        Administrative administrative = null;
        if(dto.getId() != null) {
            administrative = repository.find(dto.getId());
        }
        if(administrative == null) {
            administrative = new Administrative();
        }
        if(dto.getCode() != null) {
            administrative.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            administrative.setName(dto.getName());
        }
        if (dto.getParent() != null) {
            Administrative parent = null;
            if (dto.getParent().getId() != null) {
                parent = repository.find(dto.getParent().getId());
            }
            if (parent != null) {
                administrative.setParent(parent);
                if (parent.getLevel() != null && parent.getLevel() > 0) {
                    administrative.setLevel(parent.getLevel() + 1);
                }
            }
        } else {
            administrative.setLevel(1); // Tỉnh
            administrative.setParent(null);
        }
        if(dto.getChildren() != null && dto.getChildren().size() > 0) {
            Iterator<AdministrativeDto> iterator = dto.getChildren().iterator();
            HashSet<Administrative> children = new HashSet<Administrative>();
            while (iterator.hasNext()) {
                AdministrativeDto childrenDto = iterator.next();
                Administrative childrenAdministrative = null;
                if(childrenDto.getId() != null) {
                    childrenAdministrative = repository.find(childrenDto.getId());
                }
                if(childrenAdministrative == null) {
                    childrenAdministrative = new Administrative();
                }
                if(childrenDto.getCode() != null) {
                    childrenAdministrative.setCode(childrenDto.getCode());
                }
                if(childrenDto.getName() != null) {
                    childrenAdministrative.setName(childrenDto.getName());
                }
                if(administrative != null) {
                    childrenAdministrative.setParent(administrative);
                }
                if(administrative.getLevel() != null && administrative.getLevel() > 0) {
                    childrenAdministrative.setLevel(administrative.getLevel() + 1);
                }
                children.add(childrenAdministrative);
            }
            if(administrative.getChildren() != null) {
                administrative.getChildren().clear();
                administrative.getChildren().addAll(children);
            } else  {
                administrative.setChildren(children);
            }
        }
        administrative = repository.save(administrative);
        return new AdministrativeDto(administrative, true);
    }

    @Override
    public Boolean isExist(String code) {
        if(code !=  null) {
            Long number = repository.count(code);
            if(number == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if(id != null) {
            Administrative administrative = repository.find(id);
            if(administrative != null) {
                if(administrative.getChildren() == null || administrative.getChildren().size() == 0) {
                    repository.delete(administrative);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Page<AdministrativeDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if (pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            String sql = " Select new com.arc.app.core.dto.AdministrativeDto(entity, true) From AdministrativeUnit entity ";
            String sqlCount = "Select count(entity.id) From AdministrativeUnit entity ";
            String whereClause  = "  Where (1=1) ";
            String oderBy = " Order by entity.code ASC";
            if(dto.getParentId() !=  null) {
                whereClause += " AND entity.parent.id = :parenId ";
            } else {
                whereClause  += " AND (entity.parent is null OR entity.level = 1) ";
            }
            if (dto.getTextSearch() != null && StringUtils.hasText(dto.getTextSearch())) {
                whereClause += " AND (entity.code Like :textSearch OR entity.name Like :textSearch)";
            }
            sql += whereClause + oderBy;
            Query query = manager.createQuery(sql, AdministrativeDto.class);
            Query qCount = manager.createQuery(sqlCount);
            if(dto.getParentId() != null) {
                query.setParameter("parenId", dto.getParentId());
                qCount.setParameter("parenId", dto.getParentId());
            }
            if (dto.getTextSearch() != null && StringUtils.hasText(dto.getTextSearch())) {
                query.setParameter("textSearch", dto.getTextSearch());
                qCount.setParameter("textSearch", dto.getTextSearch());
            }
            query.setFirstResult(pageIndex * pageSize);
            query.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            List<AdministrativeDto> result = query.getResultList();
            Page<AdministrativeDto> page = new PageImpl<>(result, pageable, number);
            return page;
        }
        return null;
    }

    @Override
    public List<Long> getListChildrenId(Long parentId) {
        if(parentId != null) {
            String sqlSelect = "Select entity.id From Administrative entity " +
                    " WHERE entity.parent.id is not null " +
                    " AND entity.parent.parent.id is not null AND (entity.parent.id in = :parentId " +
                    " OR entity.parent.parent.id = :parentId)" +
                    " Oder by entity.level DESC";
            Query query = manager.createQuery(sqlSelect, Long.class);
            query.setParameter("parentId", parentId);
            List<Long> result = query.getResultList();
            result.add(parentId);
        }
        return null;
    }

    @Override
    public Boolean deleteAll(Long id) {
        return null;
    }

    @Override
    public void importExcel(List<AdministrativeImport> listDto) {
        if(listDto != null && listDto.size() > 0) {
            for(AdministrativeImport dto : listDto) {
                Administrative province = null;
                Administrative district = null;
                Administrative commune = null;
                // Province
                if(dto.getCodeProvince() != null) {
                    List<Administrative> listUnit = repository.findCode(dto.getCodeProvince());
                    if(listUnit != null && listUnit.size() > 0) {
                        province = listUnit.get(0);
                    }
                    if(province == null) {
                        province = new Administrative();
                        province.setCode(dto.getCodeProvince());
                        if(dto.getNameProvince() != null) {
                            province.setName(dto.getNameProvince());
                        }
                        province.setLevel(Constants.PROVINCE_LEVEL);
                        province = repository.save(province);
                    }
                }
                // District
                if(province != null) {
                    if(dto.getCodeDistrict() != null) {
                        List<Administrative> listUnit = repository.findCode(dto.getCodeDistrict());
                        if(listUnit != null && listUnit.size() > 0) {
                            district = listUnit.get(0);
                        }
                        if(district == null) {
                            district = new Administrative();
                            district.setParent(province);
                            district.setCode(dto.getCodeDistrict());
                            if(dto.getNameDistrict() != null) {
                                district.setName(dto.getNameDistrict());
                            }
                        }
                        district.setLevel(Constants.DISTRICT_LEVEL);
                        district = repository.save(district);
                    }
                }
                // Commune
                if(district != null) {
                    if(dto.getCodeCommune() != null) {
                        List<Administrative> listUnit = repository.findCode(dto.getCodeCommune());
                        if(listUnit != null && listUnit.size() > 0) {
                            commune = listUnit.get(0);
                        }
                        if(commune == null) {
                            commune = new Administrative();
                            commune.setParent(district);
                            commune.setCode(dto.getCodeCommune());
                            if(dto.getNameCommune() != null) {
                                commune.setName(dto.getNameCommune());
                            }
                        }
                        commune.setLevel(Constants.COMMUNE_LEVEL);
                        commune = repository.save(commune);
                    }
                }
            }
        }
        return;
    }
}
