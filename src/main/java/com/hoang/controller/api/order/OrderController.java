package com.hoang.controller.api.order;

import com.hoang.entities.Order;
import com.hoang.projections.*;
import com.hoang.service.OrderService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RequestMapping(value = "/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PreAuthorize("hasAuthority('READ_ORDER')")
    @GetMapping(value = "orders")
    public ResponseEntity<Map<String,Object>> findAll(
            @RequestParam(value = "page",required = false,defaultValue = "0") Integer page
    ){
        return orderService.findAll(page);
    }
    @PreAuthorize("hasAuthority('UPDATE_ORDER')")
    @GetMapping(value = "orders/{id}")
    public ResponseEntity<Order> edit(@PathVariable("id") Integer id){
        return orderService.edit(id);
    }
    @PreAuthorize("hasAuthority('UPDATE_ORDER')")
    @GetMapping(value = "orders/orderstatus")
    public ResponseEntity<List<OrderStatusProjection>> findOrderStatus(){
        return orderService.findOrderStatus();
    }
    @PreAuthorize("hasAuthority('UPDATE_ORDER')")
    @PutMapping(value = "orders")
    public ResponseEntity<ServiceResponse> updateOrder(@RequestBody Map<String,Object> map){
        return orderService.update(map);
    }
    @PreAuthorize("hasAuthority('DELETE_ORDER')")
    @DeleteMapping(value = "orders/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
        return orderService.remove(id);
    }
}
