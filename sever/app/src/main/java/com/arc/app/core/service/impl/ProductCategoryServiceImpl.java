package com.arc.app.core.service.impl;

import com.arc.app.core.domain.ProductCategory;
import com.arc.app.core.dto.ProductCategoryDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.repository.ProductCategoryRepository;
import com.arc.app.core.service.ProductCategoryService;
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
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public List<ProductCategoryDto> getAll() {
        return repository.getAll();
    }

    @Override
    public ProductCategoryDto find(Long id) {
        if(id != null) {
            ProductCategory productCategory = repository.find(id);
            if(productCategory != null) {
                return new ProductCategoryDto(productCategory, true);
            }
        }
        return null;
    }

    @Override
    public ProductCategoryDto save(ProductCategoryDto dto) {
        if(dto == null) {
            return null;
        }
        ProductCategory productCategory = null;
        if(dto.getId() != null) {
            productCategory = repository.find(dto.getId());
        }
        if(productCategory == null) {
            productCategory = new ProductCategory();
        }
        if(dto.getCode() != null) {
            productCategory.setCode(dto.getCode());
        }
        if(dto.getName() != null) {
            productCategory.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            productCategory.setDescription(dto.getDescription());
        }
        if(dto.getStatus() != null) {
            productCategory.setStatus(dto.getStatus());
        }
        productCategory = repository.save(productCategory);
        return new ProductCategoryDto(productCategory, false);
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
            ProductCategory productCategory = repository.find(id);
            if(productCategory != null) {
                repository.delete(productCategory);
                return true;
            }
        }
        return false;
    }

    @Override
    public void makeDelete(Long id) {
        if(id != null) {
            ProductCategory productCategory = repository.find(id);
            if(productCategory != null) {
                productCategory.setStatus(2);
                repository.save(productCategory);
            }
        }
        return;
    }

    @Override
    public Page<ProductCategoryDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.ProductCategoryDto(entity, false) From ProductCategory entity ";
            String sqlCount = "Select count(entity.id) From ProductCategory entity ";
            String orderBy = " Order By entity.code ";
            String whereClause = " Where (1=1) ";
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, ProductCategoryDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ProductCategoryDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
