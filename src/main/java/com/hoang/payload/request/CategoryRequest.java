package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CategoryRequest {
    private Integer id;
    @NotBlank(message = "Tên không được rỗng")
    private String name;
    @NotBlank(message = "Đường dẫn không được rỗng")
    private String slug;
    @NotNull(message = "Danh mục cha không được rỗng")
    private Integer parentId;
    private Short status;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
