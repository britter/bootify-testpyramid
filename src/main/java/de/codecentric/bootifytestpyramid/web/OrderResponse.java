package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.Order;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
final class OrderResponse {

    private final long id;

    private AddressPart deliveryAddress;

    private List<OrderItemPart> items;

    private BigDecimal deliveryPrice;

    OrderResponse(Order order) {
        id = order.getId();
        deliveryAddress = new AddressPart(order.getDeliveryAddress());
        items = order.getItems().stream().map(OrderItemPart::new).collect(toList());
        deliveryPrice = order.calculateDeliveryPrice().getAmount();
    }
}
