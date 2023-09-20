package com.burakbayramin.bookstore.service.impl;

import com.burakbayramin.bookstore.dto.LoginDto;
import com.burakbayramin.bookstore.dto.SignupDto;
import com.burakbayramin.bookstore.entity.Role;
import com.burakbayramin.bookstore.entity.User;
import com.burakbayramin.bookstore.exception.APIException;
import com.burakbayramin.bookstore.exception.ResourceNotFoundException;
import com.burakbayramin.bookstore.repository.RoleRepository;
import com.burakbayramin.bookstore.repository.UserRepository;
import com.burakbayramin.bookstore.security.JwtTokenProvider;
import com.burakbayramin.bookstore.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getNameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String signup(SignupDto signupDto) {

        if (userRepository.existsByEmail(signupDto.getEmail())){
            throw new APIException(HttpStatus.BAD_REQUEST, "email is already exists");
        }

        User user = new User();
        user.setName(signupDto.getName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User Signup success";
    }
}
