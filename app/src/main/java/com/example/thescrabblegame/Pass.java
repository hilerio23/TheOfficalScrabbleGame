package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

/**
 * class Pass works as a placeholder when the player wants to pass
 *
 * @author Anabel Hilerio
 * @version April 2021
 */
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
