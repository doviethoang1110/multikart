package com.hoang.projections;

import java.sql.Timestamp;

public interface ReviewProjection {
    Integer getId();
    String getName();
    String getContent();
    Short getRating();
    Timestamp getCreated();
}
