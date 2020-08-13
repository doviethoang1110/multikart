package com.hoang.controller.api.role;

import com.hoang.dto.server.PermissionDto;
import com.hoang.dto.server.RoleDto;
import com.hoang.dto.server.RoleDtoEdit;
import com.hoang.payload.request.RoleRequest;
import com.hoang.service.RoleService;
import com.hoang.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
@RequestMapping(value = "/api/v1")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping(value = "roles")
    @PreAuthorize("hasAnyRole('ADMINROLE','MANAGER') or hasAnyAuthority('DELETE_ROLE','UPDATE_ROLE','READ_ROLE','CREATE_ROLE')")
    public ResponseEntity<List<RoleDto>> getAll(){
        return roleService.findAll();
    }

    @GetMapping(value = "select")
    @PreAuthorize("hasAnyAuthority('UPDATE_ROLE','READ_ROLE','DELETE_ROLE')")
    public ResponseEntity<List<PermissionDto>> getSelect(){return roleService.findSelect();}

    @GetMapping(value = "roles/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ROLE','READ_ROLE','DELETE_ROLE')")
    public ResponseEntity<RoleDtoEdit> findOne(@PathVariable("id") Integer id){
        return roleService.findOne(id);
    }

    @PostMapping(value = "roles")
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ResponseEntity<ServiceResponse> create(@Valid  @RequestBody RoleRequest roleRequest,Errors errors){
        return roleService.create(roleRequest,errors);
    }
    @PutMapping(value = "roles")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public ResponseEntity<ServiceResponse> update(@Valid @RequestBody RoleRequest roleRequest, Errors errors){
        return roleService.create(roleRequest,errors);
    }
}
