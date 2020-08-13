package com.hoang.controller.api.permissions;

import com.hoang.entities.Permissions;
import com.hoang.payload.request.PermissionRequest;
import com.hoang.repository.permissions.IPermissionsRepository;
import com.hoang.service.PermissionService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @GetMapping(value = "/permissions")
    @PreAuthorize("hasAnyRole('ADMINPERMISSION','MANAGER') or hasAnyAuthority('CREATE_PERMISSION','DELETE_PERMISSION','UPDATE_PERMISSION','READ_PERMISSION')")
    public ResponseEntity<Map<String,Object>> getAll(
            @RequestParam(value = "page",required = false,defaultValue = "0") Integer page
    ){
        return permissionService.getAll(page);
    }
    @GetMapping(value = "permissions/{id}")
    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    public ResponseEntity<Permissions> view(@PathVariable("id") Integer id){
        return permissionService.findOne(id);
    }
    @PostMapping(value = "/permissions")
    @PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody List<PermissionRequest> permissionRequests, Errors errors){
        return permissionService.createPermission(permissionRequests,errors);
    }
    @PutMapping(value = "/permissions")
    @PreAuthorize("hasAuthority('UPDATE_PERMISSION')")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody PermissionRequest permissionRequest,Errors errors){
        return permissionService.update(permissionRequest,errors);
    }
    @DeleteMapping(value = "/permissions/{id}")
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
        return permissionService.delete(id);
    }
    @PostMapping(value = "/permissions/multidelete")
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    public ResponseEntity<ServiceResponse> multidelete(@RequestBody Integer[] ids){
        return permissionService.multidelete(ids);
    }
}
