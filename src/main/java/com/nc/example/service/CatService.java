package com.nc.example.service;

import com.nc.example.model.Cat;

import java.util.List;

public interface CatService {

    Cat findById(Long id);

    Cat create(Cat cat);

    List<Cat> findAll();

}
