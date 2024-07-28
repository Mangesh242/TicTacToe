package org.example.strategies.winningStrategies;

import org.example.models.Board;
import org.example.models.Move;

public interface WinningStrategy {

    boolean checkWin(Board board, Move move);

    void undoMove(Move move);
}
