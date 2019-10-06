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
package com.github.britter.bootifytestpyramid.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.britter.bootifytestpyramid.domain.Address;

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
