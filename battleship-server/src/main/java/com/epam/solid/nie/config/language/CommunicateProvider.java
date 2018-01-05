package com.epam.solid.nie.config.language;

/**
 * It provides the proper communicates based on specified language.
 */
public interface CommunicateProvider {
    //todo:

    /**
     *
     * @param language language
     */
    void populate(Language language);
}
