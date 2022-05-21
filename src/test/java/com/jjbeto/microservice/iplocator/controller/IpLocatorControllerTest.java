package com.jjbeto.microservice.iplocator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void validListOfIpv4() {
        List<String> listOfIps = List.of(
                "0.0.0.0",
                "129.0.0.0",
                "169.253.255.255",
                "192.88.100.0",
                "192.167.255.255",
                "192.169.0.0",
                "223.255.255.255"
        );

        for (String ip : listOfIps) {
            assertTrue(ip.matches(IpLocatorController.VALID_IP), "IP '%s' is invalid".formatted(ip));
        }
    }

    @Test
    void invalidListOfIpv4() {
        List<String> listOfIps = List.of(
                "256.0.0.0",
                "129.0.0.256",
                "",
                "Ulala",
                "Germany"
        );

        for (String ip : listOfIps) {
            assertFalse(ip.matches(IpLocatorController.VALID_IP), "IP '%s' is valid".formatted(ip));
        }
    }

    @Test
    void validListOfIpv6() {
        List<String> listOfIps = List.of(
                "1200:0000:AB00:1234:0000:2552:7777:1313",
                "21DA:D3:0:2F3B:2AA:FF:FE28:9C5A",
                "FE80:0000:0000:0000:0202:B3FF:FE1E:8329"
        );

        for (String ip : listOfIps) {
            assertTrue(ip.matches(IpLocatorController.VALID_IP), "IP '%s' is invalid".formatted(ip));
        }
    }

    @Test
    void invalidListOfIpv6() {
        List<String> listOfIps = List.of(
                "1200:0000:AB00:1234:O000:2552:7777:1313",
                "[2001:db8:0:1]:80",
                "http://[2001:db8:0:1]:80"
        );

        for (String ip : listOfIps) {
            assertFalse(ip.matches(IpLocatorController.VALID_IP), "IP '%s' is valid".formatted(ip));
        }
    }

}