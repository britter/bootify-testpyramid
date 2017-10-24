package de.codecentric.bootifytestpyramid.persistence;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public final class OrderEntityBuilder {

    private AddressEmbeddable deliveryAddress;

    private PriceEmbeddable deliveryPrice;

    private List<OrderItemEntity> items = new ArrayList<>();

    public static OrderEntityBuilder empty() {
        return new OrderEntityBuilder();
    }

    public OrderEntityBuilder withDeliveryAddress(AddressEmbeddable deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public OrderEntityBuilder withDeliveryPrice(PriceEmbeddable price) {
        this.deliveryPrice = price;
        return this;
    }

    public OrderEntityBuilder withOrderItems(OrderItemEntity... items) {
        this.items = asList(items);
        return this;
    }

    public OrderEntity build() {
        OrderEntity entity = new OrderEntity(deliveryAddress, items);
        entity.setDeliveryPrice(deliveryPrice);
        return entity;
    }
}
