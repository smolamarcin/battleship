package com.academy.solid.nie.client.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * Represents a point in two-dimensional space.
 * Contains horizontal and vertical coordinates.
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Point2D {
    private final int x;
    private final int y;

    /**
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return String.format("%d,%d,", x, y);
    }

    /**
     * @param o represent another Point2D
     * @return information whether two points are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Point2D)) {
            return false;
        }
        Point2D point2D = (Point2D) o;
        return x == point2D.x && y == point2D.y;
    }

    /**
     * @return HashCode based of values of fields
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
