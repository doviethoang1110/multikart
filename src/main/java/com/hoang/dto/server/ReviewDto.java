package com.hoang.dto.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
@Validated
public class ReviewDto {
    private Integer id;
    @JsonProperty("slug")
    @NotNull(message = "Mã sản phẩm không được rỗng")
    private String slug;
    @JsonProperty("name")
    @NotNull(message = "Tên không được rỗng")
    private String name;
    @JsonProperty("email")
    @NotNull(message = "Email không được rỗng")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @JsonProperty("rating")
    @NotNull(message = "Rating không được rỗng")
    private Short rating;
    @JsonProperty("content")
    @NotNull(message = "Nội dung không được rỗng")
    private String content;

    public ReviewDto() {
    }

    public ReviewDto(Integer id, String slug, String name, String email, Short rating, String content) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
