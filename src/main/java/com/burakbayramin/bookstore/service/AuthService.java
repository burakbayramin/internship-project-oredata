package com.burakbayramin.bookstore.service;

import com.burakbayramin.bookstore.dto.LoginDto;
import com.burakbayramin.bookstore.dto.SignupDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String signup(SignupDto signupDto);
}
