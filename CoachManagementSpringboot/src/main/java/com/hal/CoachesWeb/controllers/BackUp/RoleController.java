package com.hal.CoachesWeb.controllers.BackUp;

import com.hal.CoachesWeb.entity.Role;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllRole(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Get role success", roleService.getAllRole())
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getRoleById(@PathVariable int id){
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Find role success", role)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Can not find role id", "")
        );
    }
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addRole(@RequestBody Role role){
        if (roleService.addRole(role)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Add role success", role)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Can not add role", "")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateRole(@RequestBody Role role){
        if (roleService.getRoleById(role.getId()).isPresent()){
            if (roleService.updateRole(role)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Update role success", role)
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Can not update role","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Can not found role id= "+role.getId(),"")
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteRole(@PathVariable int id){
        if (roleService.getRoleById(id).isPresent()){
            if (roleService.deleteRole(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Delete role success", "")
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Can not delete role", "")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Can not found role id", "")
        );
    }
}
