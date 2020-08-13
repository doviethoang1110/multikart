package com.hoang.convertor;

import com.hoang.dto.server.PermissionDto;
import com.hoang.entities.Permissions;
import com.hoang.payload.request.PermissionRequest;
import org.springframework.stereotype.Component;

@Component
public class PermissionConvertor {
    public Permissions convertor(PermissionRequest permissionRequest){
        Permissions permissions = new Permissions();
        if(permissionRequest.getId()!=null){
            permissions.setId(permissionRequest.getId());
        }
        permissions.setName(permissionRequest.getName());
        permissions.setDisplayName(permissionRequest.getDisplayName());
        return permissions;
    }
    public PermissionDto convertor(Permissions permissions){
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permissions.getId());
        permissionDto.setDisplayName(permissions.getDisplayName());
        return permissionDto;
    }
}
