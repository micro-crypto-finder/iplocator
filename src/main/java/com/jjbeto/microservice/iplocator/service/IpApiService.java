package com.jjbeto.microservice.iplocator.service;

import com.jjbeto.microservice.iplocator.client.IpApiClient;
import com.jjbeto.microservice.iplocator.dto.IpApiResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IpApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpApiService.class);

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

    @Cacheable(value = "IpApiService.getCurrency")
    public String getCurrency(final String ip) {
        final IpApiResponseDto dto = ipApiClient.getFromJsonByIp(ip, fields);
        if (dto == null || dto.getCurrency() == null) {
            LOGGER.warn("Ip '{}' not found, currency: '{}' returned (fallback)", ip, fallbackCurrency);
            return fallbackCurrency;
        }
        LOGGER.info("Ip '{}' found, currency: '{}'", ip, dto.getCurrency());
        return dto.getCurrency();
    }

}
