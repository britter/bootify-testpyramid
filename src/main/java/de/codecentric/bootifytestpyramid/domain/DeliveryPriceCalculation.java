package de.codecentric.bootifytestpyramid.domain;

import java.math.BigDecimal;

import static de.codecentric.bootifytestpyramid.domain.Price.germanPrice;

public final class DeliveryPriceCalculation {

    private static final Price FIX_PRICE = germanPrice(BigDecimal.valueOf(19.99));

    private static final Price OVERWEIGHT_PRICE = germanPrice(BigDecimal.valueOf(99.99));

    private static final BigDecimal TEN_PERCENT = BigDecimal.valueOf(0.10);

    private DeliveryPriceCalculation() {
    }

    public static Price calculate(final Weight totalWeight) {
        WeightClass weightClass = WeightClass.valueOf(totalWeight);

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
