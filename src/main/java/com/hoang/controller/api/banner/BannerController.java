package com.hoang.controller.api.banner;

import com.hoang.entities.Banner;
import com.hoang.payload.request.BannerRequest;
import com.hoang.projections.BannerProjection;
import com.hoang.service.BannerService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@Controller
@RequestMapping(value = "/api/v1")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @PreAuthorize("hasAuthority('READ_BANNER')")
    @GetMapping(value = "banners")
    public ResponseEntity<Map<String,Object>> findAll(
            @RequestParam(value = "page",defaultValue = "0",required = false) Integer page){
        return bannerService.findAll(page);
    }
    @PreAuthorize("hasAuthority('READ_BANNER')")
    @GetMapping(value = "banners/{id}")
    public ResponseEntity<Banner> findOne(@PathVariable("id") Integer id){
        return bannerService.findOne(id);
    }
    @PostMapping(value = "banners")
    @PreAuthorize("hasAuthority('CREATE_BANNER')")
    public ResponseEntity<ServiceResponse> create(@Valid @ModelAttribute BannerRequest request, Errors errors, HttpServletRequest servletRequest){
        return bannerService.create(request,errors,servletRequest);
    }
    @PreAuthorize("hasAuthority('DELETE_BANNER')")
    @DeleteMapping(value = "banners/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
        return bannerService.delete(id);
    }
}
