package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BannerRequest {
    private Integer id;
    @NotBlank(message = "Tiêu đề không được rỗng")
    @Size(min = 10,max = 30,message = "Tiêu đề từ 10 đến 30 ký tự")
    private String title;
    @NotBlank(message = "Nội dung không được rỗng")
    @Size(min = 10,max = 30,message = "Nội dung từ 10 đến 30 ký tự")
    private String content;
    private MultipartFile image;
    @NotNull(message = "Loại banner không được rỗng")
    private Short type;
    @NotBlank(message = "Liên kết không được rỗng")
    private String links;
    @NotNull(message = "Trạng thái không được rỗng")
    private Boolean status;

    public BannerRequest() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
