package com.missedfaces.server.domain.dto;

import lombok.Data;

@Data
public class FaceDTO {
  private byte[] data;
  private int width;
  private int height;
}
