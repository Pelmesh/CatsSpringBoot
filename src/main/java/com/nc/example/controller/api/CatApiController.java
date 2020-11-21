package com.nc.example.controller.api;

import com.nc.example.model.Cat;
import com.nc.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cats")
public class CatApiController {

    @Autowired
    CatService catService;

    @GetMapping
    public List<Cat> getAll() {
        return catService.findAll();
    }

    @GetMapping("/{id}")
    public Object getCat(@PathVariable Long id) {
        try {
            catService.findById(id).get();
        } catch (NoSuchElementException e) {
            return HttpStatus.NOT_FOUND;
        }
        return catService.findById(id).get();
    }

    @PostMapping("/create")
    public Object createCat(@RequestBody Cat cat) {
        if (cat.getId() != null || !checkParent(cat)) return HttpStatus.BAD_REQUEST;
        return catService.create(cat);
    }

    private boolean checkParent(Cat cat) {
        if (cat.getCatFather() != null) {
            Cat catF;
            try {
                catF = catService.findById(cat.getCatFather().getId()).get();
            } catch (NoSuchElementException e) {
                return false;
            }
            if (!catF.getGender().equals("M")) {
                return false;
            }
        }
        if (cat.getCatMother() != null) {
            Cat catM;
            try {
                catM = catService.findById(cat.getCatMother().getId()).get();
            } catch (NoSuchElementException e) {
                return false;
            }
            if (!catM.getGender().equals("F")) {
                return false;
            }
        }
        return true;
    }

    @PutMapping("/create")
    public Object updateCat(@RequestBody Cat cat) {
        if (cat.getId() == null) return HttpStatus.BAD_REQUEST;
        try {
            catService.findById(cat.getId()).get();
        } catch (NoSuchElementException e) {
            return HttpStatus.NOT_FOUND;
        }
        return catService.create(cat);
    }

    @DeleteMapping("/{id}")
    public Object deleteCat(@PathVariable Long id) {
        try {
            catService.findById(id).get();
        } catch (NoSuchElementException e) {
            return HttpStatus.BAD_REQUEST;
        }
        List<Cat> catL = catService.findChildren(id, id);
        if (catL != null) {
            for (Cat value : catL) {
                if (value.getCatFather() != null && value.getCatFather().getId().equals(id)) {
                    value.setCatFather(null);
                }
                if (value.getCatMother() != null && value.getCatMother().getId().equals(id)) {
                    value.setCatMother(null);
                }
                catService.create(value);
            }
        }
        catService.deleteById(id);
        return HttpStatus.OK;
    }
}
