package com.shop.service.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Receipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long orderId;

    private String orderDetails;

    private Date orderDate = new Date();

    @Column(nullable = false)
    private long clientId;

    private String clientDetails;

    private Double clientLongitude;

    private Double clientLatitude;

    @Column(nullable = false)
    private long cargoId;

    @Column(nullable = false)
    private String cargoCode;

    @Column(nullable = false)
    private String cargoName;

    private String cargoDetails;

    private Double cargoLongitude;

    private Double cargoLatitude;

    @OneToMany(mappedBy = "receipt", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrderDrinkReceipt> orderDrinkReceipts = new ArrayList<>();

}
