package com.academy.solid.nie.client.output;

import com.academy.solid.nie.client.ui.WindowDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Logs messages to windows
 */
public class WindowOutputDispatcher implements Output {
    private List<WindowDisplayer> windows = new ArrayList();

    @Override
    public final void send(String msg) {
        WindowDisplayer window = new WindowDisplayer(msg).withButtonWhoExitThisWindow();
        windows.add(window);
        window.display();
    }

}
