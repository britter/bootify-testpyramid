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
package de.codecentric.bootifytestpyramid.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Order {

    private long id;

    private final Address deliveryAddress;

    private final List<OrderItem> items;

    public Order(final long id, final Address deliveryAddress, final List<OrderItem> items) {
        this.id = id;
        this.deliveryAddress = Objects.requireNonNull(deliveryAddress, "Parameter 'deliveryAddress' must not be null");
        this.items = new ArrayList<>(Objects.requireNonNull(items, "Parameter 'items' must not be null"));
    }

    public long getId() {
        return id;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Weight getTotalWeight() {
        return items.stream()
                .map(OrderItem::getTotalWeight)
                .reduce(Weight.ZERO, Weight::add);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var order = (Order) o;
        return id == order.id &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryAddress, items);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", deliveryAddress=" + deliveryAddress +
                ", items=" + items +
                '}';
    }

    public Price calculateDeliveryPrice() {
        return DeliveryPriceCalculation.calculate(getTotalWeight());
    }
}
