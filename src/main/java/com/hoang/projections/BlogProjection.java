package com.hoang.projections;

import java.sql.Timestamp;

public interface BlogProjection {
    String getTitle();
    String getSlug();
    String getImage();
    String getContent();
    Timestamp getCreatedAt();
    String getCreatedBy();
}
