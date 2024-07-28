package org.example.strategies.BotPlaying;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.CellState;
import org.example.models.Move;

import java.util.List;

public class BotEasyPlayingStrategy implements BotPlayingStretegy {
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row:board.getGrid()){
            for(Cell cell:row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(new Cell(cell.getRow(),cell.getCol()),null);
                }
            }
        }
        return null;
    }
}
