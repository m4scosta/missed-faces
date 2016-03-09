package com.missedfaces.server.domain.dto;

import lombok.Data;

public @Data class DetectionDto {

  private Long detectorId;
  private Long time;
  private byte[] face;
}
