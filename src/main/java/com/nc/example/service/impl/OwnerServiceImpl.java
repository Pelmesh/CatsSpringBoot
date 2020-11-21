package com.nc.example.service.impl;

import com.nc.example.model.Owner;
import com.nc.example.repo.OwnerRepository;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).get();
    }

    @Override
    public Owner create(Owner cat) {
        return ownerRepository.save(cat);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findByName(String name) {
        return ownerRepository.findByUsername(name);
    }
}
