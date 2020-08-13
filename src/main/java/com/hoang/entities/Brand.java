package com.hoang.entities;

import javax.persistence.*;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity{
    @Column(name = "name",nullable = false,length = 100)
    private String name;
    @Column(name = "slug",nullable = false,length = 100)
    private String slug;
    @Column(name = "image",nullable = false)
    private String image;
    @Column(name = "status",nullable = false)
    private Short status;


    public Brand() {
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
