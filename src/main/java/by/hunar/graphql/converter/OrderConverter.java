package by.hunar.graphql.converter;

import by.hunar.graphql.entity.OrderEntity;
import by.hunar.graphql.model.Order;
import by.hunar.graphql.repository.OrderRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public record OrderConverter(OrderRepository repository, OrderStateConverter stateConverter, ProductConverter productConverter) {

    public Order convertToDto(OrderEntity entity) {
        return new Order(entity.getId(), entity.getName(), stateConverter.convertToDto(entity.getState()),
            entity.getProducts().stream().map(product -> productConverter.convertToDto(product)).collect(Collectors.toList()), entity.getPlacedAt());
    }

    public OrderEntity convertToEntity(Order order) {
        Optional<OrderEntity> entityOptional = repository.findById(order.id());
        if (entityOptional.isPresent()) {
            OrderEntity entity = entityOptional.get();
            entity.setName(order.name());
            entity.setPlacedAt(order.placedAt());
            entity.setState(stateConverter.convertToEntity(order.state()));
            entity.setProducts(order.products().stream().map(productConverter::convertToEntity).toList());
            return entity;
        } else {
            OrderEntity entity = new OrderEntity();
            entity.setId(order.id());
            entity.setName(order.name());
            entity.setPlacedAt(order.placedAt());
            entity.setState(stateConverter.convertToEntity(order.state()));
            entity.setProducts(order.products().stream().map(productConverter::convertToEntity).toList());
            return entity;
        }
    }
}
