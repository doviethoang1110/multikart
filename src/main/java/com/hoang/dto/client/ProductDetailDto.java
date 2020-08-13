package com.hoang.dto.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDetailDto {
    private Integer id;
    private String name;
    private String slug;
    private Double discount;
    private Short vision;
    private Set<OptionDto> optionDtos = new HashSet<>();
    private String description;
    private String image;
    private List<String> image_list = new ArrayList<>();

    public ProductDetailDto() {
    }

    public String getSlug() {
        return slug;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Set<OptionDto> getOptionDtos() {
        return optionDtos;
    }

    public void setOptionDtos(Set<OptionDto> optionDtos) {
        this.optionDtos = optionDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<String> image_list) {
        this.image_list = image_list;
    }

}
