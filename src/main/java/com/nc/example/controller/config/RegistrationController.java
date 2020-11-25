package com.nc.example.controller.config;

import com.nc.example.controller.CatController;
import com.nc.example.model.Owner;
import com.nc.example.model.Role;
import com.nc.example.service.OwnerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(CatController.class);

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getRegistration(Model model) {
        return "registration";
    }

    @PostMapping
    public String createUser(@Valid Owner user, BindingResult bindingResult, Model model) {
        Owner ownerRepeat = ownerService.findByName(user.getUsername());
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("owner", user);
            return "registration";
        }
        if (ownerRepeat != null) {
            model.addAttribute("message", "User exist!");
            model.addAttribute("owner", user);
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ownerService.create(user);
        LOGGER.info("User created id:"+user.getId());
        return "redirect:/login";
    }

    private Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }


}
