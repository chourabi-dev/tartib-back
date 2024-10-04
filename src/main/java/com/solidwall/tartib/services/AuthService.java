package com.solidwall.tartib.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.configs.CustomUserDetailsService;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.core.utils.JwtUtil;
import com.solidwall.tartib.dao.AuthDao;
import com.solidwall.tartib.dto.auth.SigninDto;
import com.solidwall.tartib.entities.ArticleEntity;
import com.solidwall.tartib.entities.UserEntity;
import com.solidwall.tartib.implementations.AuthImplementation;
import com.solidwall.tartib.repositories.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthService implements AuthImplementation {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Override
  public AuthDao signin(SigninDto signinDto) {

    Optional<UserEntity> user = userRepository.findByUsername(signinDto.getUsername());

    if (user.isPresent()) {

      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String accessToken = jwtUtil.generateToken(authentication.getName());

      System.out.println(accessToken);

      AuthDao authDao = AuthDao.fromUserEntity(user.get(), accessToken);

      return authDao;

    } else {
      throw new NotFoundException("user not found");
    }
  }

  @Override
  public UserEntity signup() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'signup'");
  }

  @Override
  public UserEntity forgotPassword() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'forgotPassword'");
  }

  @Override
  public UserEntity resendCode(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resendCode'");
  }

  @Override
  public UserEntity verifyCode(ArticleEntity user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'verifyCode'");
  }

  @Override
  public UserEntity resetPassword(Long id, ArticleEntity user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resetPassword'");
  }

  @Override
  public AuthDao signinWithToken(String token) {

    String username = jwtUtil.extractUsername(token);

    UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

    if (jwtUtil.validateToken(token, userDetails.getUsername())) {

      Optional<UserEntity> user = userRepository.findByUsername(userDetails.getUsername());
      if (user.isPresent()) {

        String accessToken = jwtUtil.generateToken(user.get().getUsername());

        System.out.println(accessToken);

        AuthDao authDao = AuthDao.fromUserEntity(user.get(), accessToken);

        return authDao;

      } else {
        throw new NotFoundException("user not found");
      }
    } else {
      throw new NotFoundException("user not found");
    }

    /*
     * String username = jwtUtil.extractUsername(token);
     * 
     * UserDetails userDetails =
     * this.customUserDetailsService.loadUserByUsername(username);
     * 
     * if (jwtUtil.validateToken(token, userDetails.getUsername())) {}
     * 
     * Optional<UserEntity> user = userRepository.findByUsername(username);
     * 
     * if(user.isPresent()) {
     * 
     * String accessToken = jwtUtil.generateToken(user.get().getUsername());
     * 
     * System.out.println(accessToken);
     * 
     * AuthDao authDao = AuthDao.fromUserEntity(user.get(), accessToken);
     * 
     * return authDao;
     * 
     * } else {
     * throw new NotFoundException("user not found");
     * }
     */
  }

}
