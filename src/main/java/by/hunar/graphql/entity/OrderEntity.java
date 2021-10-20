package by.hunar.graphql.entity;

import by.hunar.graphql.model.Product;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name="id_state", nullable=false)
    private OrderStateEntity state;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "products_orders",
        joinColumns = { @JoinColumn(name = "id_order") },
        inverseJoinColumns = { @JoinColumn(name = "id_product") }
    )
    private List<ProductEntity> products;
    private Date placedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderStateEntity getState() {
        return state;
    }

    public void setState(OrderStateEntity state) {
        this.state = state;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
