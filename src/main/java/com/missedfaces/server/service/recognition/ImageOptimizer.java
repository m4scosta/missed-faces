package com.missedfaces.server.service.recognition;

import org.bytedeco.javacpp.opencv_core.Mat;

public interface ImageOptimizer {

  void optimize(Mat image);
}
