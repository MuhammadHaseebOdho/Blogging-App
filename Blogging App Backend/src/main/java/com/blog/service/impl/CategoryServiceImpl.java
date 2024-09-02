package com.blog.service.impl;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.mapper.CategoryMapper;
import com.blog.payload.CategoryDto;
import com.blog.repositories.CategoryRepository;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        categoryRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(id,"Requested Category Id: "+id+" can not be found")
        );
        categoryDto.setCategoryId(id);
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        return  categoryMapper.categoryToCategoryDto(categoryRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(id,"Requested Category Id: "+id+" can not be found")
        ));
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(id,"Requested Category Id: "+id+" can not be found")
        );
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryMapper.categoryListToCategoryDtoList(categoryRepository.findAll());
    }
}
