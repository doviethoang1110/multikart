package com.hoang.payload.request;

import java.util.List;

public class CouponCustomerRequest {
    private Integer coupon_id;
    private List<Integer> customer_id;

    public CouponCustomerRequest() {
    }

    public Integer getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(Integer coupon_id) {
        this.coupon_id = coupon_id;
    }

    public List<Integer> getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(List<Integer> customer_id) {
        this.customer_id = customer_id;
    }
}
