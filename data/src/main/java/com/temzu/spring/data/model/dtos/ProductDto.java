package com.temzu.spring.data.model.dtos;

import com.temzu.spring.data.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private Category categoryDto;
}
