package com.jjbeto.microservice.iplocator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IpLocatorControllerITCase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findCanadianDollarForCanadianIP() throws Exception {
        final String ip = "24.48.0.1";
        mockMvc.perform(get("/api/ip-locator/currency/" + ip))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("CAD")));
    }

    @Test
    void findEuroForGermanIP() throws Exception {
        final String ip = "18.184.45.226";
        mockMvc.perform(get("/api/ip-locator/currency/" + ip))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("EUR")));
    }

    @Test
    void returnEuroAsDefaultIfIpNotFound() throws Exception {
        final String ip = "0.0.0.0";
        mockMvc.perform(get("/api/ip-locator/currency/" + ip))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("EUR")));
    }

    @Test
    void returnErrorIfInvalidDataIsGiven() throws Exception {
        final String ip = "hallo!";
        mockMvc.perform(get("/api/ip-locator/currency/" + ip))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
