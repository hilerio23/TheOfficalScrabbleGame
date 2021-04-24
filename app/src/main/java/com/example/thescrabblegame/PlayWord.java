package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

/**
 * class PlayWord acts as place holder for the action of playing a word
 *
 * @author Anabel Hilerio, Samone Watkins
 * @version April 2021
 */
public class PlayWord extends GameAction {
    private ScrabbleLetter[] wordPlayed;
    private int[] sTiles;
    private int[] xArray;
    private int[] yArray;
    private boolean vertical;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlayWord(GamePlayer player, ScrabbleLetter[] wordToPlay, int[] specialTiles, int[] xArray, int[] yArray, boolean direction) {
        super(player);
        wordPlayed = wordToPlay;
        sTiles = specialTiles;
        this.xArray = xArray;
        this.yArray = yArray;
        vertical = direction;
    }

    public ScrabbleLetter[] getWordToPlay(){
        return wordPlayed;
    }
    public int[] getSpecialTiles(){
        return sTiles;
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
}
