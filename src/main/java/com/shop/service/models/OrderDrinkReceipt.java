package com.shop.service.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderDrinkReceipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long drinkId;

    @Column(nullable = false)
    private String drinkCode;

    @Column(nullable = false)
    private String drinkName;

    @Column(nullable = false)
    private int drinkQuantity;

    @Column(nullable = false)
    private double drinkUnitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(nullable = false)
    private Receipt receipt;

}
