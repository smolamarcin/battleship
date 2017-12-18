package com.epam.solid.nie.state;

import com.epam.solid.nie.state.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class StateTest {

    private GameState state = new GameState();

    public void should_changeToEndState(){
        //given
        State endState = new EndState();

        //when
        endState.process(state);

        //then
        Assert.assertTrue(state.getState().equals(endState));
    }

    public void should_changeToStartState(){
        //given
        State playState = new PlayState();

        //when
        playState.process(state);

        //then
        Assert.assertTrue(state.getState().equals(playState));
    }

    public void should_changeToConfigState(){
        //given
        State configState = new ConfigState();

        //when
        configState.process(state);

        //then
        Assert.assertTrue(state.getState().equals(configState));
    }
}
