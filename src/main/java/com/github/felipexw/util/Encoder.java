package com.github.felipexw.util;

import java.util.List;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public abstract class Encoder {

  public static final String[] VALUES = {
      "A", "B", "C", "D", "F", "G", "H", "I", "J",
      "K", "L", "M", "N", "O", "P", "Q", "R", "S",
      "T", "U", "V", "X", "W", "Y", "Z"};

  public abstract double[] encodeFromList(List<String> word);

  public abstract double encode(String word);
}
