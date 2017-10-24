package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;
import de.codecentric.bootifytestpyramid.domain.OrderItem;
import de.codecentric.bootifytestpyramid.domain.Weight;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal weight;

    private PriceEmbeddable price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public OrderItemEntity() {
    }

    public OrderItemEntity(final String name, final String description, final BigDecimal weight, final PriceEmbeddable price, final int quantity) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItemEntity fromCreateOrderItem(final CreateOrderCommand.CreateOrderItem c) {
        return new OrderItemEntity(c.name, c.description, c.weight.getValue(), PriceEmbeddable.fromPrice(c.price), c.quantity);
    }

    public OrderItem toOrderItem() {
        return new OrderItem(id, name, description, new Weight(weight), price.toPrice(), quantity);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public PriceEmbeddable getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(final OrderEntity order) {
        this.order = order;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderItemEntity that = (OrderItemEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderItemEntity{" +
                "id=" + id +
                '}';
    }
}
