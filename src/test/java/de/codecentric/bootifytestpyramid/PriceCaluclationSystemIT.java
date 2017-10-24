package de.codecentric.bootifytestpyramid;

import de.codecentric.bootifytestpyramid.persistence.OrderEntity;
import de.codecentric.bootifytestpyramid.persistence.OrderEntityRepository;
import de.codecentric.bootifytestpyramid.persistence.PriceEmbeddable;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static de.codecentric.bootifytestpyramid.domain.Price.EUR;
import static de.codecentric.bootifytestpyramid.domain.Price.germanPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class PriceCaluclationSystemIT {

    private static final PriceEmbeddable EXPECTED_DELIVERY_PRICE = PriceEmbeddable.fromPrice(germanPrice(19.99));

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderEntityRepository repository;

    @Autowired
    @Value("classpath:de/codecentric/bootifytestpyramid/web/create-order-request.json")
    private Resource createOrderRequest;

    @Test
    void should_persist_calculated_price() throws Exception {
        mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(IOUtils.toByteArray(createOrderRequest.getURI())));

        List<OrderEntity> orders = repository.findAll();

        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getDeliveryPrice()).isEqualTo(EXPECTED_DELIVERY_PRICE);
    }

}
