package com.hoang.projections;

public interface ProductSearchProjection {
    String getSlug();
    String getName();
    String getCategorySlug();
    Double getPriceFrom();
    Double getPriceTo();
    String getImage();
}
