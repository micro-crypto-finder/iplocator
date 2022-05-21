package com.jjbeto.microservice.iplocator.dto;

public class IpApiResponseDto {

    private String status;

    private String message;

    private String countryCode;

    private String currency;

    public IpApiResponseDto() {
    }

    public IpApiResponseDto(String status, String message, String countryCode, String currency) {
        this.status = status;
        this.message = message;
        this.countryCode = countryCode;
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

