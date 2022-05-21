package com.jjbeto.microservice.iplocator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ip-locator")
public class IpLocatorController {

    @GetMapping("/currency/{ip}")
    public String getCurrency(@PathVariable String ip) {
        return ip;
    }

}
