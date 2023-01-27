package com.arc.app.core.service.impl;

import com.arc.app.core.domain.Role;
import com.arc.app.core.domain.User;
import com.arc.app.core.dto.RoleDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.dto.UserDto;
import com.arc.app.core.repository.RoleRepository;
import com.arc.app.core.repository.UserRepository;
import com.arc.app.core.service.UserService;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<UserDto> getAll() {
        return repository.getAll();
    }

    @Override
    public UserDto find(Long id) {
        if(id != null) {
            User entity = repository.find(id);
            if(entity != null) {
                return new UserDto(entity);
            }
        }
        return null;
    }

    @Override
    public UserDto save(UserDto dto) {
        if(dto == null) {
            return null;
        }
        User user = null;
        if(dto.getId() != null) {
            user =  repository.find(dto.getId());
        }
        if(user == null) {
            user = new User();
        }
        if(dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if(dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if(dto.getRoles() != null && dto.getRoles().size() > 0) {
            Iterator<RoleDto> iterator = dto.getRoles().iterator();
            HashSet<Role> roles = new HashSet<Role>();
            while ((iterator.hasNext())) {
                RoleDto roleDto = iterator.next();
                Role role = null;
                if(roleDto.getId() != null) {
                    role = roleRepository.find(roleDto.getId());
                    if(role != null) {
                        roles.add(role);
                    }
                }
            }
            if(user.getRoles() != null && user.getRoles().size() > 0) {
                user.getRoles().clear();
                user.getRoles().addAll(roles);
            } else {
                user.setRoles(roles);
            }
        }
        user = repository.save(user);
        return new UserDto(user);
    }

    @Override
    public Boolean isExist(String username) {
        if(username != null) {
            Long count = repository.count(username);
            if(count > 0) {
                return true;
            }
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if(id != null) {
            User user = repository.find(id);
            if(user != null) {
                repository.delete(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDto login(String username, String password) {
        if(username != null && password != null) {
            User user = repository.login(username, password);
            if(user != null) {
                return new UserDto(user);
            }
        }
        return null;
    }

    @Override
    public Page<UserDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.UserDto(entity) From User entity ";
            String sqlCount = "Select count(entity.id) From User entity ";
            String whereClause = " Where (1=1) ";
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.username Like :textSearch ";
            }
            sqlSelect += whereClause;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, UserDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<UserDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
