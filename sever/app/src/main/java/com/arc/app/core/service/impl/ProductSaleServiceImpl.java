package com.arc.app.core.service.impl;

import com.arc.app.core.domain.ImagePath;
import com.arc.app.core.domain.Product;
import com.arc.app.core.domain.ProductSale;
import com.arc.app.core.dto.ProductDto;
import com.arc.app.core.dto.ProductSaleDto;
import com.arc.app.core.dto.SearchDto;
import com.arc.app.core.other.Constants;
import com.arc.app.core.repository.ImagePathRepository;
import com.arc.app.core.repository.ProductSaleRepository;
import com.arc.app.core.service.ProductSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ProductSaleServiceImpl implements ProductSaleService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private ProductSaleRepository repository;
    @Autowired
    private ImagePathRepository imagePathRepository;

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<ProductSaleDto> getAll() {
        return repository.getAll();
    }

    @Override
    public ProductSaleDto find(Long id) {
        if(id != null) {
            ProductSale item = repository.find(id);
            if(item != null) {
                return new ProductSaleDto(item, true);
            }
        }
        return null;
    }

    @Override
    public ProductSaleDto save(ProductSaleDto dto) {
        if(dto == null) {
            return null;
        }
        ProductSale productSale = null;
        if(dto.getId() != null) {
            productSale = repository.find(dto.getId());
        }
        if(productSale == null) {
            productSale = new ProductSale();
        }
        if(dto.getCode() != null) {
            productSale.setCode(dto.getCode());
        }
        if(dto.getSale() != null) {
            productSale.setSale(dto.getSale());
        }
        if(dto.getStartDate() != null) {
            productSale.setStartDate(dto.getStartDate());
        }
        if(dto.getEndDate() != null) {
            productSale.setEndDate(dto.getEndDate());
        }
        productSale = repository.save(productSale);
        return new ProductSaleDto(productSale, true);
    }

    @Override
    public Boolean isExist(String code) {
        if(code != null) {
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
            ProductSale item = repository.find(id);
            if(item != null) {
                repository.delete(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public ProductSaleDto saveImage(Long id, List<MultipartFile> listFile) {
        if (id != null && listFile != null && listFile.size() > 0) {
            try {
                HashSet<ImagePath> imageCards = new HashSet<ImagePath>();
                ProductSale productSale = repository.find(id);
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
                    if(productSale != null) {
                        imageCard.setProductSale(productSale);
                    }
                    imageCards.add(imageCard);
                }
                if (productSale != null) {
                    if (imageCards != null && imageCards.size() > 0) {
                        for (ImagePath item : imageCards) {
                            imagePathRepository.save(item);
                        }
                    }
                    if (productSale != null) {
                        productSale.getImageProductSales().clear();
                        productSale.getImageProductSales().addAll(imageCards);
                    } else {
                        productSale.setImageProductSales(imageCards);
                    }
                    productSale = repository.save(productSale);
                    return new ProductSaleDto(productSale, false);
                }
            } catch (Exception e) {
                logger.error("Error: ", e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public Page<ProductSaleDto> search(SearchDto dto) {
        if(dto != null && dto.getPageIndex() != null && dto.getPageSize() != null) {
            int pageIndex = dto.getPageIndex();
            int pageSize = dto.getPageSize();
            if(pageIndex > 0) {
                pageIndex = pageIndex - 1;
            } else {
                pageIndex = 0;
            }
            String sqlSelect = "Select new com.arc.app.dto.ProductSaleDto(entity, false) From ProductSale entity ";
            String sqlCount = "Select count(entity.id) From ProductSale entity ";
            String orderBy = " Order By entity.code ";
            String whereClause = " Where (1=1) ";
            if(dto.getTextSearch() != null) {
                whereClause += " AND entity.code Like :textSearch ";
            }
            sqlSelect += whereClause + orderBy;
            sqlCount += whereClause;
            Query q = this.manager.createQuery(sqlSelect, ProductSaleDto.class);
            Query qCount = this.manager.createQuery(sqlCount);
            if(dto.getTextSearch() != null) {
                q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
                qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            }
            q.setFirstResult(pageIndex*pageSize);
            q.setMaxResults(pageSize);
            Long number = (Long) qCount.getSingleResult();
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            Page<ProductSaleDto> page = new PageImpl<>(q.getResultList(), pageable , number);
            return page;
        }
        return null;
    }
}
