package com.hoang.dto.client;

public class BrandDto {
    private Integer id;
    private String name;
    private String slug;
    private String image;

    public BrandDto(Integer id, String name, String slug, String image) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
