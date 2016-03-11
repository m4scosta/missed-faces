package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.MissedPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissedPersonRepository extends CrudRepository<MissedPerson, Long> {

  MissedPerson getByCounter(Long counter);
}
