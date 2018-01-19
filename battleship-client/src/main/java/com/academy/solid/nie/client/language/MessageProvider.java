package com.academy.solid.nie.client.language;

/**
 * It provides the proper communicates based on specified language.
 */
public interface MessageProvider {
    /**
     * Populate given language.
     *
     * @param language determines provided language
     */
    void populate(Language language);
}
