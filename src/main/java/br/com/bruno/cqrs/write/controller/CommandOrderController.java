package br.com.bruno.cqrs.write.controller;

import br.com.bruno.cqrs.write.dto.CommandOrderCreateRequest;
import br.com.bruno.cqrs.write.dto.CommandOrderUpdateRequest;
import br.com.bruno.cqrs.write.dto.CommandOrderResponse;
import br.com.bruno.cqrs.write.service.CommandOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/command/orders")
@RequiredArgsConstructor
public class CommandOrderController {

    private final CommandOrderService service;

    @PostMapping
    public ResponseEntity<CommandOrderResponse> createOrder(@Valid @RequestBody CommandOrderCreateRequest request) {
        service.createOrder(request);
        return ResponseEntity.ok(CommandOrderResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Order created successfully")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandOrderResponse> updateOrder(@PathVariable String id,
                                                            @Valid @RequestBody CommandOrderUpdateRequest request) {
        service.updateOrder(id, request);
        return ResponseEntity.ok(CommandOrderResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Order updated successfully")
                .build());
    }
}
