package com.nc.example.service;

import com.nc.example.model.Cat;

import java.util.List;
import java.util.Optional;

public interface CatService {

    Optional<Cat> findById(Long id);

    Cat create(Cat cat);

    List<Cat> findAll();

    List<Cat> findAllByGender(String gender);

    List<Cat> findChildren(Long fatherId, Long motherId);

    void deleteById(Long id);

}
