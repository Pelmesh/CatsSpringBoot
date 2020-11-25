package com.nc.example.controller.api;

import com.nc.example.model.Cat;
import com.nc.example.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/cats")
public class CatApiController {

    @Autowired
    CatService catService;

    @GetMapping
    public Object getAll(@PageableDefault(sort = {"id"}, size = 20, direction = Sort.Direction.ASC) Pageable pageable) {
        return catService.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public Object getCat(@PathVariable Long id) {
        Optional<Cat> cat = catService.findById(id);
        if (cat.isPresent()) return cat.get();
        return HttpStatus.NOT_FOUND;
    }

    @PostMapping
    public Object createCat(@RequestBody Cat cat) {
        if (cat.getId() != null || !checkParent(cat)) return HttpStatus.BAD_REQUEST;
        catService.create(cat);
        if (cat.getCatMother() != null) {
            cat.setCatMother(catService.findById(cat.getCatMother().getId()).get());
        }
        if (cat.getCatFather() != null) {
            cat.setCatFather(catService.findById(cat.getCatFather().getId()).get());
        }
        return cat;
    }

    private boolean checkParent(Cat cat) {
        if (cat.getCatFather() != null) {
            Cat catF;
            try {
                catF = catService.findById(cat.getCatFather().getId()).get();
            } catch (NoSuchElementException e) {
                return false;
            }
            if (!catF.getGender().equals("M")) return false;
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

    @PutMapping
    public Object updateCat(@RequestBody Cat cat) {
        if (cat.getId() == null) return HttpStatus.BAD_REQUEST;
        Optional<Cat> catO = catService.findById(cat.getId());
        if (catO.isPresent()) return catService.create(cat);
        return HttpStatus.NOT_FOUND;
    }

    @DeleteMapping("/{id}")
    public Object deleteCat(@PathVariable Long id) {
        Optional<Cat> cat = catService.findById(id);
        if (!cat.isPresent()) return HttpStatus.NOT_FOUND;
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
