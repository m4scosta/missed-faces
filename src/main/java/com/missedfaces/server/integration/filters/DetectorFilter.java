package com.missedfaces.server.integration.filters;

import com.missedfaces.server.domain.dto.DetectionDto;
import com.missedfaces.server.domain.repositories.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("detectorFilter")
public class DetectorFilter {

  @Autowired
  private DetectorRepository detectorRepository;

  public boolean validateDetectorId(DetectionDto dto) {
    return detectorRepository.exists(dto.getDetectorId());
  }
}
