package com.github.felipexw.util;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public class StringEncoder extends Encoder {
    @Override
    public int encode(String word) {
        int encondedValue  = 0;

        for(int i =0; i < word.length(); i++){
            for( byte j = 0; j < VALUES.length; j++){
                String c = String.valueOf(word.charAt(i));
                if (c.equalsIgnoreCase(VALUES[j]))
                    encondedValue += (j+1);
            }
        }
        return encondedValue;
    }
}
