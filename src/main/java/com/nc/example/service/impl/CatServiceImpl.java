package com.nc.example.service.impl;

import com.nc.example.model.Cat;
import com.nc.example.repo.CatRepository;
import com.nc.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    CatRepository catRepo;

    @Override
    public Optional<Cat> findById(Long id) {
        return catRepo.findById(id);
    }

    @Override
    public Cat create(Cat cat) {
        return catRepo.save(cat);
    }

    @Override
    public List<Cat> findAll() {
        return catRepo.findAll();
    }

    @Override
    public List<Cat> findAllByGender(String gender) {
        return catRepo.findAllByGender(gender);
    }

    @Override
    public List<Cat> findChildren(Long fatherId, Long motherId) {
        return catRepo.findAllByCatFatherIdOrCatMotherId(fatherId, motherId);
    }

    @Override
    public void deleteById(Long id) {
        catRepo.deleteById(id);
    }
}
