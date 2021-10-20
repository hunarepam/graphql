package by.hunar.graphql.repository;

import by.hunar.graphql.entity.OrderEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findByStateId(Integer id);
}
