package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class RandomShuffle extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public RandomShuffle(GamePlayer player) {
        super(player);
    }
}
