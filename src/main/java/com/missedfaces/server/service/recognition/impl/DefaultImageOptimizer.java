package com.missedfaces.server.service.recognition.impl;

import com.missedfaces.server.service.recognition.ImageOptimizer;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_imgproc;
import org.springframework.stereotype.Component;

import static org.bytedeco.javacpp.opencv_core.*;

@Component
public class DefaultImageOptimizer implements ImageOptimizer{

  @Override
  public void optimize(Mat image) {
    applyDifferenceOfGaussians(image);
    applyContrastEqualization(image);
  }

  private void applyDifferenceOfGaussians(Mat image) {
    Mat blur1 = new Mat();
    Mat blur2 = new Mat();
    opencv_imgproc.blur(image, blur1, new Size(2, 2));
    opencv_imgproc.blur(image, blur2, new Size(4, 4));
    subtract(blur2, blur1, image);
  }

  private void applyContrastEqualization(Mat image) {
    image.convertTo(image, CV_8UC1);
    opencv_imgproc.equalizeHist(image, image);
  }
}
