package com.missedfaces.server.service.recognition;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.beans.MissedPerson;

import java.io.IOException;

public interface RecognitionService {

  int recognize(Detection detection) throws IOException;
  void update(MissedPerson person) throws IOException ;
}
