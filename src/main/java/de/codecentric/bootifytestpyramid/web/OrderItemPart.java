/*
 * Copyright 2017 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;
import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand.CreateOrderItem;
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

    CreateOrderItem toCreateOrderItem() {
        var item = new CreateOrderItem();
        item.name = name;
        item.description = description;
        item.price = Price.germanPrice(price);
        item.weight = new Weight(weight);
        item.quantity = quantity;
        return item;
    }
}
