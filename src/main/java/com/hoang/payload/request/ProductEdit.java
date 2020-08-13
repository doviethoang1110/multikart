package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class ProductEdit {
    private Integer id;
    @NotBlank(message = "Tên không được rỗng")
    private String name;
    @NotBlank(message = "Đường dẫn không được rỗng")
    private String slug;
    @NotNull(message = "Danh mục không được rỗng")
    private Integer category;
    @NotNull(message = "Nhãn hiệu không được rỗng")
    private Integer brand;
    @NotNull(message = "Giảm giá không được rỗng")
    private Double discount;
    @NotNull(message = "Phạm vi không được rỗng")
    private Short vision;
    @NotNull(message = "Trạng thái không được rỗng")
    private Short status;
    @NotNull(message = "Độ ưu tiên không được rỗng")
    private Short priority;
    @NotBlank(message = "Mô tả không được rỗng")
    private String description;
    private MultipartFile image;
    private MultipartFile[] image_list;

    public ProductEdit() {
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Short getVision() {
        return vision;
    }

    public void setVision(Short vision) {
        this.vision = vision;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile[] getImage_list() {
        return image_list;
    }

    public void setImage_list(MultipartFile[] image_list) {
        this.image_list = image_list;
    }
}
