package com.missedfaces.server.api;

import com.google.common.collect.Lists;
import com.missedfaces.server.domain.beans.MissedPerson;
import com.missedfaces.server.domain.beans.MissedPersonImage;
import com.missedfaces.server.domain.repositories.DetectionRepository;
import com.missedfaces.server.domain.repositories.MissedPersonImageRepository;
import com.missedfaces.server.domain.repositories.MissedPersonRepository;
import com.missedfaces.server.service.accounts.AccountsService;
import com.missedfaces.server.service.recognition.RecognitionService;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/desaparecidos")
@Secured({"ROLE_ADMIN", "ROLE_USER"})
public class MissedPersonController {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private MissedPersonRepository missedPersonRepository;

  @Autowired
  private MissedPersonImageRepository missedPersonImageRepository;

  @Autowired
  private DetectionRepository detectionRepository;

  @Autowired
  private RecognitionService recognitionService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("persons", accountsService.currentUser().getMissedPersons());
    return "missed_people/index";
  }

  @RequestMapping(value = "/novo", method = RequestMethod.GET)
  public String addNew() {
    return "missed_people/new";
  }

  @RequestMapping(value = "/deletar/{id}", method = RequestMethod.DELETE)
  @Transactional
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    MissedPerson person = missedPersonRepository.findByIdAndUser(id, accountsService.currentUser());
    if (person != null) {
      detectionRepository.delete(person.getDetections());
      missedPersonRepository.delete(id);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/novo", method = RequestMethod.POST)
  public ResponseEntity<Void> saveNew(@RequestParam("name") String name,
                                      @RequestParam("bornDate") String bornDate,
                                      @RequestParam("missedDate") String missedDate,
                                      @RequestParam("images[]") MultipartFile[] images) {
    MissedPerson missedPerson = new MissedPerson();
    missedPerson.setUser(accountsService.currentUser());
    try {
      missedPerson.setName(name);
      missedPerson.setBornDate(parseDate(bornDate));
      missedPerson.setMissedDate(parseDate(missedDate));
      missedPerson.setImages(Lists.newArrayList());
      for (MultipartFile file : images) {
        MissedPersonImage image = new MissedPersonImage();
        image.setData(toByteArray(file.getInputStream()));
        missedPersonImageRepository.save(image);
        missedPerson.getImages().add(image);
      }
      missedPersonRepository.save(missedPerson);
      recognitionService.updateTraining(missedPerson);
    } catch (ParseException | IOException e) {
      throw new RuntimeException(e);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  private byte[] toByteArray(InputStream in) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Streams.copy(in, out, true);
    return out.toByteArray();
  }

  private Date parseDate(String date) throws ParseException {
    return new SimpleDateFormat("yyyy-MM-dd").parse(date);
  }
}
