package org.example.controller;

import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;
import org.example.strategies.WinningStrategy;
import org.example.models.Move;

import java.util.List;

public class GameController {

    public Game startGame(int size, List<Player> players, List<WinningStrategy> winningStrategies){
        return Game.getBuilder().setSize(3).setPlayers(players).setWinningStrategies(winningStrategies).build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void makeMove(Game game) {
            game.makeMove();
    }
    public void undoMove(Game game)
    {

    }
    public Player getWinner(Game game) {
        return null;
    }
}
