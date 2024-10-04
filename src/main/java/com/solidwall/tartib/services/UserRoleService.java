package com.solidwall.tartib.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.FoundException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.dto.userrole.CreateDto;
import com.solidwall.tartib.dto.userrole.UpdateDto;
import com.solidwall.tartib.entities.RoleEntity;
import com.solidwall.tartib.entities.UserEntity;
import com.solidwall.tartib.entities.UserRoleEntity;
import com.solidwall.tartib.implementations.UserRoleImplementation;
import com.solidwall.tartib.repositories.RoleRepository;
import com.solidwall.tartib.repositories.UserRepository;
import com.solidwall.tartib.repositories.UserRoleRepository;

@Service
public class UserRoleService implements UserRoleImplementation {

  @Autowired
  UserRoleRepository userRoleRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public List<UserRoleEntity> findAll() {
    if (!userRoleRepository.findAll().isEmpty()) {
      return userRoleRepository.findAll();
    } else {
      throw new NotFoundException("not exist any user role ");
    }
  }

  @Override
  public UserRoleEntity getOne(Long id) {
    Optional<UserRoleEntity> userRole = userRoleRepository.findById(id);
    if (userRole.isPresent()) {
      return userRole.get();
    } else {
      throw new NotFoundException("user role not exist");
    }
  }

  @Override
  public UserRoleEntity findOne() {
    return null;
  }

  @Override
  public UserRoleEntity create(CreateDto data) {

    Optional<UserEntity> user = userRepository.findById(data.getUser());
    Optional<RoleEntity> role = roleRepository.findById(data.getRole());
    Optional<UserRoleEntity> userRole = userRoleRepository.findByUserAndRole(user.get(),role.get());

    if (!userRole.isPresent()) {

      UserRoleEntity newUserRole = new UserRoleEntity();
      newUserRole.setUser(user.get());
      newUserRole.setRole(role.get());

      return userRoleRepository.save(newUserRole);

    } else {
      throw new FoundException("user role already exist");
    }

  }

  @Override
  public UserRoleEntity update(Long id, UpdateDto data) {
    
    Optional<UserEntity> user = userRepository.findById(data.getUser());
    Optional<RoleEntity> role = roleRepository.findById(data.getRole());
    Optional<UserRoleEntity> userRole = userRoleRepository.findById(id);

    if (userRole.isPresent()) {

      UserRoleEntity updateUserRole = userRole.get();
      updateUserRole.setUser(user.get());
      updateUserRole.setRole(role.get());

      return userRoleRepository.save(updateUserRole);

    } else {
      throw new FoundException("user role not exist");
    }

  }

  @Override
  public void delete(Long id) {
    Optional<UserRoleEntity> userRole = userRoleRepository.findById(id);
    if (userRole.isPresent()) {
      userRoleRepository.deleteById(id);
    } else {
      throw new NotFoundException("user role not exist");
    }
  }

}
