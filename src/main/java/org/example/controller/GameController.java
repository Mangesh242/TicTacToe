package org.example.controller;

import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;

import java.util.List;

public class GameController {

    public Game startGame(int size, List<Player> players){
        return Game.getBuilder().setSize(3).setPlayers(players).build();
    }
    public void displayBoard(Game game) {
        game.displayBoard();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
//    public void makeMove(Move move) {
//
//    }
}
