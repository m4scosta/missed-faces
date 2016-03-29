package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.MissedPerson;
import com.missedfaces.server.domain.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissedPersonRepository extends CrudRepository<MissedPerson, Long> {

  MissedPerson getByCounter(Long counter);

  List<MissedPerson> findByUser(User user);

  MissedPerson findByIdAndUser(Long id, User user);
}
