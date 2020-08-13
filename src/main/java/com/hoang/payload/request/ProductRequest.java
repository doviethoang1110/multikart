package com.hoang.payload.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class ProductRequest {
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
    private Set<OptionRequest> option = new HashSet<>();
    private Set<SkuRequest> sku = new HashSet<>();
    public ProductRequest() {
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

    public Set<OptionRequest> getOption() {
        return option;
    }

    public void setOption(Set<OptionRequest> option) {
        this.option = option;
    }

    public Set<SkuRequest> getSku() {
        return sku;
    }

    public void setSku(Set<SkuRequest> sku) {
        this.sku = sku;
    }

    public static class OptionRequest{
        private Integer id;
        private String option;
        private Set<String> values = new HashSet<>();

        public OptionRequest() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public Set<String> getValues() {
            return values;
        }

        public void setValues(Set<String> values) {
            this.values = values;
        }
    }
    public static class SkuRequest{
        private Integer id;
        private String code;
        private Double importPrice;
        private Double exportPrice;
        private Integer stock;
        private Set<SkuValue> values = new HashSet<>();

        public SkuRequest() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public Set<SkuValue> getValues() {
            return values;
        }

        public void setValues(Set<SkuValue> values) {
            this.values = values;
        }
    }
    public static class SkuValue{
        private String key;
        private String value;

        public SkuValue() {
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
