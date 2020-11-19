package com.nc.example.service;

import com.nc.example.model.Owner;

import java.util.List;

public interface OwnerService {

    Owner findById(Long id);

    Owner create(Owner cat);

    List<Owner> findAll();

}
