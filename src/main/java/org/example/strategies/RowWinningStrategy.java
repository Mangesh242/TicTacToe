package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

public class RowWinningStrategy implements WinningStrategy{
    @Override
public boolean checkWinner(Board board, Move move) {
    return false;
}

    @Override
    public void handleUndo(Board board, Move move) {

    }
}
