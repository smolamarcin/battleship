package com.epam.solid.nie.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by marek on 13.12.2017.
 */
@Getter
@RequiredArgsConstructor(staticName="of")
public class Point2D {
    private final int x;
    private final int y;
}
