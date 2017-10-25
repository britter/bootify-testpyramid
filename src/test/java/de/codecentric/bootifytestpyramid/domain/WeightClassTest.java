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
