package com.ecommerce.microcommerce.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

//@JsonFilter("myDynamicFilter")    => add filter to entity, cf controller

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @Length(min=3, max=25, message="Nom trop long ou trop court")
    private String name;
    //@Min(value=1)
    private int price;

    @JsonIgnore
    private int purchasePrice;

    public Product() {
    }

    public Product(int id, String name, int price, int purchasePrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.purchasePrice = purchasePrice;
    }
    @JsonIgnore
    public int getMarge() {
        return this.price - this.purchasePrice;
    }

    @Override
    public String toString(){
        return "Product{" + " id=" + id + ", name='"+ name + '\'' +
                ", price=" + price + '}';
    }
}
