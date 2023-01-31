package com.arc.app.core.service.impl;

import com.arc.app.core.domain.ImagePath;
import com.arc.app.core.domain.Person;
import com.arc.app.core.domain.Product;
import com.arc.app.core.domain.ProductCategory;
import com.arc.app.core.dto.PersonDto;
import com.arc.app.core.dto.ProductCategoryDto;
import com.arc.app.core.dto.ProductDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.other.Constants;
import com.arc.app.core.repository.ImagePathRepository;
import com.arc.app.core.repository.ProductCategoryRepository;
import com.arc.app.core.repository.ProductRepository;
import com.arc.app.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private EntityManager manager;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    private ImagePathRepository imagePathRepository;


    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public List<ProductDto> getAll() {
        return repository.getAll();
    }

    @Override
    public ProductDto find(Long id) {
        if (id != null) {
            Product product = repository.find(id);
            if (product != null) {
                return new ProductDto(product, true);
            }
        }
        return null;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        Product product = null;
        if (dto.getId() != null) {
            product = repository.find(dto.getId());
        }
        if (product == null) {
            product = new Product();
        }
        if (dto.getCode() != null) {
            product.setCode(dto.getCode());
        }
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getQuantity() != null) {
            product.setQuantity(dto.getQuantity());
        }
        if (dto.getUnit() != null) {
            product.setUnit(dto.getUnit());
        }
        if (dto.getStatus() != null) {
            product.setStatus(dto.getStatus());
        }
        if (dto.getProductCategory() != null && dto.getProductCategory().getId() != null) {
            ProductCategory productCategory = productCategoryRepository.find(dto.getProductCategory().getId());
            if (productCategory != null) {
                product.setProductCategory(productCategory);
            }
        }
        product = repository.save(product);
        return new ProductDto(product, true);
    }

    @Override
    public Boolean isExist(String code) {
        if (code != null) {
            Long number = repository.count(code);
            if (number == 0) {
                return true;
            }
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null) {
            Product product = repository.find(id);
            if (product != null) {
                repository.delete(product);
            }
        }
        return null;
    }

    @Override
    public void makeDelete(Long id) {
        if (id != null) {
            Product product = repository.find(id);
            if (product != null) {
                product.setStatus(3);
                repository.save(product);
            }
        }
    }

    @Override
    public ProductDto saveImageProduct(Long id, List<MultipartFile> listFile) {
        if (id != null && listFile != null && listFile.size() > 0) {
            try {
                HashSet<ImagePath> imageCards = new HashSet<ImagePath>();
                for (MultipartFile file : listFile) {
                    String extension = file.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
                    UUID randomCode = UUID.randomUUID();
                    String fileName = randomCode + "." + extension;
                    try {
                        File fileToBeSaved = new File(Constants.LOCAL_PATH, fileName);
                        file.transferTo(fileToBeSaved);
                    } catch (Exception e) {
                        logger.error("Error: ", e.getMessage(), e);
                    }
                    ImagePath imageCard = new ImagePath();
                    imageCard.setExtension(extension);
                    imageCard.setType(file.getContentType());
                    imageCard.setSize(file.getSize());
                    imageCard.setName(fileName);
                    imageCards.add(imageCard);
                }
                Product product = repository.find(id);
                if (product != null) {
                    if (imageCards != null && imageCards.size() > 0) {
                        for (ImagePath item : imageCards) {
                            imagePathRepository.save(item);
                        }
                    }
                    if (product != null) {
                        product.getImageProducts().clear();
                        product.getImageProducts().addAll(imageCards);
                    } else {
                        product.setImageProducts(imageCards);
                    }
                    product = repository.save(product);
                    return new ProductDto(product, false);
                }
            } catch (Exception e) {
                logger.error("Error: ", e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public Page<ProductDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.ProductDto(entity, false) From ProductDto entity ";
            String sqlCount = "Select count(entity.id) From ProductCategory entity ";
            String orderBy = " Order By entity.code ";
            String whereClause = " Where (1=1) ";
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch OR entity.name Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, ProductDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ProductDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
