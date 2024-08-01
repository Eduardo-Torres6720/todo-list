package com.example.todo_list.domain.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskDTO(
    @NotBlank
    @NotNull
    String title,
    String description
) {

}
