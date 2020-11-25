package com.nc.example.service;

import com.nc.example.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CatService {

    Optional<Cat> findById(Long id);

    Cat create(Cat cat);

    List<Cat> findAll();

    Page<Cat> findAll(Pageable pageable);

    List<Cat> findAllByGender(String gender);

    List<Cat> findChildren(Long fatherId, Long motherId);

    void deleteById(Long id);

}
