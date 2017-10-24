package de.codecentric.bootifytestpyramid.domain;

import java.util.Objects;

public final class OrderItem {

    private long id;

    private String name;

    private String description;

    private Weight weight;

    private Price price;

    private int quantity;

    public OrderItem(final long id, final String name, final String description, final Weight weight, final Price price, final int quantity) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Parameter 'name' must not be null");
        this.description = Objects.requireNonNull(description, "Parameter 'description' must not be null");
        this.weight = Objects.requireNonNull(weight, "Parameter 'weight' must not be null");
        this.price = Objects.requireNonNull(price, "Parameter 'price' must not be null");
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Weight getWeight() {
        return weight;
    }

    public Price getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Weight getTotalWeight() {
        return weight.multiply(quantity);
    }

    public Price getTotalPrice() {
        return price.multiply(quantity);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
                quantity == orderItem.quantity &&
                Objects.equals(name, orderItem.name) &&
                Objects.equals(description, orderItem.description) &&
                Objects.equals(weight, orderItem.weight) &&
                Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, weight, price, quantity);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
