package com.solidwall.tartib.implementations;

import java.util.List;
import com.solidwall.tartib.entities.RoleEntity;

public interface RoleImplementation {

  List<RoleEntity> findAll();

  RoleEntity findOne(String name);

  RoleEntity getOne(Long id);

  RoleEntity create(RoleEntity data);

  RoleEntity update(Long id, RoleEntity data);

  void delete(Long id);

}
