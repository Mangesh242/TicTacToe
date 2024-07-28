package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{

    HashMap<Integer,HashMap<Character,Integer>> counts=new HashMap<>();

    @Override
    public boolean checkWin(Board board, Move move) {

        int row=move.getCell().getRow();
        Character sym=move.getCell().getSymbol().getSymbol();

        //steps 1: Row exist
        if(!counts.containsKey(row)){
            counts.put(row,new HashMap<>());
        }
        //step 2: check the symbol in inner hashmap
        HashMap<Character,Integer> countRow = counts.get(row);
        if(!countRow.containsKey(sym)){
            countRow.put(sym,0);
        }

        //step 3:increase the count
        countRow.put(sym,countRow.get(sym)+1);

        counts.put(row,countRow);

//        step 4: check if winner is there
        if(countRow.get(sym)==board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void undoMove(Move move) {
        int row=move.getCell().getRow();
        Character sym=move.getPlayer().getSymbol().getSymbol();
        counts.get(row).put(sym,counts.get(row).get(sym)-1);
    }
}
