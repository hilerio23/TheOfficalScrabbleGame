package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class PlayWord extends GameAction {
    private ScrabbleLetter[] wordPlayed;
    private int xCoord;
    private int yCoord;
    private boolean vertical;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayWord(GamePlayer player, ScrabbleLetter[] wordToPlay, int xCoordinate, int yCoordinate, boolean direction) {
        super(player);
        //set this.wordToPlay
        wordPlayed = wordToPlay;
        xCoord = xCoordinate;
        yCoord = yCoordinate;
        vertical = direction;
    }
    //add a getter for wordToPlay
    public ScrabbleLetter[] getWordToPlay(){
        return wordPlayed;
    }
    public int getxCoord(){
        return xCoord;
    }
    public int getyCoord(){
        return yCoord;
    }
    public boolean getIsVertical(){
        return vertical;
    }
    //add an instance variable this.wordToPlay
}
