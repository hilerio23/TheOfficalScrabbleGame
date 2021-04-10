package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class PlayWord extends GameAction {
    private ScrabbleLetter[] wordPlayed;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayWord(GamePlayer player, ScrabbleLetter[] wordToPlay) {
        super(player);
        //set this.wordToPlay
        wordPlayed = wordToPlay;

    }
    //add a getter for wordToPlay
    public ScrabbleLetter[] getWordToPlay(){
        return wordPlayed;
    }
    //add an instance variable this.wordToPlay
}
