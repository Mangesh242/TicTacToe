package org.example.models;

import org.example.strategies.BotPlayingStrategyFactory;
import org.example.strategies.BotPlayingStretegy;

import java.util.Scanner;

public class BotPlayer extends Player{
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStretegy playingStretegy;
    Scanner sc=new Scanner(System.in);


    public BotPlayer(int id, String name,PlayerType playerType, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(id, name,playerType,symbol);
        this.difficultyLevel = difficultyLevel;
        this.playingStretegy=BotPlayingStrategyFactory.getBotPlayingStrtegy(difficultyLevel);
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public BotPlayingStretegy getPlayingStretegy() {
        return playingStretegy;
    }

    public void setPlayingStretegy(BotPlayingStretegy playingStretegy) {
        this.playingStretegy = playingStretegy;
    }

    @Override
    public Move acceptMove(Board board) {
       return this.playingStretegy.makeMove(board);
    }
}
