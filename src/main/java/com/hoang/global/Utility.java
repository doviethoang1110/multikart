package com.hoang.global;

import com.hoang.repository.brand.IBrandRepository;
import com.hoang.repository.category.ICategoryRepository;
import com.hoang.repository.coupon.ICouponRepository;
import com.hoang.repository.currency.ICurrencyRepository;
import com.hoang.repository.order_status.IOrderStatusRepository;
import com.hoang.repository.payment.IPaymentStatusRepository;
import com.hoang.repository.permissions.IPermissionsRepository;
import com.hoang.repository.product.IProductRepository;
import com.hoang.repository.roles.IRolesRepository;
import com.hoang.repository.shipping.IShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utility {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private IPaymentStatusRepository paymentStatusRepository;
    @Autowired
    private IShippingRepository shippingRepository;
    @Autowired
    private ICouponRepository couponRepository;
    @Autowired
    private ICurrencyRepository currencyRepository;
    @Autowired
    private IOrderStatusRepository orderStatusRepository;
    @Autowired
    private IPermissionsRepository permissionsRepository;
    @Autowired
    private IRolesRepository rolesRepository;
    @Autowired
    private IProductRepository productRepository;
    public boolean checkUnique(String slug,Integer id,String prefix){
        boolean check = true;
        String a;
        String b;
        switch (prefix){
            case "category":
                a = categoryRepository.findSlug(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = categoryRepository.findSlug(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "brand":
                a = brandRepository.findSlug(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = brandRepository.findSlug(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "coupon":
                a = couponRepository.findCode(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = couponRepository.findCode(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "currency":
                a = currencyRepository.uniqueCode(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = currencyRepository.uniqueCode(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "order_status":
                a = orderStatusRepository.uniqueName(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = orderStatusRepository.uniqueName(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "payment":
                a = paymentStatusRepository.uniqueName(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = paymentStatusRepository.uniqueName(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "shipping":
                a = shippingRepository.uniqueName(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = shippingRepository.uniqueName(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "permission":
                a = permissionsRepository.findOne(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = permissionsRepository.findOne(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "role":
                a = rolesRepository.findName(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = rolesRepository.findName(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "role_display":
                a = rolesRepository.findDisplayName(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = rolesRepository.findDisplayName(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "product":
                a = productRepository.uniqueName(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = productRepository.uniqueName(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
            case "product_edit":
                a = productRepository.uniqueSlug(slug);
                if(a == null){
                    check = false;
                }else{
                    if(id!=null){
                        b = productRepository.uniqueSlug(id);
                        check = !a.equals(b) ? true : false;
                    }
                }
                break;
        }
        return check;
    }
}
