package com.nc.example.controller;

import com.nc.example.model.Cat;
import com.nc.example.model.Owner;
import com.nc.example.service.CatService;
import com.nc.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class CatController {

    @Autowired
    private CatService catService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("catList", catService.findAll());
        return "cat/catList";
    }

    @GetMapping("/cat/create")
    public String getCreate(Model model) {
        setModel(model);
        return "cat/catCreate";
    }

    @PostMapping("/cat/create")
    public String createCat(@Valid Cat cat, BindingResult bindingResult, Model model) {
        if (!checkErrors(cat, bindingResult, model)) {
            return "cat/catCreate";
        }
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
            return "cat/catInfo";
        }
        return "404";
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
        setModel(model);
        Optional<Cat> cat = catService.findById(id);
        if (cat.isPresent()) {
            model.addAttribute("cat", cat.get());
            return "cat/catCreate";
        }
        return "404";
    }

    @PutMapping("/cat/create/{id}")
    public String updateCat(@Valid Cat cat, BindingResult bindingResult, Model model) {
        if (!checkErrors(cat, bindingResult, model)) {
            return "cat/catCreate";
        }
        catService.create(cat);
        model.addAttribute("message", "Cat changed");
        return "success";
    }

    private Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }

    private void setModel(Model model) {
        model.addAttribute("ownerList", ownerService.findAll());
        model.addAttribute("fatherList", catService.findAllByGender("M"));
        model.addAttribute("motherList", catService.findAllByGender("F"));
    }

    private boolean checkErrors(Cat cat, BindingResult bindingResult, Model model) {
        model.addAttribute("cat", cat);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            setModel(model);
            return false;
        }
        if (!checkParent(cat)) {
            model.addAttribute("message", "Check parent");
            setModel(model);
            return false;
        }
        return true;
    }

    private boolean checkParent(Cat cat) {
        if (cat.getCatFather() != null) {
            if (!cat.getCatFather().getGender().equals("M")) {
                return false;
            }
        }
        if (cat.getCatMother() != null) {
            if (!cat.getCatMother().getGender().equals("F")) {
                return false;
            }
        }
        return true;
    }

}
