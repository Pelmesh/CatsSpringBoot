package com.nc.example.service;

import com.nc.example.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {

    Optional<Owner> findById(Long id);

    Owner create(Owner cat);

    List<Owner> findAll();

    Owner findByName(String name);

}
