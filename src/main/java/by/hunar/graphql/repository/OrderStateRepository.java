package by.hunar.graphql.repository;

import by.hunar.graphql.entity.OrderStateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStateRepository extends CrudRepository<OrderStateEntity, Integer> {
}
