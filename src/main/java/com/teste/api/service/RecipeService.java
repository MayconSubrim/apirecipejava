package com.teste.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.teste.api.dtos.RecipeDTO;
import com.teste.api.models.CategoryModel;
import com.teste.api.models.RecipeModel;
import com.teste.api.models.UserModel;
import com.teste.api.repository.CatergoryRepository;
import com.teste.api.repository.RecipeRepository;
import com.teste.api.repository.UserRepository;

@Service
public class RecipeService {
    final RecipeRepository recipeRepository;
    final UserRepository userRepository;
    final CatergoryRepository catergoryRepository;

    RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, CatergoryRepository catergoryRepository){
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.catergoryRepository = catergoryRepository;
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

        Optional<UserModel> user = userRepository.findById(dto.getAuthorId());

        if (!user.isPresent()) {
            return Optional.empty();
        }

        List<CategoryModel> categorys = catergoryRepository.findAllById(dto.getCategoryIds());


        RecipeModel recipe = new RecipeModel(dto, user.get(), categorys);
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
