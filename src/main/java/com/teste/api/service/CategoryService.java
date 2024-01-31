package com.teste.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teste.api.dtos.CategoryDto;
import com.teste.api.models.CategoryModel;
import com.teste.api.models.RecipeModel;
import com.teste.api.repository.CatergoryRepository;
import com.teste.api.repository.RecipeRepository;

@Service
public class CategoryService {
    final CatergoryRepository catergoryRepository;
    final RecipeRepository recipeRepository;
    CategoryService(CatergoryRepository catergoryRepository, RecipeRepository recipeRepository){
        this.catergoryRepository = catergoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public CategoryModel save(CategoryDto dto) {
        CategoryModel category = new CategoryModel(dto);
        return catergoryRepository.save(category);
    }

    public List<RecipeModel> findByCategoryId(Long id) {
        return recipeRepository.findByCategoryId(id);
    }
}
