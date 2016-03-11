package com.missedfaces.server.domain.dto;

import lombok.Data;

public @Data class DetectionDTO {
  private Long detectorId;
  private Long time;
  private FaceDTO face;
}
