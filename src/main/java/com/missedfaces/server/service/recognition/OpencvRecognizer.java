package com.missedfaces.server.service.recognition;

import org.bytedeco.javacpp.opencv_imgproc;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

@Component
public class OpencvRecognizer implements Recognizer {

  private static final int UNRECOGNIZED = -1;

  private boolean trained = false;

  private FaceRecognizer recognizer = createEigenFaceRecognizer(80, 6000);

  @Override
  public int recognize(byte[] image) throws IOException {
    if (image == null || !trained) {
      return UNRECOGNIZED;
    }
    int[] label = new int[1];
    double[] confidence = new double[1];
    Mat img = matFromByteArray(image);
    recognizer.predict(img, label, confidence);
    img.release();
    if (confidence[0] >= 80) {
      return label[0];
    }
    return UNRECOGNIZED;
  }

  @Override
  public void train(byte[][] images, int[] labels) throws IOException {
    if (images == null || labels == null || images.length != labels.length) {
      return;
    }
    MatVector cvImages = toCvImages(images);
    Mat cvLabels = toCvLabels(labels);
    recognizer.train(cvImages, cvLabels);
    trained = true;

    for (int i = 0; i < images.length; i++) {
      cvImages.get(i).release();
    }
    cvLabels.release();
  }

  @Override
  public void update(byte[][] images, int[] labels) throws IOException {
    MatVector cvImages = toCvImages(images);
    Mat cvLabels = toCvLabels(labels);
    recognizer.update(cvImages, cvLabels);
    for (int i = 0; i < images.length; i++) {
      cvImages.get(i).release();
    }
    cvLabels.release();
  }

  private MatVector toCvImages(byte[][] images) throws IOException {
    MatVector cvImages = new MatVector(images.length);
    for (int i = 0; i < images.length; i++) {
      cvImages.put(i, matFromByteArray(images[i]));
    }
    return cvImages;
  }

  @SuppressWarnings({"deprecation"})
  private Mat toCvLabels(int[] labels) {
    Mat cvLabels = new Mat(labels.length, 1, CV_32SC1);
    IntBuffer labelsBuffer = cvLabels.getIntBuffer();
    for (int i = 0; i < labels.length; i++) {
      labelsBuffer.put(i, labels[i]);
    }
    return cvLabels;
  }

  private Mat matFromByteArray(byte[] bytes) throws IOException {
    Path tempFile = Files.createTempFile("img", ".temp");
    Files.write(tempFile, bytes);
    Mat image = imread(tempFile.toAbsolutePath().toString(), CV_LOAD_IMAGE_GRAYSCALE);
    opencv_imgproc.resize(image, image, new Size(100, 100));
    Files.delete(tempFile);
    return image;
  }
}
