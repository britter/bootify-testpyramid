package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.Address;
import de.codecentric.bootifytestpyramid.domain.AddressTemplates;

public final class AddressEmbeddableBuilder {

    private String firstName;

    private String lastName;

    private String street;

    private String zip;

    private String city;

    private AddressEmbeddableBuilder() {
    }

    public static AddressEmbeddableBuilder empty() {
        return new AddressEmbeddableBuilder();
    }

    public static AddressEmbeddableBuilder johnDoe() {
        Address johnDoe = AddressTemplates.johnDoe();
        return empty()
                .withFirstName(johnDoe.getFirstName())
                .withLastName(johnDoe.getLastName())
                .withStreet(johnDoe.getStreet())
                .withCity(johnDoe.getCity())
                .withZip(johnDoe.getZip());
    }

    public AddressEmbeddableBuilder withFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddressEmbeddableBuilder withLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddressEmbeddableBuilder withStreet(final String street) {
        this.street = street;
        return this;
    }

    public AddressEmbeddableBuilder withZip(final String zip) {
        this.zip = zip;
        return this;
    }

    public AddressEmbeddableBuilder withCity(final String city) {
        this.city = city;
        return this;
    }

    public AddressEmbeddable build() {
        return new AddressEmbeddable(firstName, lastName, street, zip, city);
    }
}
