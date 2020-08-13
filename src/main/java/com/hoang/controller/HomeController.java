package com.hoang.controller;

import com.hoang.dto.client.BannerDto;
import com.hoang.dto.client.CategoryDto;
import com.hoang.dto.client.ProductDetailDto;
import com.hoang.dto.client.ProductDto;
import com.hoang.dto.server.ReviewDto;
import com.hoang.entities.Currency;
import com.hoang.projections.*;
import com.hoang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private BlogService blogService;
    @GetMapping(value = "trang-chu")
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView("index");
        List<BannerDto> list = bannerService.getAll();
        mav.addObject("listBlog",blogService.findListRecent());
        mav.addObject("listRun",bannerService.filter(list,"1"));
        mav.addObject("listAdvert",bannerService.filter(list,"2").stream().limit(2).collect(Collectors.toList()));
        mav.addObject("seo",bannerService.filter(list,"3").stream().limit(1).collect(Collectors.toList()).get(0));
        return mav;
    }
    @GetMapping(value = "index")
    public ResponseEntity<List<ProductDto>> getProductIndex(){
        return new ResponseEntity<>(productService.findSpecialProduct(),HttpStatus.OK);
    }
    @GetMapping(value = "san-pham")
    public ModelAndView viewProduct(){
        ModelAndView mav = new ModelAndView("product_list");
        return mav;
    }
    @GetMapping(value = "search")
    public ResponseEntity<Map<String,Object>> view(
            @RequestParam(name = "brand",required = false,defaultValue = "") String brand,
            @RequestParam(name = "price",required = false,defaultValue = "") String price,
            @RequestParam(name = "sort",required = false,defaultValue = "") String sort,
            @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
            @RequestParam(name = "size",required = false,defaultValue = "12") Integer size){
        return productService.search(brand,price,sort,page,size);
    }
    @GetMapping(value = "tim-kiem")
    public ResponseEntity<List<ProductSearchProjection>> search(@RequestParam(name = "key",required = false) String key){
        return new ResponseEntity<>(productService.searchProjections(key),HttpStatus.OK);
    }
    @GetMapping(value = "danh-muc/{slug}")
    public ModelAndView getProductByCategoryId(@PathVariable("slug") String slug){
        ModelAndView modelAndView = new ModelAndView("product_category");
        modelAndView.addObject("listPro",categoryService.findProductByCategory(slug));
        modelAndView.addObject("slug",slug);
        return modelAndView;
    }
    @GetMapping(value = "{category}/{slug}")
    public ModelAndView getProductBySlug(@PathVariable("category") String category, @PathVariable("slug") String product){
        ModelAndView modelAndView = new ModelAndView("product_detail");
        if(productService.findProductSlug(product)==null||categoryService.findCategorySlug(category)==null){
            return new ModelAndView("404");
        }else{
            modelAndView.addObject("listBrand",brandService.findAll());
            modelAndView.addObject("product",productService.getDetail(product));
            modelAndView.addObject("listPro",productService.getProductRelateByCategoryId(category));
        }
        return modelAndView;
    }
    @PostMapping(value = "products/filter")
    public ResponseEntity<ServiceResponse> filter(@RequestBody List<Integer> integers){
        return productService.findSku(integers);
    }
    @GetMapping(value = "/currency")
    public ResponseEntity<List<Currency>> findAll(){
        return currencyService.findAll();
    }
    @GetMapping(value = "/getCoupon")
    public ResponseEntity<CouponDtoProjection> getCoupon(@RequestParam("coupon") String coupon, @RequestParam("userId") Integer id){
        return couponService.getCoupon(coupon,id);
    }
    @GetMapping(value = "/listCoupon")
    public ResponseEntity<List<CouponDtoProjection>> getCoupons(@RequestParam("userId") Integer id){
        return couponService.getCoupons(id);
    }
    @PostMapping(value = "/reviews")
    public ResponseEntity<ServiceResponse> addReview(@RequestBody ReviewDto reviewDto){
        return reviewService.create(reviewDto);
    }
    @GetMapping(value = "/reviews/{proId}")
    public ResponseEntity<List<ReviewProjection>> getReviews(@PathVariable("proId") Integer proId){
        return new ResponseEntity<>(reviewService.getReviews(proId), HttpStatus.OK);
    }
    @GetMapping(value = "/bai-viet")
    public ModelAndView blog(){
        ModelAndView modelAndView = new ModelAndView("blog");
        modelAndView.addObject("listBlog",blogService.findListRecent());
        return modelAndView;
    }
    @GetMapping(value = "/bai-viet/{slug}")
    public ModelAndView findBlog(@PathVariable("slug") String slug){
        ModelAndView modelAndView = new ModelAndView("blog_detail");
        modelAndView.addObject("blog",blogService.findBySlug(slug));
        return modelAndView;
    }
    @GetMapping(value = "/blogs")
    public ResponseEntity<Map<String,Object>> pageableBlog(@RequestParam(name = "page",required = false,defaultValue = "0") Integer page){
        return blogService.pageBlog(page);
    }
}
