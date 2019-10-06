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

import com.github.britter.bootifytestpyramid.domain.CreateOrderCommand;
import com.github.britter.bootifytestpyramid.domain.Order;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AddressEmbeddable deliveryAddress;

    @Embedded
    private PriceEmbeddable deliveryPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(final AddressEmbeddable deliveryAddress, List<OrderItemEntity> orderItems) {
        this.deliveryAddress = deliveryAddress;
        this.items = new ArrayList<>(orderItems);
        items.forEach(item -> item.setOrder(this));
    }

    public static OrderEntity fromCreateOrderCommand(final CreateOrderCommand createOrderCommand) {
        return new OrderEntity(
                AddressEmbeddable.fromAddress(createOrderCommand.deliveryAddress),
                createOrderItemEntities(createOrderCommand.items)
        );
    }

    private static List<OrderItemEntity> createOrderItemEntities(final List<CreateOrderCommand.CreateOrderItem> items) {
        return items.stream().map(OrderItemEntity::fromCreateOrderItem).collect(toList());
    }

    public Long getId() {
        return id;
    }

    public AddressEmbeddable getDeliveryAddress() {
        return deliveryAddress;
    }

    public PriceEmbeddable getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(final PriceEmbeddable deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public List<OrderItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Order toOrder() {
        return new Order(
                id,
                deliveryAddress.toAddress(),
                items.stream()
                        .map(OrderItemEntity::toOrderItem)
                        .collect(toList()));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var entity = (OrderEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                '}';
    }
}
