package de.codecentric.bootifytestpyramid.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderEntityRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private OrderEntityRepository repository;

    @Test
    void should_find_order_by_city_name() {
        em.persistAndFlush(orderIn("Berlin"));
        em.persistAndFlush(orderIn("Berlin"));
        OrderEntity shouldBeFound = em.persistAndFlush(orderIn("Düsseldorf"));

        List<OrderEntity> found = repository.findByDeliveryAddressCity("Düsseldorf");

        assertThat(found).hasSize(1);
        assertThat(found)
                .element(0)
                .hasFieldOrPropertyWithValue("id", shouldBeFound.getId());
    }

    private static OrderEntity orderIn(String city) {
        AddressEmbeddable address = AddressEmbeddableBuilder.johnDoe().withCity(city).build();
        return OrderEntityBuilder.empty().withDeliveryAddress(address).build();
    }
}
