package com.hoang.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_status")
public class DeliveryStatus extends BaseEntity{
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "status")
    private Short status;

    public DeliveryStatus() {
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
