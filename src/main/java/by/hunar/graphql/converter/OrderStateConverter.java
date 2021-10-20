package by.hunar.graphql.converter;

import by.hunar.graphql.entity.OrderStateEntity;
import by.hunar.graphql.model.OrderState;
import by.hunar.graphql.repository.OrderStateRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public record OrderStateConverter(OrderStateRepository repository) {

    public OrderState convertToDto(OrderStateEntity entity) {
        return new OrderState(entity.getId(), entity.getName());
    }

    public OrderStateEntity convertToEntity(OrderState orderState) {
        Optional<OrderStateEntity> entityOptional = repository.findById(orderState.id());
        if(entityOptional.isPresent()) {
            OrderStateEntity orderStateEntity = entityOptional.get();
            orderStateEntity.setName(orderState.name());
            return orderStateEntity;
        } else {
            OrderStateEntity orderStateEntity = new OrderStateEntity();
            orderStateEntity.setId(orderState.id());
            orderStateEntity.setName(orderState.name());
            return orderStateEntity;
        }
    }
}
