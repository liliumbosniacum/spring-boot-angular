package com.lilium.springangular.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vehicle extends DistributedEntity {

    @Column(nullable = false, unique = true)
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
