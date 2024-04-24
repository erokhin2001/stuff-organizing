package com.art.stuff.domain;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Type {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long typeId;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @OneToMany(
        cascade = {CascadeType.PERSIST},
        mappedBy = "type"
    )
    private List<Item> items = new ArrayList<>();

    public Type() {}
    public Type(String name) {this.name = name;}
    
    public Long getTypeId() {return typeId;}
    public void setTypeId(Long typeId) {this.typeId = typeId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {return this.name;}

}
