package com.hoang.dto.client;

public class OptionValueDto {
    private Integer id;
    private Integer optionId;
    private String name;

    public OptionValueDto(Integer id, String name,Integer optionId) {
        this.id = id;
        this.name = name;
        this.optionId = optionId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
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
}
