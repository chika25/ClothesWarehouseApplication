package com.cpan252.clotheswarehouseproject.controller;

import com.cpan252.clotheswarehouseproject.repository.DistributionCenterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/distributionCenterList")
public class DistributionListController {
    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @GetMapping
    public String DistributionCenterList() {
        return "distributionList";
    }

    @ModelAttribute
    public void items(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        var allCenters = distributionCenterRepository.findAll();

        model.addAttribute("username", username);
        model.addAttribute("allCenters", allCenters);
    }
}
