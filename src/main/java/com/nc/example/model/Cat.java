package com.nc.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @OneToOne()
    @JoinColumn(name = "father_id")
    private Cat catFather;

    @OneToOne()
    @JoinColumn(name = "mother_id")
    private Cat catMother;

    @ManyToOne()
    @JoinColumn(name = "user_id")
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
