package com.hoang.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coupon")
public class Coupon extends BaseEntity{
    @Column(name = "title",nullable = true)
    private String title;
    @Column(name = "code",nullable = true)
    private String code;
    @Column(name = "type")
    private Short type;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "detail")
    private Double detail;
    @Column(name = "end_date")
    private Timestamp endDate;
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "customer_coupon",
        joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();
    private Short status;

    public Short getStatus() {
        return status;
    }

    public Double getDetail() {
        return detail;
    }

    public void setDetail(Double detail) {
        this.detail = detail;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Coupon() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
