package com.hoang.projections;

import java.sql.Timestamp;

public interface CouponProjection {
    Integer getId();
    String getTitle();
    String getCode();
    Double getDetail();
    Timestamp getStartDate();
    Timestamp getEndDate();
    Short getType();
    Short getStatus();
}
