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
package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.Price;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Embeddable
public class PriceEmbeddable {

    private BigDecimal amount;

    private Currency currency;

    private BigDecimal tax;

    public PriceEmbeddable() {
    }

    public PriceEmbeddable(final BigDecimal amount, final Currency currency, final BigDecimal tax) {
        this.amount = amount;
        this.currency = currency;
        this.tax = tax;
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

    public static PriceEmbeddable fromPrice(final Price price) {
        return new PriceEmbeddable(price.getAmount(), price.getCurrency(), price.getTax());
    }

    public Price toPrice() {
        return new Price(amount, currency, tax);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (PriceEmbeddable) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(tax, that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency, tax);
    }

    @Override
    public String toString() {
        return "PriceEmbeddable{" +
                "amount=" + amount +
                ", currency=" + currency +
                ", tax=" + tax +
                '}';
    }
}
