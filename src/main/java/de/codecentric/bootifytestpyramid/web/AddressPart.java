package de.codecentric.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.codecentric.bootifytestpyramid.domain.Address;

import javax.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
final class AddressPart {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String street;

    @NotNull
    private String zip;

    @NotNull
    private String city;

    AddressPart() {
    }

    AddressPart(Address address) {
        firstName = address.getFirstName();
        lastName = address.getLastName();
        street = address.getStreet();
        zip = address.getZip();
        city = address.getCity();
    }

    Address toAddress() {
        return new Address(firstName, lastName, street, zip, city);
    }

}
