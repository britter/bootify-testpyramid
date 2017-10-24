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

        OrderEntity entity = createEntity(createOrderCommand);
        entity = orderEntityRepository.save(entity);
        Order order = entity.toOrder();
        entity.setDeliveryPrice(PriceEmbeddable.fromPrice(order.calculateDeliveryPrice()));
        return order;
    }

    private static OrderEntity createEntity(final CreateOrderCommand createOrderCommand) {
        return OrderEntity.fromCreateOrderCommand(createOrderCommand);
    }

}
