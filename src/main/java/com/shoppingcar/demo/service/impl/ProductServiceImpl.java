package com.shoppingcar.demo.service.impl;

import com.shoppingcar.demo.dto.ProductDto;
import com.shoppingcar.demo.dto.mapper.ProductMapper;
import com.shoppingcar.demo.exceptions.ProductException;
import com.shoppingcar.demo.model.domain.Product;
import com.shoppingcar.demo.model.repository.ProductRepository;
import com.shoppingcar.demo.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for the Product entity.
 */
@Service
@Transactional
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Save a product.
     *
     * @param productDto the entity to save
     * @return the persisted entity
     */
    public ProductDto save(ProductDto productDto) throws ProductException {
        log.debug("Request to save Product : {}", productDto);

        try {
            Product product = productMapper.toEntity(productDto);
            product = productRepository.save(product);
            return productMapper.toDto(product);
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred while persisting the product: %s", ex.getMessage()));
        }
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProductDto findOne(Long id) throws ProductException {
        log.debug("[ProductService] - findOne {} " + "productId: " + id);

        try {
            return productMapper.toDto(this.findById(id));
        } catch (ProductException e) {
            throw e;
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred getting a product by id: %s", ex.getMessage()));
        }
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Product findById(Long id) throws ProductException {
        log.debug("[ProductService] - findById {} " + "productId: " + id);

        try {
            return productRepository.findById(id)
                    .orElseThrow(() -> new ProductException(String.format("There is no product with id %s", id)));
        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * Get all Products.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() throws ProductException {
        log.debug("[ProductService] - getAll {}");

        try {
            return productMapper.toDto(productRepository.findAll());
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred getting the product list: %s", ex.getMessage()));
        }
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) throws ProductException {
        log.info("[ProductService] - deletePeripheralDeviceFromGateway {} " + "productId: " + id);

        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ProductException(String.format("An error occurred getting the product list: %s", ex.getMessage()));
        }
    }
}
