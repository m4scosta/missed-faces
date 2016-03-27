package com.missedfaces.server.service.accounts;

import com.missedfaces.server.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service(value = "userAccountsService")
public class UserAccountsService implements AccountsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public com.missedfaces.server.domain.beans.User currentUser() {
    com.missedfaces.server.domain.beans.User user = null;
    if (isAuthenticated()) {
      User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      user = userRepository.getByUsername(securityUser.getUsername());
    }
    return user;
  }

  @Override
  public boolean isAuthenticated() {
    return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
  }
}
