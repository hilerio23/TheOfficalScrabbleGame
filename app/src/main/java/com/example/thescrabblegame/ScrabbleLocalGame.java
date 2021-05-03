
package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.LocalGame;
import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;
/**
 * class ScrabbleLocalGame controls the play of the game
 *
 * @author Anabel Hilerio, Alec Uyematsu
 * @version April 2021
 */

public class ScrabbleLocalGame extends LocalGame {

    /**
     * This ctor creates a new game state
     */
    public ScrabbleLocalGame() {
        super();
        state = new ScrabbleState();
    }

    public ScrabbleLocalGame(ScrabbleState scrabbleState){
        super();
        state = new ScrabbleState(scrabbleState);
    }

    /**
     * send the updated state to a given player
     *
     * @param p
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ScrabbleState myScrabbleState = new ScrabbleState((ScrabbleState)state);
        p.sendInfo(myScrabbleState);
    }

    /**
     * can the player with the given id take an action right now?
     *
     * @param playerIdx
     * @return boolean
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == ((ScrabbleState)state).getIdNum()){
            return true;

        }
        else {
            return false;
        }
    }

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        String winningStatement = null;
        if(((ScrabbleState)state).getOver() == 1){
            if(((ScrabbleState)state).getPlayer1Score() > ((ScrabbleState)state).getPlayer2Score()){
                winningStatement = "Player 1 won with a score of " + ((ScrabbleState)state).getPlayer1Score();
            }
            else if(((ScrabbleState)state).getPlayer2Score() > ((ScrabbleState)state).getPlayer1Score()){
                winningStatement = "Player 2 won with a score of " + ((ScrabbleState)state).getPlayer2Score();
            }
            else{
                winningStatement = "Player 1 and Player 2 tied...";
            }
        }
        return winningStatement;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action){

        if(action instanceof Exchange){
            Exchange exchangeAction = (Exchange) action;
            if(((ScrabbleState)state).getIdNum() == 0) {
                ((ScrabbleState)state).exchange(exchangeAction.getLettersToExchange());
                ((ScrabbleState)state).setIdNum(1);
            }
            else{
                ((ScrabbleState)state).exchange(exchangeAction.getLettersToExchange());
                ((ScrabbleState)state).setIdNum(0);
            }
            return true;
        }
        if(action instanceof PlayWord){
            PlayWord theAction = (PlayWord)action;
            if(((ScrabbleState)state).getIdNum() == 0) {
                ((ScrabbleState)state).playWord(theAction.getWordToPlay(), theAction.getSpecialTiles(),
                        theAction.getXArray(), theAction.getYArray(), theAction.getIsVertical());
                ((ScrabbleState)state).setIdNum(1);
            }
            else{
                ((ScrabbleState)state).playWord(theAction.getWordToPlay(), theAction.getSpecialTiles(),
                        theAction.getXArray(), theAction.getYArray(), theAction.getIsVertical());
                ((ScrabbleState)state).setIdNum(0);
            }
            return true;
        }
        if(action instanceof Pass){
            ((ScrabbleState)state).pass();
            return true;
        }
        if(action instanceof ExitGame){
            ((ScrabbleState)state).exitGame();
            return true;
        }
        return false;
    }
}// class ScrabbleLocalGame

