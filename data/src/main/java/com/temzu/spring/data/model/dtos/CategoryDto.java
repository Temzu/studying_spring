package com.temzu.spring.data.model.dtos;

import com.temzu.spring.data.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto implements Category {
    private Long id;
    private String name;
}
