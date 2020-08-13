package com.hoang.projections;

import java.sql.Timestamp;

public interface OrderProjection {
    Integer getId();
    String getEmail();
    Double getTotal();
    String getPaymentMethod();
    String getCurrency();
    Double getRate();
    String getOrderStatus();
    Timestamp getCreated();
}
