package com.missedfaces.server.integration.filters;

import com.missedfaces.server.domain.dto.DetectionDTO;
import com.missedfaces.server.domain.repositories.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("detectorFilter")
public class DetectorFilter {

  @Autowired
  private DetectorRepository detectorRepository;

  public boolean validateDetectorId(DetectionDTO dto) {
    return detectorRepository.exists(dto.getDetectorId());
  }
}
