package com.shoppingcar.demo.dto.mapper;

import com.shoppingcar.demo.dto.ProductDto;
import com.shoppingcar.demo.model.domain.Product;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Product and its DTO ProductDto.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {

}
