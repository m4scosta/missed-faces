package com.missedfaces.server.service.recognition.impl;

import com.google.common.collect.Lists;
import com.missedfaces.server.domain.beans.Detection;
import com.missedfaces.server.domain.beans.MissedPerson;
import com.missedfaces.server.domain.repositories.MissedPersonRepository;
import com.missedfaces.server.service.recognition.RecognitionService;
import com.missedfaces.server.service.recognition.Recognizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("deprecation")
@Service(value = "recognitionService")
public class RecognitionServiceImpl implements RecognitionService {

  private final Recognizer recognizer;

  private final MissedPersonRepository repository;

  @Autowired
  public RecognitionServiceImpl(Recognizer recognizer, MissedPersonRepository repository) {
    this.recognizer = recognizer;
    this.repository = repository;
  }

  @Override
  public Detection tryToRecognizeDetectedFace(Detection detection) throws IOException {
    int id = recognizer.recognize(detection.getImage());
    detection.setPerson(repository.findOne((long) id));
    return detection;
  }

  public void train() throws IOException {
    List<MissedPerson> persons = Lists.newArrayList(repository.findAll());
    if (!persons.isEmpty()) {
      int totalImages = persons.stream().mapToInt(p -> p.getImages().size()).sum();
      final AtomicInteger index = new AtomicInteger(0);
      byte[][] images = new byte[totalImages][];
      int[] labels = new int[totalImages];
      persons.forEach(p -> p.getImages().forEach(i -> {
        images[index.get()] = i.getData();
        labels[index.getAndIncrement()] = p.getId().intValue();
      }));
      recognizer.train(images, labels);
    }
  }

  @Override
  public void updateTraining(MissedPerson person) throws IOException {
    int totalImages = person.getImages().size();
    final AtomicInteger index = new AtomicInteger(0);
    byte[][] images = new byte[totalImages][];
    int[] labels = new int[totalImages];
    person.getImages().forEach(i -> {
      images[index.get()] = i.getData();
      labels[index.getAndIncrement()] = person.getId().intValue();
    });
    recognizer.train(images, labels);
  }
}
