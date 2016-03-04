package com.missedfaces.server.service.detection;

import com.missedfaces.server.domain.beans.Detection;

public interface RecognitionService {

  void recognize(Detection detection);
}
