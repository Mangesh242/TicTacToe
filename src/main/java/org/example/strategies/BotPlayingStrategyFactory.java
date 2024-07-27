package org.example.strategies;

import org.example.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStretegy getBotPlayingStrtegy(BotDifficultyLevel difficult){
        if(difficult.equals(BotDifficultyLevel.EASY)){
            return new BotEasyPlayingStrategy();
        }
        return null;

    }
}
