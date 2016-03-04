package com.missedfaces.server.integration.transformers;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.dto.DetectionDto;
import com.missedfaces.server.domain.repositories.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("detectionMapper")
public class DetectionMapper {

  @Autowired
  private DetectorRepository detectorRepository;

  public Detection toBean(DetectionDto dto) {
    Detection detection = new Detection();
    detection.setDetector(detectorRepository.findOne(dto.getDetectorId()));
    detection.setImage(dto.getFace());
    detection.setTime(new Date(dto.getTime()));
    return detection;
  }

  public DetectionDto toDto(Detection bean) {
    DetectionDto detectionDto = new DetectionDto();
    detectionDto.setDetectorId(bean.getDetector().getId());
    detectionDto.setFace(bean.getImage());
    detectionDto.setTime(bean.getTime().getTime());
    return detectionDto;
  }
}
