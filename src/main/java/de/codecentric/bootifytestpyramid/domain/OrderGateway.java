package de.codecentric.bootifytestpyramid.domain;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {

    List<Order> findAll();

    List<Order> findByCity(String city);

    Optional<Order> findById(long id);

    Order createOrder(CreateOrderCommand createOrderCommand);
}
