package by.hunar.graphql.controller;

import by.hunar.graphql.model.Order;
import by.hunar.graphql.model.OrderState;
import by.hunar.graphql.model.Product;
import by.hunar.graphql.model.ProductCategory;
import by.hunar.graphql.service.OrderService;
import by.hunar.graphql.service.ProductService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @QueryMapping(name = "orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @SchemaMapping(typeName = "OrderState", field = "orders")
    public List<Order> getOrdersByState(OrderState orderState) {
        return orderService.getOrdersByState(orderState.id());
    }

    @QueryMapping(name = "orderStates")
    public List<OrderState> getOrderStates() {
        return orderService.getOrderStates();
    }

    @MutationMapping
    public Product addProduct(@Argument String name, @Argument Integer categoryId, @Argument Float price) {
        return productService.storeProduct(name, categoryId, price);
    }

    @MutationMapping
    public ProductCategory addCategory(@Argument ProductCategory category) {
        return productService.storeProductCategory(category);
    }
}
