package com.teste.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teste.api.dtos.RecipeDTO;
import com.teste.api.models.RecipeModel;
import com.teste.api.repository.RecipeRepository;

@Service
public class RecipeService {
    final RecipeRepository recipeRepository;

    RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public  List<RecipeModel> findAll(){
        return recipeRepository.findAll();
    }

    public Optional<RecipeModel> findById(Long id) {
        return recipeRepository.findById(id);
    }

    public Optional<RecipeModel> save(RecipeDTO dto) {
        if (recipeRepository.existsByTitle(dto.getTitle())) {
            return Optional.empty();
        }
        RecipeModel recipe = new RecipeModel(dto);
        return Optional.of(recipeRepository.save(recipe));
    }

    public RecipeModel update(RecipeDTO dto, Long id){
        RecipeModel newRecipe = new RecipeModel(dto);
        newRecipe.setId(id);
        return recipeRepository.save(newRecipe);
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
