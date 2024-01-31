package com.teste.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.api.dtos.CategoryDto;
import com.teste.api.models.CategoryModel;
import com.teste.api.models.RecipeModel;
import com.teste.api.service.CategoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/category")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody @Valid CategoryDto body){
        CategoryModel category = categoryService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findBuCategoryId(@PathVariable("id") Long id) {
        List<RecipeModel> recipes = categoryService.findByCategoryId(id);
        return ResponseEntity.status(HttpStatus.OK).body(recipes);
    }
    
}
