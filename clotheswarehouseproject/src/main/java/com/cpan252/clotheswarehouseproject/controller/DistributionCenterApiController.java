package com.cpan252.clotheswarehouseproject.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cpan252.clotheswarehouseproject.repository.DistributionCenterRepository;
import com.cpan252.clotheswarehouseproject.model.DistributionCenter;
import java.util.List;

@RestController
@RequestMapping(path = "/api/distributioncenters", produces = "application/json")
public class DistributionCenterApiController {
    private final DistributionCenterRepository distributionRepository;

    public DistributionCenterApiController (DistributionCenterRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }

    @GetMapping
    public List<DistributionCenter> getDistributionCenters() {
        return distributionRepository.findAll();
    }



}
