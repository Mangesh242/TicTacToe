package org.example;

import org.example.controller.GameController;
import org.example.models.*;
import org.example.strategies.ColumnWinningStrategy;
import org.example.strategies.RowWinningStrategy;
import org.example.strategies.WinningStrategy;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1 , "MT" , PlayerType.HUMAN , new Symbol('O')));
        players.add(new BotPlayer(2 , "Botty", PlayerType.BOT, new Symbol('X') , BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());

        GameController controller = new GameController();
        Game ticTacToe = controller.startGame(3,players,winningStrategies);

        controller.displayBoard(ticTacToe);
        //System.out.println(controller.getGameState(ticTacToe));

        while(ticTacToe.getGameState().equals(GameState.IN_PROGRESS)){
            //
            controller.makeMove(ticTacToe);
            controller.displayBoard(ticTacToe);
    if(!ticTacToe.getGameState().equals(GameState.SUCCESS)) {
        System.out.println("Do you want to undo last move : [Y/N] ");
        String ch = sc.next();
        if (ch.equalsIgnoreCase("Y")) {
            controller.undoMove(ticTacToe);
            System.out.println("Undo is successful");
            controller.displayBoard(ticTacToe);
        }
    }
        }

        if(ticTacToe.getGameState().equals(GameState.SUCCESS)){
            System.out.println("Winner: "+ticTacToe.getWinnerName().getName());
        }
        else if(ticTacToe.getGameState().equals(GameState.DRAW)){
            System.out.println("Draw");
        }


    }
}
