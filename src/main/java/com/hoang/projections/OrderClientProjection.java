package com.hoang.projections;


public interface OrderClientProjection {
    Integer getId();
    Double getTotal();
    String getOrderStatus();
    String getShippingStatus();
    String getCurrency();
}
