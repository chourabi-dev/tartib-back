package com.solidwall.tartib.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.solidwall.tartib.core.helpers.CustomResponseHelper;
import com.solidwall.tartib.dao.AuthDao;
import com.solidwall.tartib.dto.auth.SigninDto;
import com.solidwall.tartib.dto.auth.TokenDto;
import com.solidwall.tartib.implementations.AuthImplementation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("auth")
public class AuthController {

  @Autowired
  AuthImplementation authImplementation;

  @PostMapping("signin")
  public ResponseEntity<CustomResponseHelper<AuthDao>> signin(@Valid @RequestBody SigninDto signinDto) {
    CustomResponseHelper<AuthDao> response = CustomResponseHelper.<AuthDao>builder()
        .body(authImplementation.signin(signinDto))
        .message("user signin successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping("signin-with-token")
  public ResponseEntity<CustomResponseHelper<AuthDao>> signinWithToken(@RequestBody TokenDto tokenDto) {
    CustomResponseHelper<AuthDao> response = CustomResponseHelper.<AuthDao>builder()
        .body(authImplementation.signinWithToken(tokenDto.getAccessToken()))
        .message("user signin successfully")
        .error(false)
        .status(HttpStatus.OK.value())
        .timestamp(new Date())
        .build();
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping("signup")
  public String signup() {
    return "login";
  }

  @PostMapping("forgot-password")
  public String forgotPassword() {
    return "login";
  }

  @PostMapping("resend-code")
  public String resendCode() {
    return "login";
  }

  @PostMapping("verify-code")
  public String verifyCode() {
    return "login";
  }

  @PostMapping("reset-password")
  public String resetPassword() {
    return "login";
  }

  @PostMapping("refresh-token")
  public String refreshToken() {
    return "login";
  }

  @GetMapping("current")
  public String current() {
    return "login";
  }

}
