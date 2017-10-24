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
