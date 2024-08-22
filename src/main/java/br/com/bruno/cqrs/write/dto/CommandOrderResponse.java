package br.com.bruno.cqrs.write.dto;

import lombok.Builder;

@Builder
public record CommandOrderResponse(
        String status,
        Integer code,
        String message
) {
}
