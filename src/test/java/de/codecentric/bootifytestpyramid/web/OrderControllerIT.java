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
package de.codecentric.bootifytestpyramid.web;

import de.codecentric.bootifytestpyramid.domain.OrderGateway;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.bigOrder;
import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.completeKitchen;
import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.smallOrder;
import static de.codecentric.bootifytestpyramid.web.OrderResponseMatchers.bigOrderFields;
import static de.codecentric.bootifytestpyramid.web.OrderResponseMatchers.completeKitchenFieldsAtIndex;
import static de.codecentric.bootifytestpyramid.web.OrderResponseMatchers.smallOrderFieldsAtIndex;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderGateway orderGateway;

    @Nested
    class GetAll {

        @Test
        void should_return_all_orders() throws Exception {
            given(orderGateway.findAll()).willReturn(
                    asList(smallOrder(), completeKitchen())
            );

            mvc.perform(get("/orders"))
                    .andExpect(status().isOk())
                    .andExpect(smallOrderFieldsAtIndex(0))
                    .andExpect(completeKitchenFieldsAtIndex(1));
        }
    }

    @Nested
    class GetByCity {

        @Test
        void should_return_all_orders() throws Exception {
            given(orderGateway.findByCity(anyString())).willReturn(
                    asList(smallOrder(), completeKitchen())
            );

            mvc.perform(get("/orders?city=Newton"))
                    .andExpect(status().isOk())
                    .andExpect(smallOrderFieldsAtIndex(0))
                    .andExpect(completeKitchenFieldsAtIndex(1));
        }

        @Test
        void should_return_query_for_city() throws Exception {
            mvc.perform(get("/orders?city=Newton"));

            verify(orderGateway).findByCity("Newton");
        }
    }

    @Nested
    class GetOne {

        @Test
        void should_return_order_by_id() throws Exception {
            given(orderGateway.findById(anyLong())).willReturn(Optional.of(bigOrder()));

            mvc.perform(get("/orders/15"))
                    .andExpect(status().isOk())
                    .andExpect(bigOrderFields());
        }

        @Test
        void should_return_not_found_when_order_is_not_found() throws Exception {
            given(orderGateway.findById(anyLong())).willReturn(Optional.empty());

            mvc.perform(get("/orders/15"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class Create {

        @Nested
        class Creation {

            @Autowired
            @Value("classpath:de/codecentric/bootifytestpyramid/web/create-order-request.json")
            private Resource createOrderRequest;

            @Test
            void should_return_location() throws Exception {
                given(orderGateway.createOrder(any())).willReturn(smallOrder());

                mvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(IOUtils.toByteArray(createOrderRequest.getURI())))
                        .andExpect(status().isCreated())
                        .andExpect(header().string("location", endsWith("/orders/1")));
            }

        }

        @Nested
        class Validation {

            @Test
            void should_reject_create_order_request_when_address_is_missing() throws Exception {
                mvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(
                                "{\n" +
                                        "  \"items\": [\n" +
                                        "    {\n" +
                                        "      \"name\": \"Couch\",\n" +
                                        "      \"description\": \"A cozzy couch\",\n" +
                                        "      \"weight\": 20.0,\n" +
                                        "      \"price\": 19.99,\n" +
                                        "      \"quantity\": 1\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}"
                        ))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void should_reject_create_order_request_when_weight_is_negative() throws Exception {
                mvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(
                                "{\n" +
                                        "  \"deliveryAddress\": {\n" +
                                        "    \"firstName\": \"John\",\n" +
                                        "    \"lastName\": \"Doe\",\n" +
                                        "    \"street\": \"Mainstreet 15\",\n" +
                                        "    \"zip\": \"64782\",\n" +
                                        "    \"city\": \"Newton\"\n" +
                                        "  },\n" +
                                        "  \"items\": [\n" +
                                        "    {\n" +
                                        "      \"name\": \"Couch\",\n" +
                                        "      \"description\": \"A cozzy couch\",\n" +
                                        "      \"weight\": -20.0,\n" +
                                        "      \"price\": 19.99,\n" +
                                        "      \"quantity\": 1\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}"
                        ))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void should_reject_create_order_request_when_price_is_negative() throws Exception {
                mvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(
                                "{\n" +
                                        "  \"deliveryAddress\": {\n" +
                                        "    \"firstName\": \"John\",\n" +
                                        "    \"lastName\": \"Doe\",\n" +
                                        "    \"street\": \"Mainstreet 15\",\n" +
                                        "    \"zip\": \"64782\",\n" +
                                        "    \"city\": \"Newton\"\n" +
                                        "  },\n" +
                                        "  \"items\": [\n" +
                                        "    {\n" +
                                        "      \"name\": \"Couch\",\n" +
                                        "      \"description\": \"A cozzy couch\",\n" +
                                        "      \"weight\": 20.0,\n" +
                                        "      \"price\": -19.99,\n" +
                                        "      \"quantity\": 1\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}"
                        ))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void should_reject_create_order_request_when_quantity_is_negative() throws Exception {
                mvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(
                                "{\n" +
                                        "  \"deliveryAddress\": {\n" +
                                        "    \"firstName\": \"John\",\n" +
                                        "    \"lastName\": \"Doe\",\n" +
                                        "    \"street\": \"Mainstreet 15\",\n" +
                                        "    \"zip\": \"64782\",\n" +
                                        "    \"city\": \"Newton\"\n" +
                                        "  },\n" +
                                        "  \"items\": [\n" +
                                        "    {\n" +
                                        "      \"name\": \"Couch\",\n" +
                                        "      \"description\": \"A cozzy couch\",\n" +
                                        "      \"weight\": 20.0,\n" +
                                        "      \"price\": 19.99,\n" +
                                        "      \"quantity\": -1\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}"
                        ))
                        .andExpect(status().isBadRequest());
            }
        }
    }
}
