package br.com.bruno.cqrs.shared;

import lombok.Builder;

import java.io.Serializable;
import java.util.Map;

@Builder
public record OrderUpdateKafka(String staticId, Map<String, Integer> items)  implements Serializable {
}
