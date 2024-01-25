package com.teste.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.api.dtos.RecipeDTO;
import com.teste.api.models.RecipeModel;
import com.teste.api.repository.RecipeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipes")
public class AllRecipes {

    
    final RecipeRepository recipeRepository;

    AllRecipes(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<RecipeModel> GetRecipes() {
        return recipeRepository.findAll();
     }

    @GetMapping("/{id}")
    public Optional<RecipeModel> GetRecipesById(@PathVariable long id){
        Optional<RecipeModel> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) { 
            return Optional.empty();
        }

        return recipe;
    }

    @PostMapping()
    public RecipeModel CreatRecipe(@RequestBody @Valid RecipeDTO body){
        RecipeModel recipe = new RecipeModel(body);
        return recipeRepository.save(recipe);
    }

    @PutMapping("/{id}")
    public RecipeModel UpdateRecipe(@PathVariable Long id, @RequestBody RecipeDTO  body) {
        Optional<RecipeModel> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()){
            //tratar erro
        }

        RecipeModel newRecipe = new RecipeModel(body);
        newRecipe.setId(id);
        return recipeRepository.save(newRecipe);
     }
    
    @DeleteMapping("/{id}")
     public void DeleteRecipe(@PathVariable Long id) { 
        recipeRepository.deleteById(id);
     }
}
