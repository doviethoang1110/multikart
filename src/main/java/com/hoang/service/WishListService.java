package com.hoang.service;

import com.hoang.entities.WishList;
import com.hoang.projections.WishListProjection;
import com.hoang.repository.customer.ICustomerRepository;
import com.hoang.repository.product.IProductRepository;
import com.hoang.repository.wishlist.IWishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WishListService {
    @Autowired
    private IWishListRepository wishListRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    public ResponseEntity<List<WishListProjection>> getWishLists(Integer id) {
        return new ResponseEntity<>(wishListRepository.findWishList(id), HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> addWishList(Map<String, Object> map) {
        ServiceResponse response = new ServiceResponse();
        try{
            if(wishListRepository.checkExist(Integer.parseInt(map.get("product_id").toString()),Integer.parseInt(map.get("customer_id").toString()))!=null){
                response.setStatus("error");
                response.setData("Sản phẩm này đã tồn tại trong wishlist");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                WishList wishList = new WishList();
                wishList.setProduct(productRepository.getById(Integer.parseInt(map.get("product_id").toString())));
                wishList.setCustomer(customerRepository.findOne(Integer.parseInt(map.get("customer_id").toString())));
                wishListRepository.save(wishList);
                response.setData("Thêm thành công");
                response.setStatus("success");
                return new ResponseEntity<>(response,HttpStatus.OK);
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Thêm thất bại");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ServiceResponse> removeWishList(Integer id) {
        ServiceResponse response = new ServiceResponse();
        try {
            wishListRepository.deleteById(id);
            response.setData("Xóa thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
