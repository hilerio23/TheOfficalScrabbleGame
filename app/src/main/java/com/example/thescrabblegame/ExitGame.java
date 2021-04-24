package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

/**
 * class ExitGame works as a placeholder when a player wants to exit the game
 *
 * @author Anabel Hilerio
 * @version April 2021
 */
public class ExitGame extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ExitGame(GamePlayer player) {
        super(player);
    }
}
