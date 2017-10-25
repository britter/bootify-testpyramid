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

import de.codecentric.bootifytestpyramid.domain.Order;
import de.codecentric.bootifytestpyramid.domain.OrderItem;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.bigOrder;
import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.completeKitchen;
import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.smallOrder;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

final class OrderResponseMatchers {

    private OrderResponseMatchers() {
    }

    static ResultMatcher bigOrderFields() throws Exception {
        return orderFieldsAtPath(bigOrder(), "$");
    }

    static ResultMatcher smallOrderFieldsAtIndex(int index) throws Exception {
        return orderFieldsAtIndex(smallOrder(), index);
    }

    static ResultMatcher completeKitchenFieldsAtIndex(int index) throws Exception {
        return orderFieldsAtIndex(completeKitchen(), index);
    }

    static ResultMatcher orderFieldsAtIndex(Order order, int index) throws Exception {
        String jsonPath = "$[" + index + "]";
        return orderFieldsAtPath(order, jsonPath);
    }

    private static ResultMatcher orderFieldsAtPath(final Order order, final String jsonPath) throws Exception {
        return allOf(
                jsonPath(jsonPath + ".id").value(order.getId()),
                jsonPath(jsonPath + ".deliveryAddress.firstName").value(order.getDeliveryAddress().getFirstName()),
                jsonPath(jsonPath + ".deliveryAddress.lastName").value(order.getDeliveryAddress().getLastName()),
                jsonPath(jsonPath + ".deliveryAddress.street").value(order.getDeliveryAddress().getStreet()),
                jsonPath(jsonPath + ".deliveryAddress.zip").value(order.getDeliveryAddress().getZip()),
                jsonPath(jsonPath + ".deliveryAddress.city").value(order.getDeliveryAddress().getCity()),
                jsonPath(jsonPath + ".deliveryPrice").value(order.calculateDeliveryPrice().getAmount()),
                orderItemsAtIndex(order.getItems(), jsonPath)
        );
    }

    static ResultMatcher orderItemsAtIndex(List<OrderItem> items, String jsonPath) throws Exception {
        final List<ResultMatcher> matchers = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            matchers.add(orderItemAt(items.get(i), jsonPath + ".items[" + i + "]"));
        }
        return allOf(matchers);
    }

    private static ResultMatcher orderItemAt(final OrderItem orderItem, final String jsonPath) throws Exception {
        return allOf(
                jsonPath(jsonPath + ".name").value(orderItem.getName()),
                jsonPath(jsonPath + ".description").value(orderItem.getDescription()),
                jsonPath(jsonPath + ".weight").value(orderItem.getWeight().getValue()),
                jsonPath(jsonPath + ".price").value(orderItem.getPrice().getAmount()),
                jsonPath(jsonPath + ".quantity").value(orderItem.getQuantity())
        );
    }

    private static ResultMatcher allOf(final ResultMatcher... matchers) throws Exception {
        return (result) -> {
            for (ResultMatcher m : matchers) {
                m.match(result);
            }
        };
    }

    private static ResultMatcher allOf(final Iterable<ResultMatcher> matchers) throws Exception {
        return (result) -> {
            for (ResultMatcher m : matchers) {
                m.match(result);
            }
        };
    }
}
