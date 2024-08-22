package br.com.bruno.cqrs.write.mapper;

import br.com.bruno.cqrs.write.dto.CommandOrderCreateRequest;
import br.com.bruno.cqrs.write.entity.CommandOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;


@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = ALWAYS,
        nullValuePropertyMappingStrategy = IGNORE
)
public interface CommandOrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CommandOrder toOrder(CommandOrderCreateRequest source);

}
