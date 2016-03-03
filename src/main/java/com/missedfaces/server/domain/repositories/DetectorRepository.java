package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.Detector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectorRepository extends CrudRepository<Detector, Long> {

}
