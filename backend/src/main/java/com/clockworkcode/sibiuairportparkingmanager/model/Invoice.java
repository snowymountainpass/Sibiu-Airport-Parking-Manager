package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private Integer periodOfTime;

    private BigDecimal cost;

    public Invoice() {
    }

    public Invoice(Payment payment, Integer periodOfTime, BigDecimal cost) {
        this.payment = payment;
        this.periodOfTime = periodOfTime;
        this.cost = cost;
    }
}
