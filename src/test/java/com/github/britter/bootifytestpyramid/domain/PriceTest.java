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

import java.math.BigDecimal;
import java.util.Currency;

import static com.github.britter.bootifytestpyramid.domain.Price.germanPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTest {

    @Nested
    class Invariants {

        @Test
        void should_throw_exception_when_passing_null_value() {
            assertThrows(NullPointerException.class, () -> new Price(null, Price.EUR, Price.GERMAN_TAX));
        }

        @Test
        void should_throw_exception_when_passing_null_currency() {
            assertThrows(NullPointerException.class, () -> new Price(BigDecimal.ONE, null, Price.GERMAN_TAX));
        }

        @Test
        void should_throw_exception_when_passing_null_tax() {
            assertThrows(NullPointerException.class, () -> new Price(BigDecimal.ONE, Price.EUR, null));
        }

        @Test
        void should_throw_exception_when_passing_negative_value() {
            assertThrows(IllegalArgumentException.class, () -> new Price(BigDecimal.valueOf(-1), Price.EUR, Price.GERMAN_TAX));
        }

        @Test
        void should_throw_exception_when_passing_negative_tax() {
            assertThrows(IllegalArgumentException.class, () -> new Price(BigDecimal.ONE, Price.EUR, BigDecimal.valueOf(-1)));
        }
    }

    @Nested
    class Calculations {

        @Nested
        class Add {

            @Test
            void should_add_prices() {
                Assertions.assertThat(Price.germanPrice(5).add(Price.germanPrice(3))).isEqualTo(Price.germanPrice(8));
            }

            @Test
            void should_throw_exception_when_adding_price_with_different_currency() {
                assertThrows(IllegalArgumentException.class, () -> Price.germanPrice(5).add(new Price(BigDecimal.ONE, Currency.getInstance("USD"), Price.GERMAN_TAX)));
            }

            @Test
            void should_throw_exception_when_adding_price_with_different_tax() {
                assertThrows(IllegalArgumentException.class, () -> Price.germanPrice(5).add(new Price(BigDecimal.ONE, Price.EUR, BigDecimal.valueOf(0.23))));
            }
        }

        @Nested
        class Multiply {

            @Test
            void should_multiply_prices() {
                Assertions.assertThat(Price.germanPrice(5).multiply(2)).isEqualTo(Price.germanPrice(10));
            }

            @Test
            void should_throw_exception_when_multiply_with_negative_factor() {
                assertThrows(IllegalArgumentException.class, () -> Price.germanPrice(5).multiply(-2));
            }
        }
    }
}
