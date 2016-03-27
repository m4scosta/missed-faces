package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  User getByUsername(String username);
}
