package br.com.bruno.cqrs.read.dto;

import java.util.Map;

public record QueryOrderResponse(
        String id,
        String customerId,
        Map<String, Integer> items
) {
}
