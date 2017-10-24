package de.codecentric.bootifytestpyramid.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeightClassTest {

    @Test
    void should_get_weightClasses() {
        assertThat(WeightClass.valueOf(new Weight(50))).isEqualTo(WeightClass.UP_TO_100);
        assertThat(WeightClass.valueOf(new Weight(100))).isEqualTo(WeightClass.UP_TO_100);
        assertThat(WeightClass.valueOf(new Weight(150))).isEqualTo(WeightClass.FROM_100_TO_500);
        assertThat(WeightClass.valueOf(new Weight(500))).isEqualTo(WeightClass.FROM_100_TO_500);
        assertThat(WeightClass.valueOf(new Weight(550))).isEqualTo(WeightClass.ABOVE_500);
    }
}
