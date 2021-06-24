package com.a2z.demo.product.entity;

import com.a2z.demo.common.entity.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;
    private String name;
    private double price;


    public ProductEntity() {
    }

    public ProductEntity(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "ProductEntity[id=%d, code='%s', name='%s', price='%s']",
                id, code, name, price);
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}