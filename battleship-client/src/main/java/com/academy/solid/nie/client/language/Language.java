package com.academy.solid.nie.client.language;

/**
 * Available languages in the game.
 */
public enum Language {
    POLISH, ENGLISH;

    public static Language defaultLanguage() {
        return POLISH;
    }
}
