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
package com.github.britter.bootifytestpyramid.persistence;

import com.github.britter.bootifytestpyramid.domain.Order;
import com.github.britter.bootifytestpyramid.domain.OrderItem;
import com.github.britter.bootifytestpyramid.domain.OrderTemplates;

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
