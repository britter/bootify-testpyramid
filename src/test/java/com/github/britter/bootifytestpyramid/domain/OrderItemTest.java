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

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.britter.bootifytestpyramid.domain.Price.germanPrice;
import static com.github.britter.bootifytestpyramid.domain.WeightTemplates.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {

    @Nested
    class Invariants {

        @Test
        void should_throw_exception_when_null_name() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, null, "description", ONE, Price.germanPrice(19.99), 1));
        }

        @Test
        void should_throw_exception_when_null_description() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, "name", null, ONE, Price.germanPrice(19.99), 1));
        }

        @Test
        void should_throw_exception_when_null_weight() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, "name", "description", null, Price.germanPrice(19.99), 1));
        }

        @Test
        void should_throw_exception_when_null_price() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, "name", "description", ONE, null, 1));
        }
    }

    @Nested
    class Calculations {

        @Test
        void should_calculate_totalWeight() {
            Assertions.assertThat(OrderItemTemplates.chairs(2).getTotalWeight()).isEqualTo(new Weight(20));
        }
    }
}
