package com.jjbeto.microservice.iplocator.service;

import com.jjbeto.microservice.iplocator.client.IpApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IpApiServiceITCase {

    @Autowired
    IpApiService ipApiService;

    @Value("${feign.client.ip-api.fields}")
    String fields;

    @Value("${feign.client.ip-api.fallback.currency}")
    String fallbackCurrency;

    @Mock
    IpApiClient mockedIpApiClient; // test unavailability

    @Test
    void findCanadianDollarForCanadianIP() {
        final String ip = "24.48.0.1";
        String currency = ipApiService.getCurrency(ip);
        assertEquals("CAD", currency);
    }

    @Test
    void findEuroForGermanIP() {
        final String ip = "18.184.45.226";
        String currency = ipApiService.getCurrency(ip);
        assertEquals("EUR", currency);
    }

    @Test
    void returnEuroAsDefaultIfIpNotFound() {
        final String ip = "0.0.0.0";
        String currency = ipApiService.getCurrency(ip);
        assertEquals("EUR", currency);
    }

    @Test
    void returnFallbackIfInvalidDataIsGiven() {
        final String ip = "hallo!";
        String currency = ipApiService.getCurrency(ip);
        assertEquals("EUR", currency);
    }

    @Test
    void returnFallbackIfTargetServiceIsUnavailable() {
        final String ip = "18.184.45.226";

        IpApiService mockedService = new IpApiService(mockedIpApiClient, fields, fallbackCurrency);
        when(mockedIpApiClient.getFromJsonByIp(ip, fields)).thenReturn(null);

        String currency = mockedService.getCurrency(ip);
        assertEquals("EUR", currency);
    }

}
