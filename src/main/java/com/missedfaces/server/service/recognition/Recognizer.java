package com.missedfaces.server.service.recognition;

import java.io.IOException;

public interface Recognizer {

  int recognize(byte[] image) throws IOException;
  void train(byte[][] images, int[] labels) throws IOException;
  void update(byte[][] images, int[] labels) throws IOException ;
}
