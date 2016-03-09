package com.missedfaces.server.service.accounts;


import com.missedfaces.server.domain.beans.User;

public interface AccountsService {

  User currentUser();
  boolean isAuthenticated();
}
