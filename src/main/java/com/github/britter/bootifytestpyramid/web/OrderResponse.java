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
package com.github.britter.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.britter.bootifytestpyramid.domain.Order;

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
