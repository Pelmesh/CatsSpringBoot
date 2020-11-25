package com.nc.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[А-ЯА-яA-Za-z0-9]+$", message = "Example name: Boris, Kitty20")
    @Size(min = 1, message = "Short name, min 1")
    @Size(max = 15, message = "Long name, max 15")
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^[А-Яа-яA-Za-z]*$", message = "Example color: Black, White")
    @Size(max = 15, message = "Long color, max 15")
    @Column(name = "color")
    private String color;

    @Max(value = 25, message = "Max 25")
    @Min(value = 0, message = "Min 0")
    @Column(name = "age")
    private Integer age;

    @Pattern(regexp = "M|F|(^$)", message = "Select gender")
    @Column(name = "gender")
    private String gender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "father_id")
    private Cat catFather;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mother_id")
    private Cat catMother;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("owner")
    private Owner owner;

    public Cat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Cat getCatFather() {
        return catFather;
    }

    public void setCatFather(Cat catFather) {
        this.catFather = catFather;
    }

    public Cat getCatMother() {
        return catMother;
    }

    public void setCatMother(Cat catMother) {
        this.catMother = catMother;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
