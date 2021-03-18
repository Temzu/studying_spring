package com.temzu.spring.data.model.mappers;

import com.temzu.spring.data.model.dtos.ProductDto;
import com.temzu.spring.data.model.entities.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {CategoryMapper.class})
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "categoryDto", target = "category")
    Product productDtoToProduct(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto productToProductDto(Product product);

    List<Product> toProductList(List<ProductDto> productDtos);

    @InheritInverseConfiguration
    List<ProductDto> toProductDtoList(List<Product> products);
}