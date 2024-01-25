package com.teste.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RecipeDTO {
    @NotBlank(message = "title is requeried")
    @Size(max = 150)  
    private String title;

    @NotBlank
    private String ingredients;

    private String steps;
}
