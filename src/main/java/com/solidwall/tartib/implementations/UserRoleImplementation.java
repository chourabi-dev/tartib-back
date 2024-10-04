package com.solidwall.tartib.implementations;

import java.util.List;

import com.solidwall.tartib.dto.userrole.CreateDto;
import com.solidwall.tartib.dto.userrole.UpdateDto;
import com.solidwall.tartib.entities.UserRoleEntity;

public interface UserRoleImplementation {

  List<UserRoleEntity> findAll();

  UserRoleEntity getOne(Long id);

  UserRoleEntity findOne();

  UserRoleEntity create(CreateDto data);

  UserRoleEntity update(Long id, UpdateDto data);

  void delete(Long id);
  
}
