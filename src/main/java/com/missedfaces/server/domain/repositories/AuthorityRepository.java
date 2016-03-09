package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String> {

}
