package com.missedfaces.server.service.recognition;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.beans.MissedPerson;

import java.io.IOException;

public interface RecognitionService {

  void train() throws IOException;
  Detection tryToRecognizeDetectedFace(Detection detection) throws IOException;
  void updateTraining(MissedPerson person) throws IOException ;
}
