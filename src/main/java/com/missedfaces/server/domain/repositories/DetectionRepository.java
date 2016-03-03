package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.Detection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectionRepository extends CrudRepository<Detection, Long> {

}
