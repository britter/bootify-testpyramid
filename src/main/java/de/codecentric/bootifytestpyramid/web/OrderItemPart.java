package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;
import de.codecentric.bootifytestpyramid.domain.OrderItem;
import de.codecentric.bootifytestpyramid.domain.Price;
import de.codecentric.bootifytestpyramid.domain.Weight;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
final class OrderItemPart {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Positive
    private BigDecimal weight;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private int quantity;

    OrderItemPart() {
    }

    OrderItemPart(final OrderItem orderItem) {
        this.name = orderItem.getName();
        this.description = orderItem.getDescription();
        this.weight = orderItem.getWeight().getValue();
        this.price = orderItem.getPrice().getAmount();
        this.quantity = orderItem.getQuantity();
    }

    CreateOrderCommand.CreateOrderItem toCreateOrderItem() {
        CreateOrderCommand.CreateOrderItem item = new CreateOrderCommand.CreateOrderItem();
        item.name = name;
        item.description = description;
        item.price = Price.germanPrice(price);
        item.weight = new Weight(weight);
        item.quantity = quantity;
        return item;
    }
}
