package com.missedfaces.server.service.detection.impl;

import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.service.detection.RecognitionService;
import org.springframework.stereotype.Service;

import static org.bytedeco.javacpp.opencv_face.*;

@Service(value = "recognitionService")
public class RecognitionServiceImpl implements RecognitionService {

  FaceRecognizer faceRecognizer = createEigenFaceRecognizer();

  @Override
  public void recognize(Detection detection) {

  }
}
