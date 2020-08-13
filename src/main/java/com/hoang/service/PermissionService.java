package com.hoang.service;

import com.hoang.convertor.PermissionConvertor;
import com.hoang.entities.Brand;
import com.hoang.entities.Permissions;
import com.hoang.global.Utility;
import com.hoang.payload.request.PermissionRequest;
import com.hoang.repository.permissions.IPermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private IPermissionsRepository permissionsRepository;
    @Autowired
    private PermissionConvertor permissionConvertor;
    @Autowired
    private Utility utility;
    public ResponseEntity<Map<String,Object>> getAll(Integer page){
        Map<String,Object> map = new HashMap<>();
        Page<Permissions> permissions = permissionsRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page,5));
        map.put("totalPage",permissions.getTotalPages());
        map.put("listPermissions",permissions.getContent());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    public ResponseEntity<Permissions> findOne(Integer id){
        return new ResponseEntity<>(permissionsRepository.findOneById(id),HttpStatus.OK);
    }
    public ResponseEntity<ServiceResponse> createPermission(List<PermissionRequest> permissionRequests, Errors errors) {
        ServiceResponse response = new ServiceResponse<>();
            if(errors.hasErrors()){
                response.setStatus("errors");
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                List<Permissions> list = new ArrayList<>();
                for (PermissionRequest permissionRequest:permissionRequests){
                    if(utility.checkUnique(permissionRequest.getName(),null,"permission")){
                        response.setData(permissionRequest.getName()+" đã tồn tại");
                        response.setStatus("error");
                        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                    }else {
                        Permissions permissions = permissionConvertor.convertor(permissionRequest);
                        list.add(permissions);
                    }
                }
                permissionsRepository.saveAll(list);
                response.setData("Thêm mới thành công");
                response.setStatus("success");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
    }

    public ResponseEntity<ServiceResponse> delete(Integer id) {
        ServiceResponse<String> response = new ServiceResponse<>();
        try {
            permissionsRepository.deleteById(id);
            response.setData("Xóa thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.setData("Xóa thất bại");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ServiceResponse> multidelete(Integer[] ids) {
        ServiceResponse<String> response = new ServiceResponse<>();
        try {
            for (Integer id:ids){
                permissionsRepository.delete(permissionsRepository.findOneById(id));
            }
            response.setData("Xóa thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            response.setData("Xóa thất bại");
            response.setStatus("error");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ServiceResponse> update(PermissionRequest permissionRequest,Errors errors) {
        ServiceResponse response = new ServiceResponse<>();
        if(errors.hasErrors()){
            response.setStatus("errors");
            response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } else if(utility.checkUnique(permissionRequest.getName(),permissionRequest.getId(),"permission")){
            response.setData("Tên đã tồn tại");
            response.setStatus("error");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }else{
            permissionsRepository.save(permissionConvertor.convertor(permissionRequest));
            response.setData("Cập nhật thành công");
            response.setStatus("success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
