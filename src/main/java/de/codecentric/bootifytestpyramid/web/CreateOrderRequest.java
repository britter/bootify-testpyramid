package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
final class CreateOrderRequest {

    @NotNull
    @Valid
    private AddressPart deliveryAddress;

    @NotEmpty
    @Valid
    private List<OrderItemPart> items;

    CreateOrderCommand toCommand() {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand();
        createOrderCommand.deliveryAddress = deliveryAddress.toAddress();
        createOrderCommand.items = items.stream()
                .map(OrderItemPart::toCreateOrderItem)
                .collect(toList());
        return createOrderCommand;
    }
}
