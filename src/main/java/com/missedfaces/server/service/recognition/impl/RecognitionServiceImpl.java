package com.missedfaces.server.service.recognition.impl;

import com.google.common.collect.Lists;
import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.beans.MissedPerson;
import com.missedfaces.server.domain.repositories.MissedPersonRepository;
import com.missedfaces.server.service.recognition.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

@SuppressWarnings("deprecation")
@Service(value = "recognitionService")
public class RecognitionServiceImpl implements RecognitionService {

  FaceRecognizer recognizer = createEigenFaceRecognizer(80, 6000);

  @Autowired
  public RecognitionServiceImpl(MissedPersonRepository repository) {
    try {
      List<MissedPerson> missedPersons = Lists.newArrayList(repository.findAll());

      if (missedPersons.size() == 0)
        return;

      MatVector images = new MatVector(missedPersons.size());
      Mat labels = new Mat(missedPersons.size(), 1, CV_32SC1);
      IntBuffer labelsBuffer = labels.getIntBuffer();

      for (MissedPerson person : missedPersons) {
        images.put(person.getCounter().intValue(), matFromByteArray(person.getImage()));
        labelsBuffer.put(person.getCounter().intValue(), person.getId().intValue());
      }

      recognizer.train(images, labels);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int recognize(Detection detection) throws IOException {
    int[] label = new int[1];
    double[] confidence = new double[1];
    recognizer.predict(matFromByteArray(detection.getImage()), label, confidence);

    if (confidence[0] >= 80) {
      return label[0];
    }

    throw new RuntimeException("No recognized"); // TODO: trocar por excecao
  }

  @Override
  public void update(MissedPerson person) throws IOException {
    MatVector images = new MatVector(1);
    Mat labels = new Mat(1, 1, CV_32SC1);
    IntBuffer labelsBuffer = labels.getIntBuffer();
    labelsBuffer.put(person.getCounter().intValue(), person.getId().intValue());
    images.put(person.getCounter(), matFromByteArray(person.getImage()));
  }

  private Mat matFromByteArray(byte[] bytes) throws IOException {
    Path tempFile = Files.createTempFile("img", ".temp");
    Files.write(tempFile, bytes);
    Mat image = imread(tempFile.toAbsolutePath().toString(), CV_LOAD_IMAGE_GRAYSCALE);
    Files.delete(tempFile);
    return image;
  }
}
