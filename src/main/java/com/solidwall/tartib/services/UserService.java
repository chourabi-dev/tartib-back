package com.solidwall.tartib.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solidwall.tartib.core.exceptions.FoundException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.entities.UserEntity;
import com.solidwall.tartib.implementations.UserImplementation;
import com.solidwall.tartib.repositories.RoleRepository;
import com.solidwall.tartib.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService implements UserImplementation {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserEntity getOne(Long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new NotFoundException("user not exist");
    }
  }

  @Override
  public UserEntity findOne() {
    throw new UnsupportedOperationException("Unimplemented method 'findOne'");
  }

  @Override
  public List<UserEntity> findAll() {
    if (!userRepository.findAll().isEmpty()) {
      return userRepository.findAll();
    } else {
      throw new NotFoundException("not exist any user ");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      userRepository.deleteById(id);
    } else {
      throw new NotFoundException("user not exist");
    }
  }

  @Override
  public UserEntity create(UserEntity data) {
    Optional<UserEntity> user = userRepository.findByEmail(data.getEmail());
    //RoleEntity role = roleRepository.findById(1L).get();
    
    if (!user.isPresent()) {
      UserEntity newUser = new UserEntity();
      newUser.setUsername(data.getUsername());
      newUser.setFirstname(data.getFirstname());
      newUser.setLastname(data.getLastname());
      newUser.setEmail(data.getEmail());
      newUser.setPassword(passwordEncoder.encode(data.getPassword()));

      //newUser.setRoles(Collections.singletonList(role));

      return userRepository.save(newUser);
    } else {
      throw new FoundException("user exist");
    }
  }

  @Override
  public UserEntity update(Long id, UserEntity data) {
    Optional<UserEntity> user = userRepository.findById(id);
    if (user.isPresent()) {
      UserEntity updateUser = user.get();
      updateUser.setUsername(data.getUsername());
      updateUser.setFirstname(data.getFirstname());
      updateUser.setLastname(data.getLastname());
      updateUser.setEmail(data.getEmail());
      updateUser.setPassword(passwordEncoder.encode(data.getPassword()));
      return userRepository.save(updateUser);
    } else {
      throw new NotFoundException("user not found");
    }
  }

}
