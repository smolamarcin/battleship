package com.epam.solid.nie.utils;

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
}
