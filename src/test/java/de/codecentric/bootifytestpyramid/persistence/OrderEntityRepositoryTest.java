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
