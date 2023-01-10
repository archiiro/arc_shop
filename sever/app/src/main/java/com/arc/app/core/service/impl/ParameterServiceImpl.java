package com.arc.app.core.service.impl;

import com.arc.app.core.domain.Parameter;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.repository.ParameterRepository;
import com.arc.app.core.service.ParameterService;
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
public class ParameterServiceImpl implements ParameterService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private ParameterRepository repository;

    @Override
    public List<ParameterDto> getAll(Integer type) {
        if(type != null) {
            List<ParameterDto> result = repository.getAll(type);
            return result;
        }
        return null;
    }

    @Override
    public ParameterDto find(Long id) {
        if(id != null) {
            Parameter parameter = repository.find(id);
            if(parameter != null) {
                return new ParameterDto(parameter);
            }
        }
        return null;
    }

    @Override
    public ParameterDto save(ParameterDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Parameter parameter = null;
        if(id != null) {
            parameter = repository.find(id);
        }
        if(dto.getId() != null) {
            parameter =  repository.find(dto.getId());
        }
        if(parameter == null) {
            parameter = new Parameter();
        }
        if(dto.getCode() != null) {
            parameter.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            parameter.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            parameter.setDescription(dto.getDescription());
        }
        if(dto.getType() != null) {
            parameter.setType(dto.getType());
        }
        parameter = repository.save(parameter);
        return new ParameterDto(parameter);
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
            Parameter parameter = repository.find(id);
            if(parameter != null) {
                repository.delete(parameter);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<ParameterDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.ParameterDto(entity) From Parameter entity ";
            String sqlCount = "Select count(entity.id) From Ethnics entity ";
            String orderBy = " Order By entity.code ";
            String whereClause = " Where (1=1) ";
            if(dto.getTypeParameter() != null) {
                whereClause += " AND entity.type = :typeParameter";
            }
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, ParameterDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getTypeParameter() != null) {
                q.setParameter("typeParameter",  dto.getTypeParameter());
                qCount.setParameter("typeParameter", dto.getTypeParameter());
            }
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ParameterDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
