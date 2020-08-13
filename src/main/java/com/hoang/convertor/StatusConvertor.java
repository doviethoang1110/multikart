package com.hoang.convertor;

import com.hoang.entities.DeliveryStatus;
import com.hoang.entities.OrderStatus;
import com.hoang.entities.PaymentStatus;
import com.hoang.payload.request.StatusRequest;
import org.springframework.stereotype.Component;

@Component
public class StatusConvertor {
    public PaymentStatus convertToPayment(StatusRequest statusRequest){
        PaymentStatus paymentStatus = new PaymentStatus();
        if(statusRequest.getId()!=null){
            paymentStatus.setId(statusRequest.getId());
        }
        paymentStatus.setName(statusRequest.getName());
        paymentStatus.setStatus(statusRequest.getStatus());
        return paymentStatus;
    }
    public DeliveryStatus convertToDeliver(StatusRequest statusRequest){
        DeliveryStatus deliveryStatus = new DeliveryStatus();
        if(statusRequest.getId()!=null){
            deliveryStatus.setId(statusRequest.getId());
        }
        deliveryStatus.setName(statusRequest.getName());
        deliveryStatus.setStatus(statusRequest.getStatus());
        return deliveryStatus;
    }
    public OrderStatus convertToOrderStatus(StatusRequest statusRequest){
        OrderStatus orderStatus = new OrderStatus();
        if(statusRequest.getId()!=null){
            orderStatus.setId(statusRequest.getId());
        }
        orderStatus.setName(statusRequest.getName());
        orderStatus.setStatus(statusRequest.getStatus());
        return orderStatus;
    }
}
