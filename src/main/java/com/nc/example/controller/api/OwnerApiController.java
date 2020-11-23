package com.nc.example.controller.api;

import com.nc.example.model.Owner;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerApiController {

    @Autowired
    OwnerService ownerService;

    @GetMapping
    public List<Owner> getAll() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public Object getAll(@PathVariable Long id) {
        Optional<Owner> owner = ownerService.findById(id);
        if (owner.isPresent()) return owner.get();
        return HttpStatus.NOT_FOUND;
    }

}
