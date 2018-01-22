package com.academy.solid.nie.server;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Test
public class GameTest {
    private Player first;
    private Player second;

    @BeforeMethod(groups = {"unit", "integration"})
    public void setUp() throws Exception {
        first = mock(Player.class);
        second = mock(Player.class);
        when(first.getShips()).thenReturn("");
        when(second.getShips()).thenReturn("");
    }

    @Test(groups = {"unit"})
    public void afterOneInvokeOfMethodPlaySecondPlayerShouldReceiveFirstPlayersMove() throws IOException {
        //given
        String moveOfFirstPlayer = "0,0";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        Game game = new Game(first, second);
        game.play();
        //then
        verify(second).inform(moveOfFirstPlayer);
    }

    @Test(groups = {"unit"})
    public void afterSecondInvokeOfMethodPlayFirstPlayerShouldReceiveSecondPlayersMove() throws IOException {
        //given
        String moveOfSecondPlayer = "0,1";
        when(second.makeMove()).thenReturn(moveOfSecondPlayer);
        String moveOfFirstPlayer = "0,0";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        Game game = new Game(first, second);
        game.play();
        game.play();
        //then
        verify(second).inform(moveOfFirstPlayer);
        verify(first).inform(moveOfSecondPlayer);
    }

    @Test(groups = {"unit"})
    public void afterThirdInvokeOfMethodPlaySecondPlayerShouldReceiveFirstsPlayersMoveAgain() throws IOException {
        //given
        String moveOfSecondPlayer = "0,1";
        when(second.makeMove()).thenReturn(moveOfSecondPlayer);
        String moveOfFirstPlayer = "0,0";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        Game game = new Game(first, second);
        game.play();
        game.play();
        game.play();
        //then
        verify(second, times(2)).inform(moveOfFirstPlayer);
        verify(first).inform(moveOfSecondPlayer);
    }

    @Test(groups = {"unit"})
    public void gameShouldBeOverAfterSendingMoveWithEndOfGameInformation() throws IOException {
        //given
        String moveOfFirstPlayer = "Q";
        when(first.makeMove()).thenReturn(moveOfFirstPlayer);
        //when
        Game game = new Game(first, second);
        game.play();
        //then
        Assert.assertTrue(game.isGameOver());
    }
}
