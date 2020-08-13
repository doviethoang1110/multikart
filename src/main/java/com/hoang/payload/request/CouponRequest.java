package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CouponRequest {
    private Integer id;
    @NotBlank(message = "Tiêu đề không được rỗng")
    private String title;
    @NotBlank(message = "Mã không được rỗng")
    private String code;
    @NotNull(message = "Chi tiết không được rỗng")
    private Double detail;
    private Timestamp startDate;
    private Timestamp endDate;
    @NotNull(message = "Loại không được rỗng")
    private Short type;
    private Short status;

    public Double getDetail() {
        return detail;
    }

    public void setDetail(Double detail) {
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
