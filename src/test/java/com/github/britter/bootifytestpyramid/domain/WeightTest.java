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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.britter.bootifytestpyramid.domain.WeightTemplates.ONE;
import static com.github.britter.bootifytestpyramid.domain.WeightTemplates.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeightTest {

    @Nested
    class Invariants {

        @Test
        void should_throw_exception_when_passing_null_value() {
            assertThrows(NullPointerException.class, () -> new Weight(null));
        }

        @Test
        void should_throw_exception_when_passing_negative_value() {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> new Weight(BigDecimal.valueOf(-1))),
                    () -> assertThrows(IllegalArgumentException.class, () -> new Weight(-1))
            );
        }
    }

    @Nested
    class Calculations {

        @Nested
        class Add {

            @Test
            void should_add_weights() {
                assertThat(ONE.add(ONE)).isEqualTo(TWO);
            }
        }

        @Nested
        class Multiply {

            @Test
            void should_multiply_weights() {
                assertThat(ONE.multiply(2)).isEqualTo(TWO);
            }

            @Test
            void should_throw_exception_when_multiply_with_negtaive_factor() {
                assertThrows(IllegalArgumentException.class, () -> ONE.multiply(-2));
            }
        }
    }

    @Nested
    class Comparing {

        @Test
        void should_compare_to_other_weights() {
            assertAll(
                    () -> assertThat(ONE.compareTo(ONE)).isEqualTo(0),
                    () -> assertThat(ONE.compareTo(TWO)).isLessThan(0),
                    () -> assertThat(TWO.compareTo(ONE)).isGreaterThan(0)
            );
        }
    }
}
