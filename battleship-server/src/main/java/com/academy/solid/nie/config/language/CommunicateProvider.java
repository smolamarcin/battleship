package com.academy.solid.nie.config.language;

/**
 * It provides the proper communicates based on specified language.
 */
public interface CommunicateProvider {
    /**
     * Populate given language.
     *
     * @param language determines provided language
     */
    void populate(Language language);
}
