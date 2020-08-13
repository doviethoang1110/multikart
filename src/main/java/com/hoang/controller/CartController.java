package com.hoang.controller;

import com.hoang.payload.request.OrderRequest;
import com.hoang.service.OrderService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/")
public class CartController {
    @Autowired
    private OrderService orderService;
    @GetMapping(value = "gio-hang")
    public ModelAndView viewCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("cart");
        return modelAndView;
    }
    @GetMapping(value = "thanh-toan")
    public ModelAndView checkOut(){
        ModelAndView modelAndView = new ModelAndView("checkout");
        return modelAndView;
    }
    @PostMapping(value = "orders")
    public ResponseEntity<ServiceResponse> orders(@RequestBody OrderRequest orderRequest){
        return orderService.addOrder(orderRequest);
    }
}
