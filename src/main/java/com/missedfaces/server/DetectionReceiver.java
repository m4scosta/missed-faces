package com.missedfaces.server;

import com.missedfaces.server.domain.beans.Detection;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetectionReceiver {

  @RequestMapping(value = "/test", method = RequestMethod.POST)
  public String receive(@RequestBody Detection detection) {
    System.out.println("REST " + detection);
    return detection.toString();
  }
}
