package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;

public class DiagonalWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWin(Board board, Move move) {
        return false;
    }

    @Override
    public void undoMove(Move move) {

    }
}
