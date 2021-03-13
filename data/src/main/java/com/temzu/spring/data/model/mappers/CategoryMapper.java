package com.temzu.spring.data.model.mappers;

import com.temzu.spring.data.model.dtos.CategoryDto;
import com.temzu.spring.data.model.entities.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryEntity CategoryDtoToCategory(CategoryDto categoryDto);

    @InheritInverseConfiguration
    CategoryDto CategoryToCategoryDto(CategoryEntity category);

    List<CategoryEntity> toCategoryList(List<CategoryDto> categoryDtos);

    @InheritInverseConfiguration
    List<CategoryDto> toCategoryDtoList(List<CategoryEntity> category);
}
