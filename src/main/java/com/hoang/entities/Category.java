package com.hoang.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {
    @Column(name = "name",nullable = false,length = 100)
    private String name;
    @Column(name = "slug",nullable = false,length = 100)
    private String slug;
    @Column(name = "status",nullable = true)
    private Short status;
    @Column(name = "parent_id")
    private Integer parentId;

    public Category() {
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
