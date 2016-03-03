package com.missedfaces.server.service.detection.impl;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.service.detection.DetectionService;
import org.springframework.stereotype.Service;

@Service(value = "defaultDetectionService")
public class DefaultDetectionService implements DetectionService {

  @Override
  public void handleNewDetection(Detection detection) {
    System.out.println(detection);
  }
}
