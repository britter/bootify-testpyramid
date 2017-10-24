package de.codecentric.bootifytestpyramid.domain;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public final class Price {

    public static final Currency EUR = Currency.getInstance("EUR");

    public static final BigDecimal GERMAN_TAX = BigDecimal.valueOf(0.19);

    private final BigDecimal amount;

    private final Currency currency;

    private final BigDecimal tax;

    public Price(final BigDecimal amount, final Currency currency, final BigDecimal tax) {
        this.amount = Preconditions.checkPositive(amount, "Parameter 'amount' must not be null");
        this.currency = Objects.requireNonNull(currency, "Parameter 'currency' must not be null");
        this.tax = Preconditions.checkPositive(tax, "Parameter 'tax' must not be null");
    }

    public static Price germanPrice(final BigDecimal amount) {
        return new Price(amount, EUR, GERMAN_TAX);
    }

    public static Price germanPrice(final double amount) {
        return germanPrice(BigDecimal.valueOf(amount));
    }

    public Price add(final Price other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies have to match when adding prices!");
        }
        if (tax.compareTo(other.tax) != 0) {
            throw new IllegalArgumentException("Tax values have to match when adding prices");
        }

        return new Price(amount.add(other.amount), currency, tax);
    }

    public Price multiply(final int factor) {
        Preconditions.checkPositive(factor, "factor");

        return new Price(amount.multiply(BigDecimal.valueOf(factor)), currency, tax);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Price price = (Price) o;
        return Objects.equals(amount, price.amount) &&
                Objects.equals(currency, price.currency) &&
                Objects.equals(tax, price.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency, tax);
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency=" + currency +
                ", tax=" + tax +
                '}';
    }
}
