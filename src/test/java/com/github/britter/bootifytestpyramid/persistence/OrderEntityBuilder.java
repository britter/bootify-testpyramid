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
