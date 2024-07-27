package org.example;

import org.example.controller.GameController;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1 , "Mohit" , PlayerType.HUMAN , new Symbol('O')));
        players.add(new BotPlayer(2 , "Botty", PlayerType.BOT, new Symbol('X') , BotDifficultyLevel.EASY));

//        Game ticTacToe= Game.getBuilder().setSize(3).setPlayers(players).build();
        GameController controller = new GameController();
        Game ticTacToe = controller.startGame(3,players);

        controller.displayBoard(ticTacToe);
        //System.out.println(controller.getGameState(ticTacToe));
    }
}
