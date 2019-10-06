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
package com.github.britter.bootifytestpyramid.domain;

import java.util.Arrays;

import static java.util.Collections.singletonList;

public class OrderTemplates {

    private OrderTemplates() {
    }

    public static Order smallOrder() {
        return new Order(
                1, AddressTemplates.johnDoe(), singletonList(OrderItemTemplates.chairs(2))
        );
    }

    public static Order mediumOrder() {
        return new Order(
                1, AddressTemplates.johnDoe(), Arrays.asList(OrderItemTemplates.couch(), OrderItemTemplates.closet())
        );
    }

    public static Order completeKitchen() {
        return new Order(
                1, AddressTemplates.johnDoe(), Arrays.asList(OrderItemTemplates.table(), OrderItemTemplates.chairs(4))
        );
    }

    public static Order bigOrder() {
        return new Order(
                1, AddressTemplates.johnDoe(), Arrays.asList(OrderItemTemplates.bed(), OrderItemTemplates.closets(2))
        );
    }
}
