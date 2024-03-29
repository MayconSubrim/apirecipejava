package com.teste.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.api.models.RecipeModel;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeModel, Long>{
    boolean existsByTitle(String title);

     @Query(value = "SELECT r.id, title, ingredients, steps, author_id FROM recipes r JOIN tb_category rc ON rc.recipe_id = r.id JOIN recipe_category c ON rc.category_id = c.id WHERE c.id = :categoryId", nativeQuery = true)
    List<RecipeModel> findByCategoryId(@Param("categoryId") Long categoryId);
}
