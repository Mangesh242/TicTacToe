package org.example.models;

public class HumanPlayer extends Player {

    public HumanPlayer(int id, String name,PlayerType playerType,Symbol sym){
        super(id,name,playerType,sym);
    }
    @Override
    public Move acceptMove()
    {
        System.out.println("Please enter the row where you want to place the symbol : ");
        int r=sc.nextInt();
        int c=sc.nextInt();

        return new Move(new Cell(r,c),this);

    }
}
