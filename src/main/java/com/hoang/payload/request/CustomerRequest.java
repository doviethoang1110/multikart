package com.hoang.payload.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerRequest {
    private Integer id;
    @NotBlank(message = "Tên không được rỗng")
    @Size(min = 6, max = 20,message = "Tên từ 6-20 ký tự")
    private String name;
    @NotBlank(message = "Email không được rỗng")
    @Email(message = "Email không đúng định dạng")
    @Size(min = 6, max = 50,message = "Email từ 6-50 ký tự")
    private String email;
    @NotBlank(message = "Password không được rỗng")
    @Size(min = 6, max = 20,message = "Password từ 6-20 ký tự")
    private String password;
    @NotBlank(message = "Số điện thoại không được rỗng")
    private String phone;
    @NotBlank(message = "Địa chỉ không được rỗng")
    private String address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
