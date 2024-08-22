package br.com.bruno.cqrs.read.mapper;

import br.com.bruno.cqrs.shared.OrderCreateKafka;
import br.com.bruno.cqrs.shared.OrderUpdateKafka;
import br.com.bruno.cqrs.read.dto.QueryOrderResponse;
import br.com.bruno.cqrs.read.entity.QueryOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = ALWAYS,
        nullValuePropertyMappingStrategy = IGNORE
)
public interface QueryOrderMapper {

    QueryOrderResponse fromOrder(QueryOrder source);

    List<QueryOrderResponse> fromOrders(List<QueryOrder> source);

    @Mapping(target = "id", source = "staticId")
    QueryOrder fromCommandOrder(OrderCreateKafka source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    void fromCommandOrderUpdate(OrderUpdateKafka source, @MappingTarget QueryOrder dbOrder);
}
