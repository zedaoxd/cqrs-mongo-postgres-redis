package br.com.bruno.cqrs.read.controller;

import br.com.bruno.cqrs.read.dto.QueryOrderResponse;
import br.com.bruno.cqrs.read.service.QueryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/orders")
@RequiredArgsConstructor
public class QueryOrderController {
    private final QueryOrderService service;

    @GetMapping
    public ResponseEntity<List<QueryOrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryOrderResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
