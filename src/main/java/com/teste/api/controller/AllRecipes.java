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

import com.teste.api.models.RecipeModel;
import com.teste.api.repository.RecipeRepository;

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
    public String CreatRecipe(@RequestBody String body){
        return body;
 
    }

    @PutMapping("/{id}")
    public String UpdateRecipe(@PathVariable Long id, @RequestBody String body) {
        return "receita " + id + " atualizada com sucesso";
     }
    
    @DeleteMapping("/{id}")
     public String DeleteRecipe(@PathVariable Long id) { 
        return "receita " + id + " deletada com seucesos";
     }
}
