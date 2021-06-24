package com.a2z.demo.customer.entity;

import com.a2z.demo.common.entity.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;

    public CustomerEntity() {
    }

    public CustomerEntity(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "CustomerEntity[id=%d, firstName='%s', lastName='%s',email='%s']",
                id, firstName, lastName, email);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
