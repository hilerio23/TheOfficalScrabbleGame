package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class Exchange extends GameAction {
    private ScrabbleLetter[] lettersToExchange;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public Exchange(GamePlayer player, ScrabbleLetter[] letterExchange) {
        super(player);
        //set this.wordToExchange
        lettersToExchange = new  ScrabbleLetter[letterExchange.length];
        for(int i = 0; i < letterExchange.length; i++) {
            lettersToExchange[i] = letterExchange[i];
        }
    }
    //add a getter for wordToExchange
    public ScrabbleLetter[] getLettersToExchange(){
        return lettersToExchange;
    }
    //add an instance variable this.wordToExchange
}
