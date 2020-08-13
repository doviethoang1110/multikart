package com.hoang.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "option")
public class Option extends BaseEntity{
    @Column(name = "name",nullable = false,length = 100)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "option",cascade = CascadeType.ALL)
    private Set<OptionValue> optionValues = new HashSet<>();

    public Option() {
    }


    public Set<OptionValue> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(Set<OptionValue> optionValues) {
        this.optionValues = optionValues;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
