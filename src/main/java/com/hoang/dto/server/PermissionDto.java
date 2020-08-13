package com.hoang.dto.server;

public class PermissionDto {
    private Integer id;
    private String displayName;

    public PermissionDto() {
    }

    public PermissionDto(Integer id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
