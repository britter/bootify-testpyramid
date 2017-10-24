package de.codecentric.bootifytestpyramid.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static de.codecentric.bootifytestpyramid.domain.OrderItemTemplates.chairs;
import static de.codecentric.bootifytestpyramid.domain.Price.germanPrice;
import static de.codecentric.bootifytestpyramid.domain.WeightTemplates.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {

    @Nested
    class Invariants {

        @Test
        void should_throw_exception_when_null_name() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, null, "description", ONE, germanPrice(19.99), 1));
        }

        @Test
        void should_throw_exception_when_null_description() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, "name", null, ONE, germanPrice(19.99), 1));
        }

        @Test
        void should_throw_exception_when_null_weight() {
            assertThrows(NullPointerException.class, () -> new OrderItem(1, "name", "description", null, germanPrice(19.99), 1));
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
            assertThat(chairs(2).getTotalWeight()).isEqualTo(new Weight(20));
        }
    }
}
