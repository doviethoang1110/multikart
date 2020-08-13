package com.hoang.global;

import com.hoang.dto.client.CategoryDto;
import com.hoang.service.BrandService;
import com.hoang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
public class GlobalModel {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    private HttpServletRequest request;

    public GlobalModel(HttpServletRequest request) {
        this.request = request;
    }

    @ModelAttribute
    public void handleRequest(HttpSession session){
        List<CategoryDto> list = categoryService.getAllForClient();
        session.setAttribute("listCat",list);
        session.setAttribute("listBrand",brandService.findAll());
    }

    public void createMenu(List<CategoryDto> list, int parent_id, JspWriter out, String tag, String tag1){
        List<CategoryDto> cat_child = new ArrayList<>();
        Map<Integer,CategoryDto> map = new HashMap<>();
        try{
            list.forEach(e->{
                map.put(e.getId(),e);
            });
            map.forEach((k,v)->{
                if(v.getParentId()==parent_id){
                    cat_child.add(v);
                    list.remove(v);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        if(cat_child.size()>0){
            try {
                out.println(tag);
                for (CategoryDto category:cat_child){
                    String url = "<a href="+request.getContextPath()+"/danh-muc/"+category.getSlug()+">";
                    out.println("<li>");
                    out.println(url);
                    out.println(category.getName());
                    out.println("</a>");
                    createMenu(list,category.getId(),out,"<ul>","</ul>");
                    out.println("</li>");
                }
                out.println(tag1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void upload(MultipartFile multipartFile,String path){
        if(multipartFile!=null){
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            return;
        }
    }
}