package com.hoang.dto.server;

import com.hoang.entities.Coupon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CouponDto {
    private String name;
    private Set<Coupon> coupons = new HashSet<>();

    public CouponDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }
}
