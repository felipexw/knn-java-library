package com.github.felipexw.evaluations;

/**
 * Created by felipe.appio on 26/08/2016.
 */
public class EvaluatorMetric {

    public double accuracy(double[] a, double[] b){
        if ((a == null || a.length ==1 ) ||  (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");

        return 0d;
    }

    public double fMeasure(double[] a, double[] b){
        if ((a == null || a.length ==1 ) ||  (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");
        return 0d;
    }

    public double precision(double[] a, double[] b){
        if ((a == null || a.length ==1 ) ||  (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");
        return 0d;
    }

    public double recall(double[] a, double[] b){
        if ((a == null || a.length ==1 ) ||  (b == null || b.length == 1))
            throw new IllegalArgumentException("The args can't be invalid.");
        return 0d;
    }
}
