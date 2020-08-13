package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;

public class PermissionRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String displayName;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
