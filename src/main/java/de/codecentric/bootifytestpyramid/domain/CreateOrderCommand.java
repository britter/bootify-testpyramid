package de.codecentric.bootifytestpyramid.domain;

import java.util.List;

public class CreateOrderCommand {

    public Address deliveryAddress;

    public List<CreateOrderItem> items;

    public static class CreateOrderItem {

        public String name;

        public String description;

        public Weight weight;

        public Price price;

        public int quantity;
    }
}
