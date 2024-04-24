package com.art.stuff.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class Item {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long itemId;
    private String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity = 1;

    @ManyToOne
    @JoinColumn(
        name = "typeId",
        nullable = true
    )
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Type type;
    @ManyToOne
    @JoinColumn(
        name = "locationId",
        nullable = true
    )
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location location;
    private Boolean isFragile = false;
    private Boolean isChecked = false;
    
    
    public Item() {}

    public Item(String name, int quantity, Type type, Location location, boolean isFragile, boolean isChecked) {
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.location = location;
        this.isFragile = isFragile;
        this.isChecked = isChecked;
    }
    
    public Long getItemId() {return itemId;}
    public void setItemId(Long itemId) {this.itemId = itemId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public Type getType() {return type;}
    public void setType(Type type) {this.type = type;}

    public Location getLocation() {return location;}
    public void setLocation(Location location) {this.location = location;}

    public Boolean getIsFragile() {return isFragile;}
    public void setIsFragile(Boolean isFragile) {this.isFragile = isFragile;}

    public Boolean getIsChecked() {return isChecked;}
    public void setIsChecked(Boolean isChecked) {this.isChecked = isChecked;}

    public String toString() {
        return "Item{" +
               "itemId=" + itemId +
               ", name='" + name + '\'' +
               ", quantity=" + quantity +
               ", type=" + (type != null ? type.toString() : "null") +
               ", location=" + (location != null ? location.toString() : "null") +
               ", isFragile=" + isFragile +
               ", isPacked=" + isChecked +
               '}';
    }
}

