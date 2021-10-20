package by.hunar.graphql.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


public record Product(
    @Id Integer id,
    String name,
    Float price,
    @ManyToOne @JoinColumn(name="id_category", nullable=false) ProductCategory category,
    @ManyToMany(mappedBy = "products") List<Order> orders) {
}
