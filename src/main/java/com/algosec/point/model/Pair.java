package com.algosec.point.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.util.Assert;

/**
 * Created by Alexander on 09.12.2017.
 */
public final class Pair<V extends Number> {

    private V startPoint;
    private V endPoint;


    public Pair() {
    }

    public Pair(V a, V b) {
        Assert.notNull(a, "Start point is null");
        Assert.notNull(b, "End point is null");

        if (a.longValue() > b.longValue()) {
            startPoint = b;
            endPoint = a;
        } else {
            startPoint = a;
            endPoint = b;
        }

    }

    public void setStartPoint(V startPoint) {
        this.startPoint = startPoint;
    }

    public V getStartPoint() {
        return startPoint;
    }

    public void setEndPoint(V endPoint) {
        this.endPoint = endPoint;
    }

    public V getEndPoint() {
        return endPoint;
    }

    // WARNING !!! - CUSTOM EQUALS !!!
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Pair)) return false;

        Pair<V> other = (Pair<V>) o;

        return getStartPoint().equals(other.getStartPoint()) && getEndPoint().equals(other.getEndPoint());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(startPoint)
                .append(endPoint)
                .toHashCode();
    }

    @Override
    public String toString() {
        if (getStartPoint() != null && getEndPoint() != null) {
            return "{" + getStartPoint().toString() + ", " + getEndPoint().toString() + "}";
        }
        if (getStartPoint() != null) {
            return "{Xs=" + getStartPoint().toString() + ", SECOND = NULL!}";
        }

        if (getEndPoint() != null) {
            return "{Xe=" + getEndPoint().toString() + "}";
        }
        return "Both Pair items are NULL";
    }

}
