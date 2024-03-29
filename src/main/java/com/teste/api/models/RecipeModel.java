package com.teste.api.models;

import java.util.List;

import com.teste.api.dtos.RecipeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//para nao precisar criar get e seters e passar parametros nulos
@Data
@NoArgsConstructor
@AllArgsConstructor

//para identificar e criar uma tabela
@Entity
@Table(name = "recipes")
public class RecipeModel {

    public RecipeModel(RecipeDTO dto) {
        this.title = dto.getTitle();
        this.ingredients = dto.getIngredients();
        this.steps = dto.getSteps();
        this.author = new UserModel();
        this.author.setId(dto.getAuthorId());
    }

    public RecipeModel(RecipeDTO dto, UserModel user, List<CategoryModel> categorys) {
        this.title = dto.getTitle();
        this.ingredients = dto.getIngredients();
        this.steps = dto.getSteps();
        this.author = user;
        this.categories = categorys;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 150, nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String steps;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private UserModel author;

    @ManyToMany
    @JoinTable(
        name = "tb_category",
        joinColumns = @JoinColumn(name = "recipeId"),
        inverseJoinColumns = @JoinColumn(name = "categoryId")
    )
    private List<CategoryModel> categories;
}
