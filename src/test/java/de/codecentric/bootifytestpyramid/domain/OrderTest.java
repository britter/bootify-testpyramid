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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static de.codecentric.bootifytestpyramid.domain.AddressTemplates.johnDoe;
import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.chair;
import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.completeKitchen;
import static de.codecentric.bootifytestpyramid.domain.OrderTemplates.smallOrder;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    @Nested
    class Invariants {

        @Test
        void should_throw_exception_when_passing_null_deliveryAddress() {
            assertThrows(NullPointerException.class, () -> new Order(1, null, emptyList()));
        }

        @Test
        void should_throw_exception_when_passing_null_items() {
            assertThrows(NullPointerException.class, () -> new Order(1, johnDoe(), null));
        }

        @Test
        void should_throw_exception_when_items_are_modified() {
            assertThrows(UnsupportedOperationException.class, () -> smallOrder().getItems().clear());
            assertThrows(UnsupportedOperationException.class, () -> smallOrder().getItems().add(chair()));
        }
    }

    @Nested
    class Calculations {

        @Test
        void should_calculate_total_weight() {
            assertThat(completeKitchen().getTotalWeight()).isEqualTo(new Weight(60));
        }
    }
}
