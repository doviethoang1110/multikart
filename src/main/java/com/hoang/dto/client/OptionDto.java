package com.hoang.dto.client;


import java.util.HashSet;
import java.util.Set;

public class OptionDto {
    private Integer id;
    private String name;
    private Set<OptionValueDto> optionValueDtos = new HashSet<>();

    public OptionDto(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

    public Set<OptionValueDto> getOptionValueDtos() {
        return optionValueDtos;
    }

    public void setOptionValueDtos(Set<OptionValueDto> optionValueDtos) {
        this.optionValueDtos = optionValueDtos;
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
