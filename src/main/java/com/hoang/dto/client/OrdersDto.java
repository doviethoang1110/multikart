package com.hoang.dto.client;

import com.hoang.projections.OrderClientProjection;
import com.hoang.projections.OrderDetailClient;

import java.util.List;

public class OrdersDto {
    private OrderClientProjection orderClientProjection;
    private List<OrderDetailClient> list;

    public OrderClientProjection getOrderClientProjection() {
        return orderClientProjection;
    }

    public void setOrderClientProjection(OrderClientProjection orderClientProjection) {
        this.orderClientProjection = orderClientProjection;
    }

    public List<OrderDetailClient> getList() {
        return list;
    }

    public void setList(List<OrderDetailClient> list) {
        this.list = list;
    }
}
