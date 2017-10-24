package de.codecentric.bootifytestpyramid.domain;

import java.util.Objects;

public final class Address {

    private final String firstName;

    private final String lastName;

    private final String street;

    private final String zip;

    private final String city;

    public Address(final String firstName, final String lastName, final String street, final String zip, final String city) {
        this.firstName = Objects.requireNonNull(firstName, "Parameter 'firstName' must not be null");
        this.lastName = Objects.requireNonNull(lastName, "Parameter 'lastName' must not be null");
        this.street = Objects.requireNonNull(street, "Parameter 'street' must not be null");
        this.zip = Objects.requireNonNull(zip, "Parameter 'zip' must not be null");
        this.city = Objects.requireNonNull(city, "Parameter 'city' must not be null");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Address address = (Address) o;
        return Objects.equals(firstName, address.firstName) &&
                Objects.equals(lastName, address.lastName) &&
                Objects.equals(street, address.street) &&
                Objects.equals(zip, address.zip) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, street, zip, city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
