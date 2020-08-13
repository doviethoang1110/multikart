package com.hoang.dto.client;

public class ProductDto {
    private Integer id;
    private String name;
    private String slug;
    private String categoryName;
    private String categorySlug;
    private Double discount;
    private Short priority;
    private String image;
    private Integer review;
    private Double from;
    private Double to;
    public ProductDto(Integer id,String name, String slug, String categoryName, String categorySlug, Double discount, Short priority, String image,Integer review,Double from,Double to) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.categoryName = categoryName;
        this.categorySlug = categorySlug;
        this.discount = discount;
        this.priority = priority;
        this.image = image;
        this.review = review;
        this.from = from;
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }
}
