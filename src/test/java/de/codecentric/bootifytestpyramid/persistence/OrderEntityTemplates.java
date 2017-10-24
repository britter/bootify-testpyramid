package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.Address;
import de.codecentric.bootifytestpyramid.domain.Order;
import de.codecentric.bootifytestpyramid.domain.OrderItem;
import de.codecentric.bootifytestpyramid.domain.OrderTemplates;

import java.util.stream.Collectors;

public final class OrderEntityTemplates {

    private OrderEntityTemplates() {
    }

    public static OrderEntity smallOrder() {
        return fromOrder(OrderTemplates.smallOrder());
    }

    public static OrderEntity mediumOrder() {
        return fromOrder(OrderTemplates.mediumOrder());
    }

    public static OrderEntity bigOrder() {
        return fromOrder(OrderTemplates.bigOrder());
    }

    public static OrderEntity completeKitchen() {
        return fromOrder(OrderTemplates.completeKitchen());
    }

    private static OrderEntity fromOrder(final Order smallOrder) {
        return new OrderEntity(
                AddressEmbeddable.fromAddress(smallOrder.getDeliveryAddress()),
                smallOrder.getItems()
                        .stream()
                        .map(OrderEntityTemplates::fromOrderItem)
                        .collect(Collectors.toList()));
    }

    private static OrderItemEntity fromOrderItem(OrderItem orderItem) {
        return new OrderItemEntity(
                orderItem.getName(),
                orderItem.getDescription(),
                orderItem.getWeight().getValue(),
                PriceEmbeddable.fromPrice(orderItem.getPrice()),
                orderItem.getQuantity());
    }
}
