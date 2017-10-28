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
package de.codecentric.bootifytestpyramid;

import de.codecentric.bootifytestpyramid.persistence.OrderEntity;
import de.codecentric.bootifytestpyramid.persistence.OrderEntityRepository;
import de.codecentric.bootifytestpyramid.persistence.PriceEmbeddable;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static de.codecentric.bootifytestpyramid.domain.Price.germanPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PriceCalculationSystemIT {

    private static final PriceEmbeddable EXPECTED_DELIVERY_PRICE = PriceEmbeddable.fromPrice(germanPrice(19.99));

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderEntityRepository repository;

    @Autowired
    @Value("classpath:de/codecentric/bootifytestpyramid/web/create-order-request.json")
    private Resource createOrderRequest;

    @Test
    void should_persist_calculated_price() throws Exception {
        byte[] body = IOUtils.toByteArray(createOrderRequest.getURI());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        restTemplate.exchange("/orders", POST, new HttpEntity<>(body, headers), Void.class);


        List<OrderEntity> orders = repository.findAll();

        assertAll(
                () -> assertThat(orders).hasSize(1),
                () -> assertThat(orders.get(0).getDeliveryPrice()).isEqualTo(EXPECTED_DELIVERY_PRICE)
        );
    }

}
