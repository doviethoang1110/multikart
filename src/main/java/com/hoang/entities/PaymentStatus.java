package com.hoang.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_status")
public class PaymentStatus extends BaseEntity{
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "status")
    private Short status;

    public PaymentStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
