package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

public interface BotPlayingStretegy {
    Move makeMove(Board board);
}
