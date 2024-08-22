package br.com.bruno.cqrs.write.service;

import br.com.bruno.cqrs.shared.OrderCreateKafka;
import br.com.bruno.cqrs.shared.OrderUpdateKafka;
import br.com.bruno.cqrs.shared.enums.KafkaTopic;
import br.com.bruno.cqrs.write.dto.CommandOrderCreateRequest;
import br.com.bruno.cqrs.write.dto.CommandOrderUpdateRequest;
import br.com.bruno.cqrs.write.entity.CommandOrder;
import br.com.bruno.cqrs.write.mapper.CommandOrderMapper;
import br.com.bruno.cqrs.write.repository.CommandOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandOrderService {
    private final CommandOrderRepository repository;
    private final CommandOrderMapper mapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public void createOrder(CommandOrderCreateRequest request) {
        var staticId = UUID.randomUUID().toString();
        kafkaTemplate.send(KafkaTopic.ORDER_CREATE, OrderCreateKafka.builder()
                .staticId(staticId)
                .customerId(request.customerId())
                .items(request.items())
                .build());

        var order = mapper.toOrder(request);
        order.setStaticId(staticId);
        repository.save(order);
    }

    @Transactional
    public void updateOrder(final String id, CommandOrderUpdateRequest request) {
        var order = repository.findTopByStaticIdOrderByCreatedAtDesc(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        kafkaTemplate.send(KafkaTopic.ORDER_UPDATE, OrderUpdateKafka.builder()
                .staticId(order.getStaticId())
                .items(request.items())
                .build());

        CommandOrder newEntity = CommandOrder.builder()
                .staticId(order.getStaticId())
                .customerId(order.getCustomerId())
                .items(request.items())
                .build();
        order.setStaticId(null);
        repository.save(newEntity);
    }

}
