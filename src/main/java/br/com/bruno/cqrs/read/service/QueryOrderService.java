package br.com.bruno.cqrs.read.service;

import br.com.bruno.cqrs.shared.OrderCreateKafka;
import br.com.bruno.cqrs.shared.OrderUpdateKafka;
import br.com.bruno.cqrs.read.dto.QueryOrderResponse;
import br.com.bruno.cqrs.read.mapper.QueryOrderMapper;
import br.com.bruno.cqrs.read.repository.QueryOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QueryOrderService {
    private final QueryOrderRepository repository;
    private final QueryOrderMapper mapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "order", key = "#id")
    public QueryOrderResponse findById(final String id) {
        var order = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return mapper.fromOrder(order);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "order")
    public List<QueryOrderResponse> findAll() {
        return mapper.fromOrders(repository.findAll());
    }

    @CacheEvict(value = "order", allEntries = true)
    public void createOrder(final OrderCreateKafka order) {
        var Order = mapper.fromCommandOrder(order);
        log.info("Order created: {}", Order);
        repository.save(Order);
    }

    @CacheEvict(value = "order", key = "#order.staticId()")
    public void updateOrder(final OrderUpdateKafka order) {
        var orderEntity = repository.findById(order.staticId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        mapper.fromCommandOrderUpdate(order, orderEntity);
        orderEntity.getItems().entrySet().removeIf(entry -> entry.getValue() <= 0);
        repository.save(orderEntity);
    }
}
