package com.hoang.projections;

public interface SkuProjection {
    Integer getId();
    String getCode();
    Double getImportPrice();
    Double getExportPrice();
    Integer getStock();
    String getOption();
    String getStatus();
    Integer getQuantity();
}
