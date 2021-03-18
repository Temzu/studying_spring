package com.temzu.spring.data.model.dtos;

import com.temzu.spring.data.model.Category;
import com.temzu.spring.data.model.entities.CategoryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private CategoryDto categoryDto;

}
