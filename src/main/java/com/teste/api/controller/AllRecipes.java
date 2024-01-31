package com.teste.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.teste.api.service.RecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/recipes")
public class AllRecipes {

    
    final RecipeService recipeService;

    AllRecipes(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeModel>> GetRecipes() {
        List<RecipeModel> recipe = recipeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(recipe);
     }

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetRecipesById(@PathVariable long id){
        Optional<RecipeModel> recipe = recipeService.findById(id);
        if (!recipe.isPresent()) { 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("recipe not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(recipe);
    }

    @PostMapping()
    public ResponseEntity<Object> CreatRecipe(@RequestBody @Valid RecipeDTO body){
        Optional<RecipeModel> recipe = recipeService.save(body);

        if (!recipe.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("a recipe with title alredy exists");
        }
        recipeService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(body) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> UpdateRecipe(@PathVariable Long id, @RequestBody RecipeDTO  body) {
        Optional<RecipeModel> recipe = recipeService.findById(id);

        if (!recipe.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("recipe not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.update(body, id));
     }
    
    @DeleteMapping("/{id}")
     public ResponseEntity<Void> DeleteRecipe(@PathVariable Long id) { 
        recipeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }
}
