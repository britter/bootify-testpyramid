package de.codecentric.bootifytestpyramid.persistence;

import de.codecentric.bootifytestpyramid.domain.Address;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AddressEmbeddable {

    private String firstName;

    private String lastName;

    private String street;

    private String zip;

    private String city;

    public AddressEmbeddable() {
    }

    public AddressEmbeddable(final String firstName, final String lastName, final String street, final String zip, final String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    static AddressEmbeddable fromAddress(final Address deliveryAddress) {
        return new AddressEmbeddable(
                deliveryAddress.getFirstName(),
                deliveryAddress.getLastName(),
                deliveryAddress.getStreet(),
                deliveryAddress.getZip(),
                deliveryAddress.getCity());
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

    public Address toAddress() {
        return new Address(firstName, lastName, street, zip, city);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AddressEmbeddable that = (AddressEmbeddable) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(street, that.street) &&
                Objects.equals(zip, that.zip) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, street, zip, city);
    }

    @Override
    public String toString() {
        return "AddressEmbeddable{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
