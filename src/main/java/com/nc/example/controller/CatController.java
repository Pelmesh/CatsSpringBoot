package com.nc.example.controller;

import com.nc.example.model.Cat;
import com.nc.example.service.CatService;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CatController {

    @Autowired
    CatService catService;

    @Autowired
    OwnerService ownerService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("catList", catService.findAll());
        return "cat/catList";
    }

    @GetMapping("/cat/create")
    public String getCreate(Model model) {
        model.addAttribute("ownerList", ownerService.findAll());
        model.addAttribute("fatherList", catService.findAllByGender("M"));
        model.addAttribute("motherList", catService.findAllByGender("F"));
        return "cat/catCreate";
    }

    @PostMapping("/cat/create")
    public String createCat(Cat cat, Model model) {
        catService.create(cat);
        model.addAttribute("message", "Cat created");
        return "success";
    }

    @GetMapping("/cat/{id}")
    public String getCat(@PathVariable Long id, Model model) {
        Optional<Cat> cat = catService.findById(id);
        if (cat.isPresent()) {
            model.addAttribute("cat", cat.get());
            model.addAttribute("children", catService.findChildren(id, id));
        }
        return "cat/catInfo";
    }

    @DeleteMapping("/cat/{id}")
    public String deleteCat(@PathVariable Long id, Model model) {
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
        model.addAttribute("message", "Cat removed");
        return "success";
    }

    @GetMapping("/cat/create/{id}")
    public String getCatForCreate(@PathVariable Long id, Model model) {
        model.addAttribute("ownerList", ownerService.findAll());
        model.addAttribute("fatherList", catService.findAllByGender("M"));
        model.addAttribute("motherList", catService.findAllByGender("F"));
        model.addAttribute("cat", catService.findById(id));
        return "cat/catCreate";
    }

    @PutMapping("/cat/create/{id}")
    public String updateCat(Cat cat, Model model) {
        catService.create(cat);
        model.addAttribute("message", "Cat changed");
        return "success";
    }

}
