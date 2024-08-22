package br.com.bruno.cqrs.write.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record CommandOrderUpdateRequest(
        @NotNull
        Map<String, Integer> items) {
}
