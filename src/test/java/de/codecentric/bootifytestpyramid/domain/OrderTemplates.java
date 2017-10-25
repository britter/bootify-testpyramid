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
package de.codecentric.bootifytestpyramid.domain;

import java.util.Arrays;

import static de.codecentric.bootifytestpyramid.domain.AddressTemplates.johnDoe;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.bed;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.chairs;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.closet;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.closets;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.couch;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.table;
import static java.util.Collections.singletonList;

public class OrderTemplates {

    private OrderTemplates() {
    }

    public static Order smallOrder() {
        return new Order(
                1, johnDoe(), singletonList(chairs(2))
        );
    }

    public static Order mediumOrder() {
        return new Order(
                1, johnDoe(), Arrays.asList(couch(), closet())
        );
    }

    public static Order completeKitchen() {
        return new Order(
                1, johnDoe(), Arrays.asList(table(), chairs(4))
        );
    }

    public static Order bigOrder() {
        return new Order(
                1, johnDoe(), Arrays.asList(bed(), closets(2))
        );
    }
}
