package com.hoang.service;

import com.hoang.convertor.BrandConvertor;
import com.hoang.entities.Brand;
import com.hoang.global.GlobalModel;
import com.hoang.global.Utility;
import com.hoang.payload.request.BrandRequest;
import com.hoang.projections.BrandProjections;
import com.hoang.repository.brand.IBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrandService {
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private BrandConvertor convertor;
    @Autowired
    private Utility utility;
    public List<BrandProjections> findAll(){
        return brandRepository.findAllBrand();
    }
    public ResponseEntity<List<Brand>> getAll(){
        return new ResponseEntity<>(brandRepository.findByStatus(Short.parseShort("1")),HttpStatus.OK);
    }
    public ResponseEntity<Map<String,Object>> findAllForServer(Integer page){
        Map<String,Object> map = new HashMap<>();
        Page<Brand> brands = brandRepository.findAllOrderByCreate(PageRequest.of(page,5));
        map.put("totalPage",brands.getTotalPages());
        map.put("listBrands",brands.getContent());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    public ResponseEntity<ServiceResponse> createBrand(BrandRequest brandRequest, Errors errors,HttpServletRequest request){
        ServiceResponse response = new ServiceResponse();
        try {
            if(errors.hasErrors()){
                response.setStatus("errors");
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(brandRequest.getId() != 0
                ? utility.checkUnique(brandRequest.getSlug(),brandRequest.getId(),"brand")
                : utility.checkUnique(brandRequest.getSlug(),null,"brand")){
                response.setData("Đường dẫn seo đã tồn tại");
                response.setStatus("errors");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else {
                response.setData(brandRequest.getId() !=0
                    ? "Cập nhật thành công" : "Thêm mới thành công");
            }
            if(brandRequest.getImage()!=null){
                GlobalModel.upload(brandRequest.getImage(),request.getServletContext().getRealPath("WEB-INF/resources/user/assets/images/logos/"+brandRequest.getImage().getOriginalFilename()));
            }
            brandRepository.save(convertor.convertor(brandRequest));
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setData("Có lỗi xảy ra");
            response.setStatus("errors");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<ServiceResponse> delete(Integer id) {
        ServiceResponse<String> response = new ServiceResponse<>();
        if(brandRepository.findProducts(id).size()>0){
            response.setData("Nhãn hiệu chứa sản phẩm không thể xóa");
            response.setStatus("errors");
        }else{
            brandRepository.deleteById(id);
            response.setStatus("success");
            response.setData("Xóa thành công");
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<Brand> findById(Integer id) {
        return new ResponseEntity<>(brandRepository.findById(id).get(),HttpStatus.OK);
    }
}
