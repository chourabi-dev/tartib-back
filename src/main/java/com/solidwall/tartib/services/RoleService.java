package com.solidwall.tartib.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solidwall.tartib.core.exceptions.FoundException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.entities.RoleEntity;
import com.solidwall.tartib.implementations.RoleImplementation;
import com.solidwall.tartib.repositories.RoleRepository;

@Service
public class RoleService implements RoleImplementation {

  @Autowired
  RoleRepository roleRepository;

  @Override
  public List<RoleEntity> findAll() {
    if (!roleRepository.findAll().isEmpty()) {
      return roleRepository.findAll();
    } else {
      throw new NotFoundException("not exist any role ");
    }
  }

  @Override
  public RoleEntity getOne(Long id) {
    Optional<RoleEntity> role = roleRepository.findById(id);
    if (role.isPresent()) {
      return role.get();
    } else {
      throw new NotFoundException("role not exist");
    }
  }

  @Override
  public RoleEntity findOne(String name) {
    return roleRepository.findByName(name).get();
  }

  @Override
  public RoleEntity create(RoleEntity data) {
    Optional<RoleEntity> role = roleRepository.findByName(data.getName());
    if (!role.isPresent()) {
      return roleRepository.save(data);
    } else {
      throw new FoundException("role exist");
    }
  }

  @Override
  public RoleEntity update(Long id, RoleEntity data) {
    Optional<RoleEntity> role = roleRepository.findById(id);
    if (role.isPresent()) {
      RoleEntity updateRole = role.get();
      updateRole.setName(data.getName());
      updateRole.setActive(data.isActive());
      updateRole.setCreatedAt(data.getCreatedAt());
      updateRole.setDescription(data.getDescription());
      updateRole.setUpdatedAt(data.getUpdatedAt());
      return roleRepository.save(updateRole);
    } else {
      throw new NotFoundException("role not found");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<RoleEntity> role = roleRepository.findById(id);
    if (role.isPresent()) {
      roleRepository.deleteById(id);
    } else {
      throw new NotFoundException("role not exist");
    }
  }

}
