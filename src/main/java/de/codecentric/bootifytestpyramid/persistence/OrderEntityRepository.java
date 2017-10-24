package de.codecentric.bootifytestpyramid.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByDeliveryAddressCity(String city);
}
