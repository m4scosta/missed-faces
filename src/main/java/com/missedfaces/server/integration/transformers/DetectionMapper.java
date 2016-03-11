package com.missedfaces.server.integration.transformers;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.dto.DetectionDTO;
import com.missedfaces.server.domain.dto.FaceDTO;
import com.missedfaces.server.domain.repositories.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("detectionMapper")
public class DetectionMapper {

  @Autowired
  private DetectorRepository detectorRepository;

  public Detection toBean(DetectionDTO dto) {
    Detection detection = new Detection();
    detection.setDetector(detectorRepository.findOne(dto.getDetectorId()));
    detection.setImage(dto.getFace().getData()); // TODO: verificar depois
    detection.setTime(new Date(dto.getTime()));
    return detection;
  }
}
