package com.hoang.convertor;

import com.hoang.entities.Coupon;
import com.hoang.payload.request.CouponRequest;
import org.springframework.stereotype.Component;

@Component
public class CouponConvertor {
    public Coupon convertor(CouponRequest couponRequest){
        Coupon coupon = new Coupon();
        if(couponRequest.getId()!=null){
            coupon.setId(couponRequest.getId());
        }
        coupon.setDetail(couponRequest.getDetail());
        coupon.setCode(couponRequest.getCode());
        coupon.setTitle(couponRequest.getTitle());
        coupon.setStartDate(couponRequest.getStartDate());
        coupon.setEndDate(couponRequest.getEndDate());
        coupon.setType(couponRequest.getType());
        coupon.setStatus(couponRequest.getStatus());
        return coupon;
    }
}
