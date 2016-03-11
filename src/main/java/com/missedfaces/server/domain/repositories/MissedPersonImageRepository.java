package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.MissedPersonImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissedPersonImageRepository extends CrudRepository<MissedPersonImage, Long> {
}
