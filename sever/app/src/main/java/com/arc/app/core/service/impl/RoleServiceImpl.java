package com.arc.app.core.service.impl;

import com.arc.app.core.domain.Role;
import com.arc.app.core.dto.RoleDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.repository.RoleRepository;
import com.arc.app.core.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<RoleDto> getAll() {
        return repository.getAll();
    }

    @Override
    public RoleDto find(Long id) {
        if(id != null) {
            Role entity = repository.find(id);
            if(entity != null) {
                return new RoleDto(entity);
            }
        }
        return null;
    }

    @Override
    public RoleDto save(RoleDto dto) {
        if(dto == null) {
            return null;
        }
        Role role = null;
        if(dto.getId() != null) {
            role =  repository.find(dto.getId());
        }
        if(role == null) {
            role = new Role();
        }
        if(dto.getCode() != null) {
            role.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            role.setName(dto.getName());
        }
        role = repository.save(role);
        return new RoleDto(role);
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
            Role role = repository.find(id);
            if(role != null) {
                repository.delete(role);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<RoleDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.RoleDto(entity) From Role entity ";
            String sqlCount = "Select count(entity.id) From Role entity ";
            String orderBy = " Order By entity.code ";
            String whereClause = " Where (1=1) ";
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, RoleDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<RoleDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
