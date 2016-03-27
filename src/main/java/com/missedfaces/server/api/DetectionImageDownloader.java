package com.missedfaces.server.api;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.repositories.DetectionRepository;
import com.missedfaces.server.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DetectionImageDownloader {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private DetectionRepository detectionRepository;

  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  @RequestMapping("/detection/{id}/image")
  private void image(@PathVariable Long id, HttpServletResponse response) {
    Detection detection = detectionRepository.getByIdAndPersonUser(id, accountsService.currentUser());
    try {
      response.setContentType("image/jpg");
      response.getOutputStream().write(detection.getImage());
      response.flushBuffer();
    } catch (IOException ex) {
      throw new RuntimeException("IOError writing file to output stream");
    }
  }
}
