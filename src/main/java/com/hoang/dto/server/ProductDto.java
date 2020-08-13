package com.hoang.dto.server;
public class ProductDto{
    private Integer id;
    private String name;
    private Integer category;
    private Integer brand;
    private String slug;
    private Double discount;
    private Short priority;
    private String image;
    private String description;
    private String image_list;
    private Short status;
    private Short vision;

    public ProductDto(Integer id, String name, Integer category, Integer brand, String slug, Double discount, Short priority, String image, String description, String image_list, Short status, Short vision) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.slug = slug;
        this.discount = discount;
        this.priority = priority;
        this.image = image;
        this.description = description;
        this.image_list = image_list;
        this.status = status;
        this.vision = vision;
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_list() {
        return image_list;
    }

    public void setImage_list(String image_list) {
        this.image_list = image_list;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getVision() {
        return vision;
    }

    public void setVision(Short vision) {
        this.vision = vision;
    }
}
