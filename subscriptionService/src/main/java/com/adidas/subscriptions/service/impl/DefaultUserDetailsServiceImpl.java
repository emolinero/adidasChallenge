package com.adidas.subscriptions.service.impl;

import com.adidas.subscriptions.model.DefaultUserPrincipal;
import com.adidas.subscriptions.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DefaultUserDetailsServiceImpl implements UserDetailsService {
  private static final Logger logger = LoggerFactory.getLogger(DefaultUserDetailsServiceImpl.class);

  @Autowired
  UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    logger.info("Get user data with USERNAME {}", username);
    com.adidas.subscriptions.model.User user = repository.findByUsername(username).orElse(null);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ADMIN"));

    return new DefaultUserPrincipal(new User(user.getUsername(), user.getPassword(), authorities));
  }
}