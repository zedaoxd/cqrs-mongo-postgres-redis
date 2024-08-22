package br.com.bruno.cqrs.read.repository;

import br.com.bruno.cqrs.read.entity.QueryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryOrderRepository extends JpaRepository<QueryOrder, String> {
}
