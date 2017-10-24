package de.codecentric.bootifytestpyramid.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static de.codecentric.bootifytestpyramid.domain.WeightTemplates.ONE;
import static de.codecentric.bootifytestpyramid.domain.WeightTemplates.TWO;
import static org.assertj.core.api.Assertions.assertThat;
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
            assertThrows(IllegalArgumentException.class, () -> new Weight(BigDecimal.valueOf(-1)));
            assertThrows(IllegalArgumentException.class, () -> new Weight(-1));
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
            assertThat(ONE.compareTo(ONE)).isEqualTo(0);
            assertThat(ONE.compareTo(TWO)).isLessThan(0);
            assertThat(TWO.compareTo(ONE)).isGreaterThan(0);
        }
    }
}
