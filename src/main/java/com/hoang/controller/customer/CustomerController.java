package com.hoang.controller.customer;

import com.hoang.dto.client.OrdersDto;
import com.hoang.payload.request.CustomerRequest;
import com.hoang.projections.WishListProjection;
import com.hoang.service.CustomerService;
import com.hoang.service.OrderService;
import com.hoang.service.ServiceResponse;
import com.hoang.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class CustomerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private WishListService wishListService;
    @Autowired
    private CustomerService customerService;
    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error",required = false) String error){
        ModelAndView modelAndView = new ModelAndView("login");
        if(error!=null){
            modelAndView.addObject("error","Tên hoặc mật khẩu không đúng");
        }
        return modelAndView;
    }
//    @PostMapping(value = "/login")
//    public void authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {
//        String username = customerService.findByEmail(loginRequest.getEmail());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

    @GetMapping(value = "trang-ca-nhan")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping(value="/dang-xuat")
    public void logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
    @GetMapping(value = "/dang-ky")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping(value = "/dang-ky")
    public ResponseEntity<ServiceResponse> dangKy(@Valid @RequestBody CustomerRequest customerRequest,HttpServletRequest request, Errors errors){
        return customerService.register(customerRequest,errors,"http://" + request.getServerName() +
                ":" + request.getServerPort()+
                request.getContextPath());
    }
    @GetMapping(value = "/verify-email")
    public ModelAndView verifyToken(@RequestParam(name = "token") String token){
        ModelAndView modelAndView = new ModelAndView("verify-token");
        modelAndView.addObject("map",customerService.verifyEmail(token));
        return modelAndView;
    }
    @GetMapping(value = "customers/{id}/orders")
    public ResponseEntity<List<OrdersDto>> getOrders(@PathVariable("id") Integer id){
        return orderService.getOrders(id);
    }
    @GetMapping(value = "customers/{id}/wishlists")
    public ResponseEntity<List<WishListProjection>> getWishList(@PathVariable("id") Integer id){
        return wishListService.getWishLists(id);
    }
    @PostMapping(value = "wishlists")
    public ResponseEntity<ServiceResponse> addWishList(@RequestBody Map<String,Object> map){
        return wishListService.addWishList(map);
    }
    @DeleteMapping(value = "wishlists/{id}")
    public ResponseEntity<ServiceResponse>  deleteWishList(@PathVariable("id") Integer id){
        return wishListService.removeWishList(id);
    }
}
