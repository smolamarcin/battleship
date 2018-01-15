package com.academy.solid.nie.client.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
}
