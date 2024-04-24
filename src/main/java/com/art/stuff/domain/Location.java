package com.art.stuff.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Location {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long locationId;
    private String name;

    @OneToMany(
        cascade = {CascadeType.PERSIST},
        mappedBy = "location"
    )
    private List<Item> items = new ArrayList<>();

    public Location() {}

    public Location(String name) {this.name = name;}

    public Long getLocationId() {return locationId;}
    public void setLocationId(Long locationId) {this.locationId = locationId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {return this.name;}
}
