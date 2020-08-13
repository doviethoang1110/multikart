package com.hoang.payload.request;

import com.hoang.controller.api.role.PermissionRequest;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public class RoleRequest {
    private Integer id;
    @NotBlank(message = "Tên không được rỗng")
    private String name;
    @NotBlank(message = "Tên thay thế không được rỗng")
    private String displayName;
    private Set<PermissionRequest> permission = new HashSet<>();

    public RoleRequest() {
    }

    public RoleRequest(Integer id, String name, String displayName, Set<PermissionRequest> permission) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.permission = permission;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<PermissionRequest> getPermission() {
        return permission;
    }

    public void setPermission(Set<PermissionRequest> permission) {
        this.permission = permission;
    }
}
