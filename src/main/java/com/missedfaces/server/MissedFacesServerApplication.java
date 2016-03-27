package com.missedfaces.server;

import com.google.common.collect.Lists;
import com.missedfaces.server.domain.beans.*;
import com.missedfaces.server.domain.repositories.*;
import com.missedfaces.server.service.recognition.RecognitionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@SpringBootApplication
@ImportResource(value = "classpath*:spring/integration.xml")
public class MissedFacesServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(MissedFacesServerApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(RecognitionService recognitionService,
                                      DetectorRepository detectorRepository,
                                      UserRepository userRepository,
                                      AuthorityRepository authorityRepository,
                                      DetectionRepository detectionRepository,
                                      MissedPersonRepository missedPersonRepository,
                                      MissedPersonImageRepository missedPersonImageRepository) {
    return args -> {

      // TODO: remove
      Detector detector = new Detector();
      detector.setDescription("");
      detector.setLatitude(-23.1396409);
      detector.setLongitude(-45.9117957);
      detectorRepository.save(detector);

      User user = new User();
      user.setUsername("marcos_costa.sjc@hotmail.com");
      user.setPassword("1234");
      user.setEnabled(true);
      userRepository.save(user);

      Authority authority = new Authority();
      authority.setUsername(user.getUsername());
      authority.setAuthority("ROLE_ADMIN");
      authorityRepository.save(authority);

      user = userRepository.findAll().iterator().next();

      MissedPerson missedPerson = new MissedPerson();
      missedPerson.setName("Marcos Costa");
      missedPerson.setBornDate(new Date());
      missedPerson.setMissedDate(new Date());
      missedPerson.setUser(user);
      MissedPersonImage img1 = loadImage("C:\\users\\marcos\\testimages\\img1.jpg");
      missedPersonImageRepository.save(img1);
      MissedPersonImage img2 = loadImage("C:\\users\\marcos\\testimages\\img2.jpg");
      missedPersonImageRepository.save(img2);
      MissedPersonImage img3 = loadImage("C:\\users\\marcos\\testimages\\img3.jpg");
      missedPersonImageRepository.save(img3);
      MissedPersonImage img4 = loadImage("C:\\users\\marcos\\testimages\\img4.jpg");
      missedPersonImageRepository.save(img4);
      missedPerson.setImages(Lists.newArrayList(img1, img2, img3, img4));

      missedPersonRepository.save(missedPerson);

      Detection detection = new Detection();
      detection.setPerson(missedPerson);
      detection.setTime(new Date());
      detection.setDetector(detector);
      detection.setImage(img3.getData());
      detection.setReceiveTime(new Date());
      detectionRepository.save(detection);

      recognitionService.train();
    };
  }

  private MissedPersonImage loadImage(String path) {
    try {
      byte[] data = Files.readAllBytes(Paths.get(path));
      MissedPersonImage image = new MissedPersonImage();
      image.setData(data);
      return image;
    } catch (IOException e) {
      return null;
    }
  }
}
