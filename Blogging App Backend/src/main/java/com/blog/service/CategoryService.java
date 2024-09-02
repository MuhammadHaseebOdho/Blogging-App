package com.blog.service;


import com.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);

    CategoryDto getCategory(Integer id);

    void deleteCategory(Integer id);

    List<CategoryDto> getCategories();
}
