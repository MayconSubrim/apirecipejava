package com.teste.api.models;

import com.teste.api.dtos.CategoryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "recipe_category")
public class CategoryModel {

    public CategoryModel(CategoryDto categoryDto){
        this.category = categoryDto.getCategory();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String category;
}
