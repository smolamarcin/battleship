package com.academy.solid.nie.client.language;

import java.util.EnumMap;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * It provides relevant messages to the game.
 */
public class MessageProviderImpl implements MessageProvider {
    private static EnumMap<Message, String> communicates = new EnumMap<>(Message.class);

    /**
     * Fills the map with messages in the appropriate language.
     *
     * @param language language
     */
    public final void populate(final Language language) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(language.name());
        Stream.of(Message.values()).forEach(consume(resourceBundle));
    }

    private Consumer<Message> consume(final ResourceBundle resourceBundle) {
        return element -> communicates.put(element, resourceBundle.getString(element
                .name()));
    }

    /**
     * Retrieves the message in the appropriate language from the map.
     *
     * @param message message
     * @return specified message from the map
     */
    public static String getCommunicate(final Message message) {
        return communicates.get(message);
    }

    /**
     * Determines whether the map is empty.
     *
     * @return true if the map is empty
     */
    boolean isMapEmpty() {
        return communicates.isEmpty();
    }
}
