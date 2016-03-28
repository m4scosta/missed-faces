package com.missedfaces.server.api;

import com.missedfaces.server.domain.beans.Notification;
import com.missedfaces.server.domain.repositories.NotificationRepository;
import com.missedfaces.server.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/notificacoes")
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class NotificationsController {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private NotificationRepository notificationRepository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("notifications", accountsService.currentUser().getNotifications());
    return "notifications/index";
  }

  @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
  @Transactional
  public ResponseEntity<Void> edit(@PathVariable Long id) {
    Notification notification = notificationRepository.findByIdAndUser(id, accountsService.currentUser());
    if (notification != null) {
      notificationRepository.delete(notification);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/novo", method = RequestMethod.GET)
  public String addNew() {
    return "notifications/new";
  }

  @RequestMapping(value = "/novo", method = RequestMethod.POST)
  public ResponseEntity<Void> saveNew(@RequestBody Notification notification) {
    notification.setUser(accountsService.currentUser());
    notificationRepository.save(notification);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
