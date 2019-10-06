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
package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.CreateOrderCommand;
import de.codecentric.bootifytestpyramid.domain.Order;
import de.codecentric.bootifytestpyramid.domain.OrderGateway;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class JpaOrderGateway implements OrderGateway {

    private final OrderEntityRepository orderEntityRepository;

    public JpaOrderGateway(final OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderEntityRepository.findAll()
                .stream()
                .map(OrderEntity::toOrder)
                .collect(toList());
    }

    @Override
    public List<Order> findByCity(final String city) {
        Objects.requireNonNull(city, "Parameter 'city' must not be null");

        return orderEntityRepository.findByDeliveryAddressCity(city)
                .stream()
                .map(OrderEntity::toOrder)
                .collect(toList());
    }

    @Override
    public Optional<Order> findById(final long id) {
        return orderEntityRepository.findById(id)
                .map(OrderEntity::toOrder);
    }

    @Override
    @Transactional
    public Order createOrder(final CreateOrderCommand createOrderCommand) {
        Objects.requireNonNull(createOrderCommand, "Parameter 'createOrderCommand' must not be null");

        var entity = createEntity(createOrderCommand);
        entity = orderEntityRepository.save(entity);
        var order = entity.toOrder();
        entity.setDeliveryPrice(PriceEmbeddable.fromPrice(order.calculateDeliveryPrice()));
        return order;
    }

    private static OrderEntity createEntity(final CreateOrderCommand createOrderCommand) {
        return OrderEntity.fromCreateOrderCommand(createOrderCommand);
    }

}
