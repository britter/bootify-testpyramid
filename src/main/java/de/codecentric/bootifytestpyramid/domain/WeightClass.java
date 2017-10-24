package de.codecentric.bootifytestpyramid.domain;

public enum WeightClass {

    UP_TO_100,
    FROM_100_TO_500,
    ABOVE_500;

    private static final Weight HUNDRED = new Weight(100);

    private static final Weight FIVE_HUNDRED = new Weight(500);

    public static WeightClass valueOf(final Weight weight) {
        if (weight.compareTo(HUNDRED) <= 0) {
            return UP_TO_100;
        } else if (weight.compareTo(HUNDRED) > 0 && weight.compareTo(FIVE_HUNDRED) <= 0) {
            return FROM_100_TO_500;
        } else {
            return ABOVE_500;
        }
    }
}
