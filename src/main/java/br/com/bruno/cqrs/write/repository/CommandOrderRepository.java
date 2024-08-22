package br.com.bruno.cqrs.write.repository;

import br.com.bruno.cqrs.write.entity.CommandOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandOrderRepository extends MongoRepository<CommandOrder, String> {
    Optional<CommandOrder> findTopByStaticIdOrderByCreatedAtDesc(String staticId);
}
