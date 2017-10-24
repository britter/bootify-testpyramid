package de.codecentric.bootifytestpyramid.domain;

public final class AddressTemplates {

    private AddressTemplates() {
    }

    public static Address johnDoe() {
        return new Address("John", "Doe", "Mainstreet 15", "64782", "Newton");
    }

}
