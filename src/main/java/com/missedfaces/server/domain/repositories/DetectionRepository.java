package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetectionRepository extends CrudRepository<Detection, Long> {

  List<Detection> findByPersonUser(User user);

  Detection getByIdAndPersonUser(Long id, User user);
}
