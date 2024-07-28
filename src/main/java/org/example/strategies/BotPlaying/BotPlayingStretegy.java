package org.example.strategies.BotPlaying;

import org.example.models.Board;
import org.example.models.Move;

public interface BotPlayingStretegy {
    Move makeMove(Board board);
}
