package com.hoang.convertor;

import com.hoang.entities.Banner;
import com.hoang.payload.request.BannerRequest;
import com.hoang.repository.banner.IBannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BannerConvertor {
    @Autowired
    private IBannerRepository bannerRepository;
    public Banner convertor(BannerRequest request){
        Banner banner = new Banner();
        if(request.getId()!=0){
            banner = bannerRepository.findById(request.getId()).get();
        }
        banner.setTitle(request.getTitle());
        banner.setContent(request.getContent());
        banner.setLinks(request.getLinks());
        banner.setStatus(request.getStatus());
        banner.setType(request.getType());
        if(request.getImage()!=null){
            banner.setImage(request.getImage().getOriginalFilename());
        }
        return banner;
    }
}
