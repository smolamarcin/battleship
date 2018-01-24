package com.academy.solid.nie.client.output;

import com.academy.solid.nie.client.ui.WindowTranscript;

/**
 * Logs messages to windows
 */
public class WindowOutputDispatcher implements Output {
    private WindowTranscript window = new WindowTranscript();

    WindowOutputDispatcher() {
        window.display();
    }

    @Override
    public final void send(String msg) {
        window.append(msg);
        window.append("\n");
    }
}
