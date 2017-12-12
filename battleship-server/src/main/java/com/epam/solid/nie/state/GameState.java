package com.epam.solid.nie.state;

@lombok.ToString
@lombok.Getter
@lombok.Setter
public class GameState implements State {

    private State state;

    @Override
    public void process(GameState gameState) {
        this.state.process(gameState);
    }

}
