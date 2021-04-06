package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class Pass extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Pass(GamePlayer player) {
        super(player);
    }
}
