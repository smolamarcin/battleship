package com.academy.solid.nie.server;


import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * Main class for the game.
 */
public final class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
    }

    /**
     * Creates server here.
     *
     * @param args represents input
     * @throws IOException when something happens with the Server.
     */
    public static void main(final String[] args) throws IOException {
        GameInitializer gameInitializer;
        Player first = new NetPlayer();
        Player second = new NetPlayer();
        int port = Integer.parseInt(args[0]);
        System.out.println(port);
        String ip = getIp();
        System.out.println(ip);
        gameInitializer = new ServerGameInitializer(first, second, ip, port);
        gameInitializer.initializeGame();
        Game game = new Game(first, second);
        while (!game.isGameOver()) {
            game.play();
        }
        LOGGER.info("Game over");
    }

    private static String getIp() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // *EDIT*
                    if (addr instanceof Inet6Address) continue;
                    ip = addr.getHostAddress();
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }
}
