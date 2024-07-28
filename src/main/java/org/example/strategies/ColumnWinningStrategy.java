package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {

    HashMap<Integer, HashMap<Character,Integer>> counts=new HashMap<>();

    @Override
    public boolean checkWin(Board board, Move move) {

        System.out.println("Column winning: ");
        int col=move.getCell().getCol();

        Character sym=move.getCell().getSymbol().getSymbol();

        //steps 1: Row exist
        if(!counts.containsKey(col)){
            counts.put(col,new HashMap<>());
        }
        //step 2: check the symbol in inner hashmap
        HashMap<Character,Integer> countCol = counts.get(col);
        if(!countCol.containsKey(sym)){
            countCol.put(sym,0);
        }

        //step 3:increase the count
        countCol.put(sym,countCol.get(sym)+1);

        counts.put(col,countCol);

//        step 4: check if winner is there
        if(countCol.get(sym)==board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void undoMove(Move move) {
        int col=move.getCell().getCol();
        Character sym=move.getPlayer().getSymbol().getSymbol();
        counts.get(col).put(sym,counts.get(col).get(sym)-1);
    }
}
