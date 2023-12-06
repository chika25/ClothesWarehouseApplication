package com.cpan252.distributioncentremanagersapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cpan252.distributioncentremanagersapi.repository.ManagerRepository;
import com.cpan252.distributioncentremanagersapi.model.ManagerModel;
@RestController
@RequestMapping(path = "/api/managers", produces = "application/json")
public class ManagerController {
    private final ManagerRepository managerRepository;

    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping
    public Iterable<ManagerModel> getUsers() {
        return managerRepository.findAll();
    }
}
