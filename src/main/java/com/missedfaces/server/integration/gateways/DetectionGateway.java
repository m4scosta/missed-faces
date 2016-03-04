package com.missedfaces.server.integration.gateways;

import com.missedfaces.server.domain.beans.Detection;
import org.springframework.integration.annotation.Gateway;

public interface DetectionGateway {

  @Gateway(requestChannel = "detectionDTO.channel")
  void handleNewDetection(Detection detection);
}
