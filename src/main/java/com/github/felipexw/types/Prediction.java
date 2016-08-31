package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class Prediction<L> {
    private final L label;
    private final double score;
    private int count;

    public L getLabel() {
        return label;
    }

    public Prediction(L label, double score) {
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
        if (!(o instanceof Prediction)) return false;

        Prediction that = (Prediction) o;

        return label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "label='" + label + '\'' +
                ", score=" + score +
                ", count=" + count +
                '}';
    }
}
