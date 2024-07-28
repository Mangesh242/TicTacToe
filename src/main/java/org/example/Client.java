package org.example;

import org.example.controller.GameController;
import org.example.models.*;
import org.example.strategies.ColWinningStrategy;
import org.example.strategies.DiagonalWinningStrategy;
import org.example.strategies.RowWinningStrategy;
import org.example.strategies.WinningStrategy;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1 , "Mohit" , PlayerType.HUMAN , new Symbol('O')));
        players.add(new BotPlayer(2 , "Botty", PlayerType.BOT, new Symbol('X') , BotDifficultyLevel.EASY));

        //Add Winning Strategies
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        GameController controller = new GameController();
        Game ticTacToe = controller.startGame(3,players,winningStrategies);

        controller.displayBoard(ticTacToe);
        //System.out.println(controller.getGameState(ticTacToe));
        Scanner scanner = new Scanner(System.in);
        while(controller.getGameState(ticTacToe).equals(GameState.IN_PROGRESS)){
            controller.makeMove(ticTacToe);

            controller.displayBoard(ticTacToe);

            System.out.println("Do you want to undo last move ? [Y/N]");
            String undoAnswer=scanner.next();
            if(undoAnswer.equals("Y")){
                controller.undoMove(ticTacToe);
                System.out.println("Undo is successful");
                controller.displayBoard(ticTacToe);
            }
        }

        if(controller.getGameState(ticTacToe).equals(GameState.SUCCESS)){
            System.out.println(controller.getWinner(ticTacToe).getName()+" Won the game");
        }else if(controller.getGameState(ticTacToe).equals(GameState.DRAW)){
            System.out.println("Game Result is Draw");

        }
    }
}
