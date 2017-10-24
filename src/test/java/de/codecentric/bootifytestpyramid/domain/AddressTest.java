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
