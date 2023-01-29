package com.arc.app.core.service.impl;

import com.arc.app.core.domain.Administrative;
import com.arc.app.core.domain.Cooperate;
import com.arc.app.core.domain.Parameter;
import com.arc.app.core.dto.AdministrativeDto;
import com.arc.app.core.dto.CooperateDto;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.repository.AdministrativeRepository;
import com.arc.app.core.repository.CooperateRepository;
import com.arc.app.core.service.CooperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CooperateServiceImpl implements CooperateService {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CooperateRepository repository;

    @Autowired
    private AdministrativeRepository administrativeRepository;

    @Override
    public List<CooperateDto> getAll(Integer type) {
        if(type != null) {
            List<CooperateDto> result = repository.getAll(type);
            return result;
        }
        return null;
    }

    @Override
    public CooperateDto find(Long id) {
        if(id != null) {
            Cooperate entity = repository.find(id);
            if(entity != null) {
                return new CooperateDto(entity);
            }
        }
        return null;
    }

    @Override
    public CooperateDto save(CooperateDto dto) {
        if(dto == null) {
            return null;
        }
        Cooperate cooperate = null;
        if(dto.getId() != null) {
            cooperate = repository.find(dto.getId());
        }
        if(cooperate == null) {
            cooperate = new Cooperate();
        }
        if(dto.getStatus() != null) {
            cooperate.setStatus(dto.getStatus());
        }
        if(dto.getType() != null) {
            cooperate.setType(dto.getType());
        }
        if(dto.getAddressDetail() != null) {
            cooperate.setAddressDetail(dto.getAddressDetail());
        }
        if(dto.getAddress() != null && dto.getAddress().getId() != null) {
            Administrative address = administrativeRepository.find(dto.getAddress().getId());
            if(address != null) {
                cooperate.setAddress(address);
            }
        }
        cooperate = repository.save(cooperate);
        return new CooperateDto(cooperate);
    }

    @Override
    public Boolean isExist(String code) {
        if(code != null) {
            Long count = repository.count(code);
            if(count > 0) {
                return true;
            }
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if(id != null) {
            Cooperate cooperate = repository.find(id);
            if(cooperate != null) {
                repository.delete(cooperate);
                return true;
            }
        }
        return false;
    }

    @Override
    public void makeDelete(Long id) {
        if(id != null) {
            Cooperate cooperate = repository.find(id);
            if(cooperate != null) {
                cooperate.setStatus(2);
            }
        }
    }

    @Override
    public Page<CooperateDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.CooperateDto(entity) From Cooperate entity ";
            String sqlCount = "Select count(entity.id) From Cooperate entity ";
            String orderBy = " Order By entity.code ";
            String whereClause = " Where (1=1) ";
            if(dto.getType() != null) {
                whereClause += " AND entity.type = :type";
            }
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, CooperateDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getType() != null) {
                q.setParameter("type",  dto.getType());
                qCount.setParameter("type", dto.getType());
            }
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<CooperateDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
