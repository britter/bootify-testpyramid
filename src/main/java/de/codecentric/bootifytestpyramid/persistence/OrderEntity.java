package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;
import de.codecentric.bootifytestpyramid.domain.Order;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AddressEmbeddable deliveryAddress;

    @Embedded
    private PriceEmbeddable deliveryPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(final AddressEmbeddable deliveryAddress, List<OrderItemEntity> orderItems) {
        this.deliveryAddress = deliveryAddress;
        this.items = new ArrayList<>(orderItems);
        items.forEach(item -> item.setOrder(this));
    }

    public static OrderEntity fromCreateOrderCommand(final CreateOrderCommand createOrderCommand) {
        return new OrderEntity(
                AddressEmbeddable.fromAddress(createOrderCommand.deliveryAddress),
                createOrderItemEntities(createOrderCommand.items)
        );
    }

    private static List<OrderItemEntity> createOrderItemEntities(final List<CreateOrderCommand.CreateOrderItem> items) {
        return items.stream().map(OrderItemEntity::fromCreateOrderItem).collect(toList());
    }

    public Long getId() {
        return id;
    }

    public AddressEmbeddable getDeliveryAddress() {
        return deliveryAddress;
    }

    public PriceEmbeddable getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(final PriceEmbeddable deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public List<OrderItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Order toOrder() {
        return new Order(
                id,
                deliveryAddress.toAddress(),
                items.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(toList()));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderEntity entity = (OrderEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                '}';
    }
}
