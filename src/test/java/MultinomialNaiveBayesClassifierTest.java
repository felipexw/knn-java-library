package com.github.felipexw.classifier.bayes;

import org.junit.Test;

/**
 * Created by felipe.appio on 29/08/2016.
 */
public class MultinomialNaiveBayesClassifierTest {

  @Test
  public void it_should_fail(){
    for(int i =0; i < 2; i++){
      String str = String.valueOf(i);
      System.out.println(str.hashCode());
    }

  }
}
