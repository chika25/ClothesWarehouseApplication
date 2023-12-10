package com.cpan252.clotheswarehouseproject.controller;


import com.cpan252.clotheswarehouseproject.model.DistributionCenter;
import com.cpan252.clotheswarehouseproject.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cpan252.clotheswarehouseproject.repository.ItemRepository;
import com.cpan252.clotheswarehouseproject.model.Item;
import com.cpan252.clotheswarehouseproject.repository.DistributionCenterRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cpan252.clotheswarehouseproject.service.DistributionCenterService;
import org.thymeleaf.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/itemList")
public class ItemListController {
    private static final int PAGE_SIZE = 5;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @Autowired
    private DistributionCenterService distributionCenterService;

    public ItemListController(ItemRepository itemRepository, DistributionCenterRepository distributionCenterRepository) {
        this.itemRepository = itemRepository;
        this.distributionCenterRepository =distributionCenterRepository;
    }

    @GetMapping
    public String itemlist() {
        return "itemList";
    }

    @ModelAttribute
    public void items(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        var itemPage = itemRepository.findAll(PageRequest.of(0, PAGE_SIZE));
        var distributionCenters = distributionCenterRepository.findAll();
        var allItems = itemRepository.findAll();

        Set<String> processedBrands = new HashSet<>();

        // Filter out duplicate brands
        List<Item> uniqueBrands = allItems.stream()
                .filter(item -> item.getBrand() != null && processedBrands.add(item.getBrand()))
                .collect(Collectors.toList());


        model.addAttribute("allItems", allItems);
        model.addAttribute("uniqueBrands", uniqueBrands);
        model.addAttribute("distributionCenters", distributionCenters);
        model.addAttribute("username", username);
        model.addAttribute("items", itemPage);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }
    @PostMapping("/deleteAllItems")
    @PreAuthorize("hasRole('ADMIN')")
    public String processAllItemsDeletion(@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        itemRepository.deleteAll();
        redirectAttributes.addFlashAttribute("deletionCompleted", true);
        return "redirect:/itemList";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
                             @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);

        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "itemList";
        }
        var itemPage = itemRepository.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        return "itemList";

    }

    @PostMapping("/addDistributionCenter")
    @PreAuthorize("hasRole('ADMIN')")
    public String addDistributionCenter(@ModelAttribute("name") String name,
                                        @ModelAttribute("brand") String brand,
                                        @ModelAttribute("distributionName") String distributionName,
                                        @AuthenticationPrincipal User user) {
        List<Item> itemsToAdd;
        // Check if both name and brand are empty
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(brand)) {
            return "redirect:/itemList";
        } else {
            // Use your repository method to search by name and/or brand
            if (StringUtils.isEmpty(name)) {
                // Only brand is chosen
                itemsToAdd = itemRepository.findByBrand(brand);
            } else if (StringUtils.isEmpty(brand)) {
                // Only name is chosen
                itemsToAdd = itemRepository.findByName(name);
            } else {
                // Both name and brand are chosen
                itemsToAdd = itemRepository.findByNameAndBrand(name, brand);
            }
        }
        DistributionCenter centerId = distributionCenterRepository.findByName(distributionName);
        // Add items to the specified distribution center
        distributionCenterService.addItemToDistributionCenter(centerId.getId(), itemsToAdd);
        return "redirect:/itemList";
    }

    @PostMapping("/deleteItems")
    @PreAuthorize("hasRole('ADMIN')")
    public String processItemsDeletion(@ModelAttribute("name") String name,
                                       @ModelAttribute("brand") String brand,
                                       @AuthenticationPrincipal User user) {
        List<Item> itemsToDelete;
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(brand)) {
            return "redirect:/itemList";
        } else {
            // Use your repository method to search by name and/or brand
            if (StringUtils.isEmpty(name)) {
                // Only brand is chosen
                itemsToDelete = itemRepository.findByBrand(brand);
            } else if (StringUtils.isEmpty(brand)) {
                // Only name is chosen
                itemsToDelete = itemRepository.findByName(name);
            } else {
                // Both name and brand are chosen
                itemsToDelete = itemRepository.findByNameAndBrand(name, brand);
            }
        }
        itemRepository.deleteAll(itemsToDelete);
        return "redirect:/itemList";
    }

}
