package com.epam.solid.nie.config.language;

/**
 * It provides the correct communicates based on specified language.
 */
public interface CommunicateProvider {
    CommunicateProviderImpl populate(Language language);
}