package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class PlayWord extends GameAction {
    private ScrabbleLetter[] wordPlayed;
    private int[] xArray;
    private int[] yArray;
    private boolean vertical;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayWord(GamePlayer player, ScrabbleLetter[] wordToPlay, int[] xArray, int[] yArray, boolean direction) {
        super(player);
        //set this.wordToPlay
        wordPlayed = wordToPlay;
        this.xArray = xArray;
        this.yArray = yArray;
        vertical = direction;
    }
    //add a getter for wordToPlay
    public ScrabbleLetter[] getWordToPlay(){
        return wordPlayed;
    }
    public int[] getXArray(){
        return xArray;
    }
    public int[] getYArray(){
        return yArray;
    }
    public boolean getIsVertical(){
        return vertical;
    }
    //add an instance variable this.wordToPlay
}
