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

import java.math.BigDecimal;
import java.util.Objects;

public final class Weight implements Comparable<Weight> {

    public static final Weight ZERO = new Weight(0);

    private final BigDecimal value;

    public Weight(final BigDecimal value) {
        this.value = Preconditions.checkPositive(value, "value");
    }

    public Weight(final int value) {
        this(BigDecimal.valueOf(value));
    }

    public BigDecimal getValue() {
        return value;
    }

    public Weight multiply(final int factor) {
        Preconditions.checkPositive(factor, "factor");

        return new Weight(value.multiply(BigDecimal.valueOf(factor)));
    }

    public Weight add(final Weight other) {
        return new Weight(value.add(other.value));
    }

    @Override
    public int compareTo(final Weight that) {
        return this.getValue().compareTo(that.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var weight = (Weight) o;
        return Objects.equals(value, weight.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Weight{" +
                "value=" + value +
                '}';
    }
}
