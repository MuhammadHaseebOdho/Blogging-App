package com.blog.mapper;

import com.blog.entity.Category;
import com.blog.payload.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    @Autowired
    ModelMapper modelMapper;
    public CategoryDto categoryToCategoryDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }

    public List<CategoryDto> categoryListToCategoryDtoList(List<Category> categoryList){
        return categoryList.stream().map(
                category -> modelMapper.map(category,CategoryDto.class)
        ).collect(Collectors.toList());
    }

    public List<Category> categoryDtoListToCategoryList(List<CategoryDto> categoryDtoList){
        return categoryDtoList.stream().map(
                categoryDto -> modelMapper.map(categoryDto,Category.class)
        ).collect(Collectors.toList());
    }
}
