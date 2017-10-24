package de.codecentric.bootifytestpyramid.domain;

import static de.codecentric.bootifytestpyramid.domain.Price.germanPrice;

public final class OrderItemTemplates {

    private OrderItemTemplates() {
    }

    public static OrderItem couch() {
        return couches(1);
    }

    public static OrderItem couches(int quanity) {
        return new OrderItem(1, "Couch", "A cozzy couch", new Weight(35), germanPrice(289.99), quanity);
    }

    public static OrderItem closet() {
        return couches(1);
    }

    public static OrderItem closets(int quanity) {
        return new OrderItem(1, "Closet", "A closet to put your stuff into", new Weight(300), germanPrice(499.99), quanity);
    }

    public static OrderItem table() {
        return tables(1);
    }

    public static OrderItem tables(int quantity) {
        return new OrderItem(1, "Table", "A nice white kitchen table", new Weight(20), germanPrice(79.99), quantity);
    }

    public static OrderItem bed() {
        return beds(1);
    }

    public static OrderItem beds(int quantity) {
        return new OrderItem(1, "Bed", "A king sized bed", new Weight(200), germanPrice(499.99), quantity);
    }

    public static OrderItem chair() {
        return chairs(1);
    }

    public static OrderItem chairs(int quantity) {
        return new OrderItem(1, "Chair", "A kitchen chair", new Weight(10), germanPrice(29.99), quantity);
    }


}
