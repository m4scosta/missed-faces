package com.missedfaces.server.api;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.repositories.DetectionRepository;
import com.missedfaces.server.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/encontrados")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class PersonFoundController {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private DetectionRepository detectionRepository;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String detections(Model model) {
    List<Detection> detections = detectionRepository.findByPersonUser(accountsService.currentUser());
    model.addAttribute("detections", detections);
    return "found_people";
  }

  @RequestMapping(value = "/detalhes/{personId}", method = RequestMethod.GET)
  public String details(@PathVariable Long personId, Model model) {
    Detection detection = detectionRepository.getByIdAndPersonUser(personId, accountsService.currentUser());
    model.addAttribute("detection", detection);
    return "found_people_details";
  }
}
