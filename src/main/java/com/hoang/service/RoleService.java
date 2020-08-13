package com.hoang.service;

import com.hoang.convertor.PermissionConvertor;
import com.hoang.dto.server.PermissionDto;
import com.hoang.dto.server.RoleDto;
import com.hoang.dto.server.RoleDtoEdit;
import com.hoang.entities.Permissions;
import com.hoang.entities.Roles;
import com.hoang.global.Utility;
import com.hoang.payload.request.RoleRequest;
import com.hoang.projections.RoleProjections;
import com.hoang.repository.permissions.IPermissionsRepository;
import com.hoang.repository.roles.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private IRolesRepository rolesRepository;
    @Autowired
    private IPermissionsRepository permissionsRepository;
    @Autowired
    private PermissionConvertor permissionConvertor;
    @Autowired
    private Utility utility;
    public ResponseEntity<List<RoleDto>> findAll(){
        List<RoleProjections> list = null;
        List<RoleDto> roles = new ArrayList<>();
        try {
            list = rolesRepository.findRoles();
            String[] strings = {};
            for (RoleProjections roleProjections:list){
                RoleDto roleDto = new RoleDto();
                strings = roleProjections.getPermissions().split("\\,");
                roleDto.setId(roleProjections.getId());
                roleDto.setName(roleProjections.getName());
                roleDto.setDisplayName(roleProjections.getDisplayName());
                roleDto.setList(strings);
                roles.add(roleDto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
    }
    public ResponseEntity<List<PermissionDto>> findSelect() {
        return new ResponseEntity<>(permissionsRepository.findDto(),HttpStatus.OK);
    }
    public ResponseEntity<ServiceResponse> create(RoleRequest roleRequest, Errors errors) {
        ServiceResponse response = new ServiceResponse();
        Roles roles = new Roles();
        try {
            if(errors.hasErrors()){
                response.setData(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()));
                response.setStatus("errors");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(roleRequest.getId() != null
                        ? utility.checkUnique(roleRequest.getName(),roleRequest.getId(),"role")
                        : utility.checkUnique(roleRequest.getName(),null,"role")){
                response.setStatus("error");
                response.setData("Tên đã tồn tại");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else if(roleRequest.getId() != null
                    ? utility.checkUnique(roleRequest.getDisplayName(),roleRequest.getId(),"role_display")
                    : utility.checkUnique(roleRequest.getDisplayName(),null,"role_display")){
                response.setStatus("error");
                response.setData("Tên thay thế đã tồn tại");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }else{
                response.setData(roleRequest.getId() != null ? "Cập nhật thành công" : "Thêm mới thành công");
                if(roleRequest.getId() != null){
                    roles.setId(roleRequest.getId());
                }
            }
            response.setStatus("success");
            roles.setName(roleRequest.getName());
            roles.setDisplayName(roleRequest.getDisplayName());
            roles.getPermissions().addAll(roleRequest.getPermission().stream().map(request -> {
                Permissions permissions = permissionsRepository.insert(request.getId());
                permissions.getRoles().add(roles);
                return permissions;
            }).collect(Collectors.toList()));
            rolesRepository.save(roles);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus("error");
            response.setData("Có lỗi xảy ra");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<RoleDtoEdit> findOne(Integer id) {
        RoleDtoEdit roleDto = new RoleDtoEdit();
        Roles roles = rolesRepository.findOne(id);
        roleDto.setId(roles.getId());
        roleDto.setName(roles.getName());
        roleDto.setDisplayName(roles.getDisplayName());
        roles.getPermissions().forEach(permissions -> {
            PermissionDto permissionDto = permissionConvertor.convertor(permissions);
            roleDto.getPermissions().add(permissionDto);
        });
        return new ResponseEntity<>(roleDto,HttpStatus.OK);
    }
}
