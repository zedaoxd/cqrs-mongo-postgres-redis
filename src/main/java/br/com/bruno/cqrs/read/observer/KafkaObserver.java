package br.com.bruno.cqrs.read.observer;

import br.com.bruno.cqrs.read.service.QueryOrderService;
import br.com.bruno.cqrs.shared.OrderCreateKafka;
import br.com.bruno.cqrs.shared.OrderUpdateKafka;
import br.com.bruno.cqrs.shared.enums.KafkaTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaObserver {
    private final QueryOrderService service;

    @KafkaListener(topics = KafkaTopic.ORDER_CREATE, groupId = "order-group")
    private void createOrder(final OrderCreateKafka order) {
        service.createOrder(order);
    }

    @KafkaListener(topics = KafkaTopic.ORDER_UPDATE, groupId = "order-group")
    private void updateOrder(final OrderUpdateKafka order) {
        service.updateOrder(order);
    }
}
