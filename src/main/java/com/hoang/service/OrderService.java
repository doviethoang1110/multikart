package com.hoang.service;

import com.hoang.convertor.OrderConvertor;
import com.hoang.dto.client.OrdersDto;
import com.hoang.entities.Order;
import com.hoang.payload.request.OrderRequest;
import com.hoang.projections.*;
import com.hoang.repository.order.IOrderRepository;
import com.hoang.repository.order_status.IOrderStatusRepository;
import com.hoang.repository.payment.IPaymentStatusRepository;
import com.hoang.repository.shipping.IShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private OrderConvertor orderConvertor;
    @Autowired
    private IPaymentStatusRepository paymentStatusRepository;
    @Autowired
    private IShippingRepository shippingRepository;
    @Autowired
    private IOrderStatusRepository orderStatusRepository;
    public ResponseEntity<ServiceResponse> addOrder(OrderRequest orderRequest){
        ServiceResponse response = new ServiceResponse();
        try {
            orderRepository.save(orderConvertor.convertor(orderRequest));
            response.setData("Đặt hàng thành công,vui lòng kiểm tra email");
            response.setStatus("success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setData("Có lỗi xảy ra");
            response.setStatus("error");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> findAll(Integer page) {
        Map<String,Object> map = new HashMap<>();
        Page<OrderProjection> orderProjections = orderRepository.getAll(PageRequest.of(page,5));
        map.put("listOrders",orderProjections.getContent());
        map.put("totalPage",orderProjections.getTotalPages());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    public ResponseEntity<Order> edit(Integer id) {
        return new ResponseEntity<>(orderRepository.findOne(id),HttpStatus.OK);
    }

    public ResponseEntity<List<OrderStatusProjection>> findOrderStatus() {
        return new ResponseEntity<>(orderRepository.findOrderStatus(),HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> update(Map<String, Object> map) {
        ServiceResponse response = new ServiceResponse();
        try{
            Order order = orderRepository.findOne(Integer.parseInt(map.get("id").toString()));
            if(Integer.parseInt(map.get("orderStatus").toString())<= order.getOrderStatus().getId()){
                response.setStatus("error");
                response.setData("Không thể cập nhật về trạng thái cũ hơn");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                if(Integer.parseInt(map.get("orderStatus").toString())==4){
                    response.setStatus("error");
                    response.setData("Đơn hàng đã hoàn tất không thể hủy");
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }
                order.setDeliveryStatus(shippingRepository.findById(Integer.parseInt(map.get("orderStatus").toString())).get());
                order.setPaymentStatus(paymentStatusRepository.getOne(Integer.parseInt(map.get("orderStatus").toString())));
            }
            order.setOrderStatus(orderStatusRepository.findOne(Integer.parseInt(map.get("orderStatus").toString())));
            orderRepository.save(order);
            response.setData("Cập nhật thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ServiceResponse> remove(Integer id){
        ServiceResponse response = new ServiceResponse();
        try{
            orderRepository.deleteById(id);
            response.setStatus("success");
            response.setData("Xóa thành công");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.setData("Xóa thất bại");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<OrdersDto>> getOrders(Integer id) {
        List<OrdersDto> ordersDtos = new ArrayList<>();
        List<OrderClientProjection> orderClientProjections = orderRepository.findOrders(id);
        for (OrderClientProjection orderClientProjection:orderClientProjections){
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setOrderClientProjection(orderClientProjection);
            ordersDto.setList(orderRepository.findOrderDetails(orderClientProjection.getId()));
            ordersDtos.add(ordersDto);
        }
        return new ResponseEntity<>(ordersDtos,HttpStatus.OK);
    }

}
