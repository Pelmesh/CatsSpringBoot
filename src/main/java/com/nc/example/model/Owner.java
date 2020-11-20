package com.nc.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class Owner{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Pattern(regexp = "^[А-ЯА-яA-Za-z0-9]+$", message = "Example name: User, User20")
    @Size(min = 1, message = "Short name, min 1")
    @Size(max = 15, message = "Long name, max 15")
    @Column(name = "username")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Cat> getCats() {
        return cats;
    }

    public void setCats(Set<Cat> cats) {
        this.cats = cats;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<Cat> cats;

}
