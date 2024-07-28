package org.example.models;

import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner sc=new Scanner(System.in);
    public HumanPlayer(int id, String name,PlayerType playerType,Symbol sym){
        super(id,name,playerType,sym);
    }

    @Override
    public Move acceptMove(Board board) {
        System.out.println("Enter row no: ");
        int row=sc.nextInt();
        System.out.println("Enter col no: ");
        int col=sc.nextInt();

        return new Move(new Cell(row,col),this);
    }
}
