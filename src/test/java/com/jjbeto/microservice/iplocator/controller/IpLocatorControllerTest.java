package com.jjbeto.microservice.iplocator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class IpLocatorControllerTest {

    @Autowired
    IpLocatorController controller;

    @Test
    void findCanadianDollarForCanadianIP() {
        String currency = controller.getCurrency("24.48.0.1"); // ip from Canada
        assertEquals("CAD", currency);
    }

    @Test
    void findEuroForGermanIP() {
        String currency = controller.getCurrency("18.184.45.226"); // ip from Germany
        assertEquals("EUR", currency);
    }

    @Test
    void returnEuroAsDefaultIfIpNotFound() {
        String currency = controller.getCurrency("0.0.0.0"); // 0.0.0.0 is reserved
        assertEquals("EUR", currency);
    }

    @Test
    void returnErrorIfInvalidDataIsGiven() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> controller.getCurrency("hallo!"));
        assertEquals("Input 'hallo!' is not a valid IP", thrown.getMessage());
    }

}
