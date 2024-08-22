package br.com.bruno.cqrs.shared;

import lombok.Builder;

import java.io.Serializable;
import java.util.Map;

@Builder
public record OrderCreateKafka(
        String staticId,
        String customerId,
        Map<String, Integer> items
) implements Serializable {
}
