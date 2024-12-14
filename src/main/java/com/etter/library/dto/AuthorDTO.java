package com.etter.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

}
