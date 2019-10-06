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
package com.github.britter.bootifytestpyramid.persistence;

import com.github.britter.bootifytestpyramid.domain.Address;
import com.github.britter.bootifytestpyramid.domain.AddressTemplates;

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
