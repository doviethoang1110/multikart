package com.hoang.service;


import com.hoang.convertor.BannerConvertor;
import com.hoang.dto.client.BannerDto;
import com.hoang.entities.Banner;
import com.hoang.payload.request.BannerRequest;
import com.hoang.projections.BannerProjection;
import com.hoang.repository.banner.IBannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BannerService {
    @Autowired
    private IBannerRepository bannerRepository;
    @Autowired
    private BannerConvertor convertor;
    public ResponseEntity<ServiceResponse> create(BannerRequest bannerRequest, Errors errors, HttpServletRequest request){
        ServiceResponse response = new ServiceResponse();
        try{
            if(errors.hasErrors()){
                response.setStatus("errors");
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                if(bannerRequest.getImage()!=null){
                    File file = new File(request.getServletContext().getRealPath("WEB-INF/resources/user/assets/images/home-banner/")+bannerRequest.getImage().getOriginalFilename());
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    try {
                        bannerRequest.getImage().transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                bannerRepository.save(convertor.convertor(bannerRequest));
                if(bannerRequest.getId().equals(0)){
                    response.setStatus("success");
                    response.setData("Thêm mới thành công");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }else{
                    response.setStatus("success");
                    response.setData("Cập nhật thành công");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("errors");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> findAll(Integer page) {
        Map<String,Object> map = new HashMap<>();
        Page<BannerProjection> bannerProjections = bannerRepository.getAll(PageRequest.of(page,4));
        map.put("listBanner",bannerProjections.getContent());
        map.put("totalPage",bannerProjections.getTotalPages());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    public ResponseEntity<ServiceResponse> delete(Integer id) {
        ServiceResponse response = new ServiceResponse();
        try {
            bannerRepository.deleteById(id);
            response.setStatus("success");
            response.setData("Xóa thành công");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("errors");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Banner> findOne(Integer id) {
        return new ResponseEntity<>(bannerRepository.findById(id).get(),HttpStatus.OK);
    }
    public List<BannerDto> getAll(){
        return bannerRepository.findForIndex();
    }
    public List<BannerDto> filter(List<BannerDto> list,String index){
        list = list.stream().filter(bannerDto -> bannerDto.getType().equals(Short.parseShort(index))).collect(Collectors.toList());
        return list;
    }
}
