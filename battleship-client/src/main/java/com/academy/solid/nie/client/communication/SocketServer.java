package com.academy.solid.nie.client.communication;

import com.academy.solid.nie.client.language.Message;
import com.academy.solid.nie.client.language.MessageProviderImpl;
import com.academy.solid.nie.client.ui.Point2D;
import com.academy.solid.nie.client.ui.WindowDisplayer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * SocketServer implementation to communicate with the server.
 *
 * @since 1.0.1
 */

public final class SocketServer implements Server {
    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    private ShipClient server;
    private String allMoves = "";

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    private boolean firstPlayer;

    @Override
    public void connect(String ip, int port) {
        try {
            Socket socket = createSocket(ip, port);
            server = SocketClient.builder().
                    ip(ip).socket(socket).
                    out(createPrintWriter(socket)).
                    in(createBufferedReader(socket)).
                    build();
            firstPlayer = server.receiveServerInitialMessage();
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void send(final String allShips) {
        LOGGER.info(allShips);
        try {
            server.send(allShips);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public String receiveAllShips() {
        return server.getEnemyShips();
    }

    @Override
    public void sendGameOverToOpponent() {
        server.sendGameOverToOpponent();
    }

    private List<Point2D> receiveAllMovesWithoutSending() {
        allMoves = "";
        String moves = server.getEnemyShips();
        if (moves.equals("Q")) {
            new WindowDisplayer(MessageProviderImpl.getCommunicate(Message.LOSE))
                    .withButtonWhoExitSystem().display();
        }

        return Arrays.stream(moves.split(",;"))
                .map(s -> s.split(","))
                .map(stringArrayToIntArray())
                .map(arr -> Point2D.of(arr[0], arr[1]))
                .collect(Collectors.toList());
    }

    private Function<String[], int[]> stringArrayToIntArray() {
        return arr -> Stream.of(arr)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Override
    public void sendPlayerMove(final String move) {
//        allMoves += move + ";";
        send(move);
    }

    @Override
    public List<Point2D> receiveEnemyMoves() {
        send(allMoves);
        return receiveAllMovesWithoutSending();
    }

    private Socket createSocket(String inputIp, int inputPortNumber) throws IOException {
        return new Socket(inputIp, inputPortNumber);
    }

    private BufferedReader createBufferedReader(Socket inputSocket) throws IOException {
        return new BufferedReader(createInputStreamReader(inputSocket));
    }

    private InputStreamReader createInputStreamReader(Socket inputSocket) throws IOException {
        return new InputStreamReader(inputSocket.getInputStream(), StandardCharsets.UTF_8);
    }

    private PrintWriter createPrintWriter(Socket inputSocket) throws IOException {
        return new PrintWriter(createOutputStream(inputSocket), true);
    }

    private OutputStreamWriter createOutputStream(Socket inputSocket) throws IOException {
        return new OutputStreamWriter(inputSocket.getOutputStream(), StandardCharsets.UTF_8);
    }

    public List<Point2D> receiveMoves() throws IOException {
        String moves = server.receiveMoves();
        if (moves.equals("Q")) {
            new WindowDisplayer(CommunicateProviderImpl.getCommunicate(Communicate.LOSE))
                    .withButtonWhoExitSystem().display();
        }

        return Arrays.stream(moves.split(";"))
                .map(s -> s.split(","))
                .map(stringArrayToIntArray())
                .map(arr -> Point2D.of(arr[0], arr[1]))
                .collect(Collectors.toList());
    }
}
