package com.hoang.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sku")
public class Sku{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "code",nullable = false,length = 100)
    private String code;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "importPrice",nullable = false)
    private Double importPrice;
    @Column(name = "exportPrice",nullable = false)
    private Double exportPrice;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "sku_value",
    joinColumns = @JoinColumn(name = "sku_id"),inverseJoinColumns = @JoinColumn(name = "option_value_id"))
    private Set<OptionValue> optionValues = new HashSet<>();
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private Timestamp createAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "quantity")
    private Integer quantity;
    public Sku() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<OptionValue> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(Set<OptionValue> optionValues) {
        this.optionValues = optionValues;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    public Double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(Double exportPrice) {
        this.exportPrice = exportPrice;
    }
}
