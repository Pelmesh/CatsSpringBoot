package com.nc.example.controller;

import com.nc.example.model.Owner;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    @GetMapping
    public String getAllOwners(Model model) {
        model.addAttribute("ownerList", ownerService.findAll());
        return "owner/ownerList";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        return "owner/ownerCreate";
    }

    @PostMapping("/create")
    public String createOwner(Owner owner, Model model) {
        Owner ownerRepeat = ownerService.findByName(owner.getUsername());
        if (ownerRepeat != null) {
            model.addAttribute("message", "User exist");
            return "owner/ownerCreate";
        }
        ownerService.create(owner);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getOwner(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owner/ownerInfo";
    }

}
