package com.temzu.spring.data.model.mappers;

import com.temzu.spring.data.model.dtos.ProductDto;
import com.temzu.spring.data.model.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    public ProductDto toProductDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    public Product toProduct(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

}