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

import java.math.BigDecimal;
import java.util.Objects;

final class Preconditions {

    private Preconditions() {
    }

    static BigDecimal checkPositive(final BigDecimal value, final String parameterName) {
        Objects.requireNonNull(value, "Parameter '" + parameterName + "' must not be null");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Parameter '" + parameterName + "' must not be negative");
        }
        return value;
    }

    static int checkPositive(final int value, final String parameterName) {
        return checkPositive(BigDecimal.valueOf(value), parameterName).intValue();
    }
}
