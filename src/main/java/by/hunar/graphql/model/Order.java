package by.hunar.graphql.model;

import java.util.Date;
import java.util.List;

public record Order(Integer id, String name, OrderState state, List<Product> products, Date placedAt) {
}
