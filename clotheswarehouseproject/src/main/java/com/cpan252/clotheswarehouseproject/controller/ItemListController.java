package com.cpan252.clotheswarehouseproject.controller;


import com.cpan252.clotheswarehouseproject.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cpan252.clotheswarehouseproject.repository.ItemRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/itemList")
public class ItemListController {
    private static final int PAGE_SIZE = 5;
    @Autowired
    private ItemRepository itemRepository;

    public ItemListController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

        model.addAttribute("username", username);
        model.addAttribute("items", itemPage);
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
    }
    @PostMapping("/deleteAllItems")
    @PreAuthorize("hasRole('ADMIN')")
    public String processFightersDeletion(@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        itemRepository.deleteAll();
        redirectAttributes.addFlashAttribute("deletionCompleted", true);
        return "redirect:/itemList";
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
                             @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
//        var currentPath = request.getRequestURL().toString();

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
}
