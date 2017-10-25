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
package de.codecentric.bootifytestpyramid.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    @Test
    void should_throw_exception_when_passing_null_firstName() {
        assertThrows(NullPointerException.class, () -> new Address(null, "lastName", "street", "zip", "city"));
    }

    @Test
    void should_throw_exception_when_passing_null_lastName() {
        assertThrows(NullPointerException.class, () -> new Address("firstName", null, "street", "zip", "city"));
    }

    @Test
    void should_throw_exception_when_passing_null_street() {
        assertThrows(NullPointerException.class, () -> new Address("firstName", "lastName", null, "zip", "city"));
    }

    @Test
    void should_throw_exception_when_passing_null_zip() {
        assertThrows(NullPointerException.class, () -> new Address("firstName", "lastName", "street", null, "city"));
    }

    @Test
    void should_throw_exception_when_passing_null_city() {
        assertThrows(NullPointerException.class, () -> new Address("firstName", "lastName", "street", "zip", null));
    }
}
