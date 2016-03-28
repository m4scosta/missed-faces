package com.missedfaces.server.domain.repositories;

import com.missedfaces.server.domain.beans.Notification;
import com.missedfaces.server.domain.beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

  List<Notification> findByUser(User user);

  Notification findByIdAndUser(Long id, User user);
}
