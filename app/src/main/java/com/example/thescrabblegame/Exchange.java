package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

/**
 * class Exchange works as a placeholder for when a player wants to exchange
 *
 * @author Anabel Hilerio
 * @version April 2021
 */
public class Exchange extends GameAction {
    private ScrabbleLetter[] lettersToExchange;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Exchange(GamePlayer player, ScrabbleLetter[] letterExchange) {
        super(player);
        lettersToExchange = new  ScrabbleLetter[letterExchange.length];
        for(int i = 0; i < letterExchange.length; i++) {
            lettersToExchange[i] = letterExchange[i];
        }
    }
    public ScrabbleLetter[] getLettersToExchange(){
        return lettersToExchange;
    }
}
