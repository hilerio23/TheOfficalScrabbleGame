package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

public class PlayWord extends GameAction {
    private ScrabbleLetter[] wordPlayed;
    private double xCoord;
    private double yCoord;
    private boolean vertical;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayWord(GamePlayer player, ScrabbleLetter[] wordToPlay, double xCoordinate, double yCoordinate, boolean direction) {
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
    public double getxCoord(){
        return xCoord;
    }
    public double getyCoord(){
        return yCoord;
    }
    public boolean getIsVertical(){
        return vertical;
    }
    //add an instance variable this.wordToPlay
}
