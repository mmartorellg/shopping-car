package com.shoppingcar.demo.service;

import com.shoppingcar.demo.dto.ProductDto;
import com.shoppingcar.demo.exceptions.ProductException;

import java.util.List;

/**
 * Service interface for the Client entity.
 */
public interface ProductService {

    ProductDto save(ProductDto productDto) throws ProductException;

    List<ProductDto> findAll() throws ProductException;

    ProductDto findOne(Long id) throws ProductException;

    void delete(Long id) throws ProductException;
}
