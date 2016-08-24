package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class PredictedInstance implements Instance{
    private final String label;
    private final double score;

    public String getLabel() {
        return label;
    }

    public PredictedInstance(String label, double score) {
        this.label = label;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PredictedInstance)) return false;

        PredictedInstance that = (PredictedInstance) o;

        if (Double.compare(that.score, score) != 0) return false;
        return label.equals(that.label);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = label.hashCode();
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PredictedInstance{" +
                "label='" + label + '\'' +
                ", score=" + score +
                '}';
    }
}
