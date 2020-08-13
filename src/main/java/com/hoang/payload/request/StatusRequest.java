package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;

public class StatusRequest {
    private Integer id;
    @NotBlank(message = "Tên không được rỗng")
    private String name;
    private Short status;

    public StatusRequest() {
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
