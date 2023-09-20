package com.burakbayramin.bookstore.security;

import com.burakbayramin.bookstore.entity.User;
import com.burakbayramin.bookstore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByNameOrEmail(nameOrEmail, nameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with name or email" + nameOrEmail
                ));

        Set<GrantedAuthority> authorities = user.getRoles()
                .stream().map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                nameOrEmail, user.getPassword(), authorities
        );
    }
}
