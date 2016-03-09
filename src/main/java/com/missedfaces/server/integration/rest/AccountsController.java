package com.missedfaces.server.integration.rest;

import com.missedfaces.server.domain.beans.Authority;
import com.missedfaces.server.domain.beans.User;
import com.missedfaces.server.domain.repositories.AuthorityRepository;
import com.missedfaces.server.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class AccountsController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthorityRepository authorityRepository;

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  @Transactional
  public ResponseEntity<Void> signUp(@RequestBody User user) {
    userRepository.save(user);
    Authority authority = new Authority();
    authority.setUsername(user.getUsername());
    authority.setAuthority("ROLE_USER");
    authorityRepository.save(authority);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
