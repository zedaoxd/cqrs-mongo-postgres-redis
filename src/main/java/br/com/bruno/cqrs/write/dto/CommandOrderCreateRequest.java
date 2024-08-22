package br.com.bruno.cqrs.write.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record CommandOrderCreateRequest(
        // TODO: create annotation to validate if the customerId is exists in the database
        @NotBlank
        String customerId,
        @NotNull
        Map<String, Integer> items
) {
}
