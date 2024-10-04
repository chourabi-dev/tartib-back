package com.solidwall.tartib.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.solidwall.tartib.core.helpers.CustomResponseHelper;
import com.solidwall.tartib.entities.RoleEntity;
import com.solidwall.tartib.implementations.RoleImplementation;

@RestController
@RequestMapping("role")
public class RoleController {

  @Autowired
  RoleImplementation roleImplementation;

  @GetMapping("all")
  public ResponseEntity<CustomResponseHelper<List<RoleEntity>>> findAll() {
    CustomResponseHelper<List<RoleEntity>> response = CustomResponseHelper.<List<RoleEntity>>builder()
        .body(roleImplementation.findAll())
        .message("role list")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @GetMapping({ "get/{id}" })
  private ResponseEntity<CustomResponseHelper<RoleEntity>> getOne(@PathVariable Long id) {
    CustomResponseHelper<RoleEntity> response = CustomResponseHelper.<RoleEntity>builder()
        .body(roleImplementation.getOne(id))
        .message("role information")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping({ "create" })
  private ResponseEntity<CustomResponseHelper<RoleEntity>> create(@RequestBody RoleEntity role) {
    CustomResponseHelper<RoleEntity> response = CustomResponseHelper.<RoleEntity>builder()
        .body(roleImplementation.create(role))
        .message("role created successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PutMapping({ "update/{id}" })
  private ResponseEntity<CustomResponseHelper<RoleEntity>> update(@PathVariable("id") Long id,
      @RequestBody RoleEntity role) {
    CustomResponseHelper<RoleEntity> response = CustomResponseHelper.<RoleEntity>builder()
        .body(roleImplementation.update(id, role))
        .message("role updated successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @DeleteMapping({ "delete/{id}" })
  private ResponseEntity<CustomResponseHelper<Void>> delete(@PathVariable Long id) {
    roleImplementation.delete(id);
    CustomResponseHelper<Void> response = CustomResponseHelper.<Void>builder()
        .message("role deleted successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

}
