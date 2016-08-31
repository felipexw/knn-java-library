package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class PredictedInstance<T> extends Instance<T>{
    private final String label;
    private final double score;
    private int count;

    public String getLabel() {
        return label;
    }

    public PredictedInstance(String label, double score) {
        this.label = label;
        this.score = score;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PredictedInstance)) return false;

        PredictedInstance that = (PredictedInstance) o;

        return label.equals(that.label);

    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return "PredictedInstance{" +
                "label='" + label + '\'' +
                ", score=" + score +
                ", count=" + count +
                '}';
    }
}
