package com.hoang.convertor;

import com.hoang.entities.*;
import com.hoang.payload.request.OrderRequest;
import com.hoang.repository.coupon.ICouponRepository;
import com.hoang.repository.customer.ICustomerRepository;
import com.hoang.repository.order_status.IOrderStatusRepository;
import com.hoang.repository.payment.IPaymentStatusRepository;
import com.hoang.repository.product.IProductRepository;
import com.hoang.repository.shipping.IShippingRepository;
import com.hoang.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashSet;
import java.util.Set;

@Component
public class OrderConvertor {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ICouponRepository couponRepository;
    @Autowired
    private IPaymentStatusRepository paymentStatusRepository;
    @Autowired
    private IShippingRepository shippingRepository;
    @Autowired
    private IOrderStatusRepository orderStatusRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private EmailService emailService;
    public Order convertor(OrderRequest orderRequest){
        Order order = new Order();
        Coupon coupon = couponRepository.findOne(orderRequest.getCoupon());
        Customer customer = customerRepository.findOne(orderRequest.getCustomerId());
        order.setCustomer(customer);
        order.setCurrency(orderRequest.getCurrency());
        order.setRate(orderRequest.getRate());
        order.setName(orderRequest.getName());
        order.setEmail(orderRequest.getEmail());
        order.setPhone(orderRequest.getPhone());
        order.setAddress(orderRequest.getAddress());
        order.setNote(orderRequest.getNote());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setShippingMethod(orderRequest.getShippingMethod());
        order.setShipping(orderRequest.getShipping());
        order.setCoupon(orderRequest.getCoupon());
        if(coupon!=null){
            for (Coupon coupon1:customer.getCoupons()){
                if(coupon1.getId()==coupon.getId()){
                    customer.getCoupons().remove(coupon1);
                    break;
                }
            }
            for (Customer customer1:coupon.getCustomers()){
                if(customer1.getId()==customer.getId()){
                    coupon.getCustomers().remove(customer1);
                    break;
                }
            }
            couponRepository.save(coupon);
        }
        order.setSubtotal(orderRequest.getSubtotal());
        order.setTotal(orderRequest.getTotal());
        order.setOrderStatus(orderStatusRepository.findOne(1));
        order.setPaymentStatus(paymentStatusRepository.findById(1).get());
        order.setDeliveryStatus(shippingRepository.findById(1).get());
        Set<OrderDetail> orderDetails = new HashSet<>();
        String html = "";
        int i = 0;
        html += "<table style='width:800px' border='1' cellspacing='1'>";
        html += "<thead>";
        html += "<th>Stt</th>";
        html += "<th>Tên sản phẩm</th>";
        html += "<th>Mã kho</th>";
        html += "<th>Số lượng</th>";
        html += "<th>Giá</th>";
        html += "<th>Thành tiền</th>";
        html += "</thead>";
        html += "<tbody>";
        for (OrderRequest.OrderDetailRequest orderDetailRequest:orderRequest.getOrderDetailRequests()){
            i++;
            html += "<tr>";
            html += "<td>"+i+"</td>";
            html += "<td>"+orderDetailRequest.getProductName()+"</td>";
            html += "<td>"+orderDetailRequest.getSku()+"</td>";
            html += "<td>"+orderDetailRequest.getQuantity()+"</td>";
            html += "<td>"+orderDetailRequest.getPrice()+" "+order.getCurrency()+"</td>";
            html += "<td>"+orderDetailRequest.getPrice()*orderDetailRequest.getQuantity()+" "+order.getCurrency()+"</td>";
            html += "</tr>";
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setPrice(orderDetailRequest.getPrice());
            orderDetail.setQuantity(orderDetailRequest.getQuantity());
            orderDetail.setSku(orderDetailRequest.getSku());
            orderDetail.setStatus(Short.parseShort("1"));
            orderDetail.setProduct(productRepository.getById(orderDetailRequest.getProductId()));
            orderDetails.add(orderDetail);
        }
        html += "<tr><td colspan='5'>Tạm tính</td><td>"+order.getSubtotal()+" "+order.getCurrency()+"</td></tr>";
        html += "<tr><td colspan='5'>Phí vận chuyển</td><td>"+order.getShipping()+" "+order.getCurrency()+"</td></tr>";
        html += "<tr><td colspan='5'>Mã giảm giá</td><td>"+orderRequest.getCoupon()+"</td></tr>";
        html += "<tr><td colspan='5'>Tổng tiền</td><td>"+order.getTotal()+" "+order.getCurrency()+"</td></tr>";
        html += "</tbody>";
        html += "</table>";
        try {
            emailService.sendEmail(order.getEmail(),"Xác thực đơn hàng",html);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        order.setOrderDetails(orderDetails);
        return order;
    }
}
