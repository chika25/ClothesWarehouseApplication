package com.cpan252.distributioncentremanagersapi.controller;

import com.cpan252.distributioncentremanagersapi.model.dto.DistributionCenterDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Controller
@RequestMapping("/distributioncenterboard")
@CrossOrigin(origins = "http://localhost:8081")
public class GetDistributionAPIController {
    private RestTemplate restTemplate;

    public GetDistributionAPIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String dashboard() {
        return "distributioncenterboard";
    }

    @ModelAttribute("centers")
    public List<DistributionCenterDto> getUsers() {
        var centers = restTemplate.getForObject("http://localhost:8081/api/distributioncenters", DistributionCenterDto[].class);
        return Arrays.asList(centers);

    }
}
