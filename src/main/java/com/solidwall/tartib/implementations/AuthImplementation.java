package com.solidwall.tartib.implementations;

import com.solidwall.tartib.dao.AuthDao;
import com.solidwall.tartib.dto.auth.SigninDto;
import com.solidwall.tartib.entities.ArticleEntity;
import com.solidwall.tartib.entities.UserEntity;

public interface AuthImplementation {

  AuthDao signin(SigninDto signinDto);

  AuthDao signinWithToken(String token);

  UserEntity signup();

  UserEntity forgotPassword();

  UserEntity resendCode(Long id);

  UserEntity verifyCode(ArticleEntity data);

  UserEntity resetPassword(Long id, ArticleEntity data);

}
