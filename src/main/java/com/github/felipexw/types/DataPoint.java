package com.github.felipexw.types;

/**
 * Created by felipe.appio on 24/08/2016.
 */
public class DataPoint {
    private final DataPoint origin;
    private final DataPoint destination;
    private final double distance;

    public DataPoint getOrigin() {
        return origin;
    }

    public DataPoint getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    public DataPoint(DataPoint origin, DataPoint destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataPoint)) return false;

        DataPoint dataPoint = (DataPoint) o;

        if (Double.compare(dataPoint.distance, distance) != 0) return false;
        if (!origin.equals(dataPoint.origin)) return false;
        return destination.equals(dataPoint.destination);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = origin.hashCode();
        result = 31 * result + destination.hashCode();
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
