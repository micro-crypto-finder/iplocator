package com.jjbeto.microservice.iplocator.service;

import com.jjbeto.microservice.iplocator.client.IpApiClient;
import com.jjbeto.microservice.iplocator.dto.IpApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IpApiService {

    private final IpApiClient ipApiClient;

    private final String fields;

    private final String fallbackCurrency;

    public IpApiService(
            final IpApiClient ipApiClient,
            @Value("${feign.client.ip-api.fields}") final String fields,
            @Value("${feign.client.ip-api.fallback.currency}") final String fallbackCurrency
    ) {
        this.ipApiClient = ipApiClient;
        this.fields = fields;
        this.fallbackCurrency = fallbackCurrency;
    }

    public String getCurrency(final String ip) {
        final IpApiResponseDto dto = ipApiClient.getFromJsonByIp(ip, fields);
        if (dto == null || dto.getCurrency() == null) {
            return fallbackCurrency;
        }
        return dto.getCurrency();
    }

}
