package com.hoang.payload.request;

import java.util.HashSet;
import java.util.Set;

public class SkuValueRequest {
    private String code;
    private Integer stock;
    private Integer productId;
    private Double importPrice;
    private Double exportPrice;
    private Set<Value> options = new HashSet<>();

    public SkuValueRequest() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Set<Value> getOptions() {
        return options;
    }

    public void setOptions(Set<Value> options) {
        this.options = options;
    }

    public static class Value{
        private Integer id;
        private String value;

        public Value() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
