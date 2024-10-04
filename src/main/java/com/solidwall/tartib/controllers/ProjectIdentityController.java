package com.solidwall.tartib.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.solidwall.tartib.dto.project.identity.CreateDto;
import com.solidwall.tartib.dto.project.identity.UpdateDto;
import com.solidwall.tartib.entities.ProjectIdentityEntity;
import com.solidwall.tartib.implementations.ProjectIdentityImplementation;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("project-identity")
public class ProjectIdentityController {

  @Autowired
  ProjectIdentityImplementation projectIdentityImplementation;

  @GetMapping({ "all" })
  public ResponseEntity<CustomResponseHelper<List<ProjectIdentityEntity>>> findAll() {
    CustomResponseHelper<List<ProjectIdentityEntity>> response = CustomResponseHelper
        .<List<ProjectIdentityEntity>>builder()
        .body(projectIdentityImplementation.findAll())
        .message("project identity list")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @GetMapping({ "find" })
  public ResponseEntity<CustomResponseHelper<ProjectIdentityEntity>> findOne(@RequestParam Map<String, String> reqParam) {
    CustomResponseHelper<ProjectIdentityEntity> response = CustomResponseHelper
        .<ProjectIdentityEntity>builder()
        .body(projectIdentityImplementation.findOne(reqParam))
        .message("project identity found")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @GetMapping({ "get/{id}" })
  private ResponseEntity<CustomResponseHelper<ProjectIdentityEntity>> getOne(@PathVariable Long id) {
    CustomResponseHelper<ProjectIdentityEntity> response = CustomResponseHelper.<ProjectIdentityEntity>builder()
        .body(projectIdentityImplementation.getOne(id))
        .message("project identity data")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping({ "create" })
  private ResponseEntity<CustomResponseHelper<ProjectIdentityEntity>> create(@RequestBody CreateDto data) {
    CustomResponseHelper<ProjectIdentityEntity> response = CustomResponseHelper.<ProjectIdentityEntity>builder()
        .body(projectIdentityImplementation.create(data))
        .message("project identity created successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PutMapping({ "update/{id}" })
  private ResponseEntity<CustomResponseHelper<ProjectIdentityEntity>> update(@PathVariable("id") Long id,
      @RequestBody UpdateDto user) {
    CustomResponseHelper<ProjectIdentityEntity> response = CustomResponseHelper.<ProjectIdentityEntity>builder()
        .body(projectIdentityImplementation.update(id, user))
        .message("project identity updated successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @DeleteMapping({ "delete/{id}" })
  private ResponseEntity<CustomResponseHelper<Void>> delete(@PathVariable Long id) {
    projectIdentityImplementation.delete(id);
    CustomResponseHelper<Void> response = CustomResponseHelper.<Void>builder()
        .message("project identity deleted successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
