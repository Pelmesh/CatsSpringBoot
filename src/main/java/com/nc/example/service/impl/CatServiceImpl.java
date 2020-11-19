package com.nc.example.service.impl;

import com.nc.example.model.Cat;
import com.nc.example.repo.CatRepository;
import com.nc.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatRepository catRepo;

    @Override
    public Cat findById(Long id) {
        return catRepo.getOne(id);
    }

    @Override
    public Cat create(Cat cat) {
        return catRepo.save(cat);
    }

    @Override
    public List<Cat> findAll() {
        return catRepo.findAll();
    }
}
