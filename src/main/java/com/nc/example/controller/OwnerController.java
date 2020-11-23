package com.nc.example.controller;

import com.nc.example.model.Owner;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String getAllOwners(Model model) {
        model.addAttribute("ownerList", ownerService.findAll());
        return "owner/ownerList";
    }

    @GetMapping("/{id}")
    public String getOwner(@PathVariable Long id, Model model) {
        Optional<Owner> owner = ownerService.findById(id);
        if (owner.isPresent()) {
            model.addAttribute("owner", owner.get());
            return "owner/ownerInfo";
        }
        return "404";
    }

}
