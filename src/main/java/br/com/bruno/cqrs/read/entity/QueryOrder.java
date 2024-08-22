package br.com.bruno.cqrs.read.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Builder
public class QueryOrder {

    @Id
    private String id;

    private String customerId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Integer> items = new HashMap<>();

    public QueryOrder() {
    }

    public QueryOrder(String id, String customerId, Map<String, Integer> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryOrder that = (QueryOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
