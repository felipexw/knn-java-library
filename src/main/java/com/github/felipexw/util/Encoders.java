package com.github.felipexw.util;

/**
 * Created by felipe.appio on 23/08/2016.
 */
public final class Encoders {

    public static final String[] VALUES = {
            "A", "B", "C", "D", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "X", "W", "Y", "Z"};

    public static int encode(String word) {
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
