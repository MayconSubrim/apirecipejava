package com.teste.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.api.models.RecipeModel;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Long>{
    boolean existsByTitle(String title);
}
