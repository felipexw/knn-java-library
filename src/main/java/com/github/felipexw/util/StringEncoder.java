package com.github.felipexw.util;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class StringEncoder extends Encoder {

  @Override public double[] encodeFromList(List<String> words) {
    double[] encodedValues = new double[words.size()];

    for (int i = 0; i < words.size(); i++) {
      String word = words.get(i);
      encodedValues[i] = word.hashCode();
    }

    return encodedValues;
  }

  @Override public double encode(String word) {
    return word.hashCode();
  }
}
