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
        var that = (AddressEmbeddable) o;
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
