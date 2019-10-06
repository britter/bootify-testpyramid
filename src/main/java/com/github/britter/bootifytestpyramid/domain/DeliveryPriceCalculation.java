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

import static com.github.britter.bootifytestpyramid.domain.Price.germanPrice;

final class DeliveryPriceCalculation {

    private static final Price FIX_PRICE = Price.germanPrice(BigDecimal.valueOf(19.99));

    private static final Price OVERWEIGHT_PRICE = Price.germanPrice(BigDecimal.valueOf(99.99));

    private static final BigDecimal TEN_PERCENT = BigDecimal.valueOf(0.10);

    private DeliveryPriceCalculation() {
    }

    public static Price calculate(final Weight totalWeight) {
        var weightClass = WeightClass.valueOf(totalWeight);

        switch (weightClass) {
            case UP_TO_100:
                return FIX_PRICE;
            case FROM_100_TO_500:
                return weightDependendPrice(totalWeight);
            case ABOVE_500:
                return weightDependendPrice(totalWeight).add(OVERWEIGHT_PRICE);
            default:
                throw new IllegalStateException("Unable to determine WeightClass for weight " + totalWeight);
        }
    }

    private static Price weightDependendPrice(final Weight totalWeight) {
        return germanPrice(totalWeight.getValue().multiply(TEN_PERCENT));
    }
}
