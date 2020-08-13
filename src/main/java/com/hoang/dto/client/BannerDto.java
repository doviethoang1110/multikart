package com.hoang.dto.client;

public class BannerDto {
    private Integer id;
    private String title;
    private String content;
    private String image;
    private String links;
    private Short type;

    public BannerDto(Integer id, String title, String content, String image, String links,Short type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.links = links;
        this.type = type;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
