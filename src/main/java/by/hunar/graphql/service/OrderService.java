package by.hunar.graphql.service;

import by.hunar.graphql.converter.OrderConverter;
import by.hunar.graphql.converter.OrderStateConverter;
import by.hunar.graphql.model.Order;
import by.hunar.graphql.model.OrderState;
import by.hunar.graphql.repository.OrderRepository;
import by.hunar.graphql.repository.OrderStateRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderConverter orderConverter;
    private final OrderStateRepository orderStateRepository;
    private final OrderStateConverter orderStateConverter;

    public OrderService(OrderRepository repository,
                        OrderConverter orderConverter,
                        OrderStateRepository orderStateRepository,
                        OrderStateConverter orderStateConverter) {
        this.repository = repository;
        this.orderConverter = orderConverter;
        this.orderStateRepository = orderStateRepository;
        this.orderStateConverter = orderStateConverter;
    }

    public List<Order> getAllOrders() {
        List<Order> result = new ArrayList<>();
        repository.findAll().forEach(orderEntity -> result.add(orderConverter.convertToDto(orderEntity)));

        return result;
    }

    public List<Order> getOrdersByState(Integer stateId) {
        return repository.findByStateId(stateId).stream().map(orderConverter::convertToDto).collect(Collectors.toList());
    }

    public List<OrderState> getOrderStates() {
        List<OrderState> result = new ArrayList<>();
        orderStateRepository.findAll().forEach(entity -> result.add(orderStateConverter.convertToDto(entity)));
        return result;
    }
}
