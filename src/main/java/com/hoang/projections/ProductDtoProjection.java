package com.hoang.projections;

public interface ProductDtoProjection {
    Integer getId();
    String getName();
    String getSlug();
    String getCategoryName();
    String getCategorySlug();
    Double getDiscount();
    Short getPriority();
    String getImage();
    Double getPriceFrom();
    Double getPriceTo();
}
