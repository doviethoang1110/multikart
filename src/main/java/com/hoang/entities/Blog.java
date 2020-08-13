package com.hoang.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "slug")
    private String slug;
    @Column(name = "content")
    private String content;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private Boolean status;

    public Blog() {
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
