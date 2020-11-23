package com.nc.example.service.impl;

import com.nc.example.model.Owner;
import com.nc.example.repo.OwnerRepository;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService, UserDetailsService {

    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return ownerRepository.findByUsername(name);
    }
}
