package com.jjbeto.microservice.iplocator.client;

import com.jjbeto.microservice.iplocator.dto.IpApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "${feign.client.ip-api.name}", url = "${feign.client.ip-api.url}")
public interface IpApiClient {

    @RequestMapping(method = GET, path = "/json/{ip}")
    IpApiResponseDto getFromJsonByIp(@PathVariable(value = "ip") String ip, @RequestParam("fields") String fields);

}
