package com.missedfaces.server.api;

import com.missedfaces.server.domain.beans.User;
import com.missedfaces.server.domain.repositories.DetectionRepository;
import com.missedfaces.server.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class HomeController {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private DetectionRepository detectionRepository;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  private String home(Model model) {
    User user = accountsService.currentUser();
    int registeredCount = user.getMissedPersons().size();
    int foundCount = detectionRepository.findByPersonUser(user).size();
    int notificationsCount = user.getNotifications().size();
    model.addAttribute("missedPersonCount", registeredCount);
    model.addAttribute("personFoundCount", foundCount);
    model.addAttribute("notificationsCount", notificationsCount);
    return "home";
  }
}
