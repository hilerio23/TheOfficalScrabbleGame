
package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.LocalGame;
import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;


public class ScrabbleLocalGame extends LocalGame {

    //private ScrabbleState state;

    public ScrabbleLocalGame() {
        super();
        state = new ScrabbleState();
    }
    public ScrabbleLocalGame(ScrabbleState scrabbleState){
        super();
        state = new ScrabbleState(scrabbleState);
    }
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ScrabbleState myScrabbleState = new ScrabbleState((ScrabbleState)state);
        p.sendInfo(myScrabbleState);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == ((ScrabbleState)state).getIdNum()){
            return true;

        }
        else {
            return false;
        }
    }

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
            //if it is this players turn
            if(((ScrabbleState)state).getIdNum() == 0) {
                ((ScrabbleState)state).playWord(theAction.getWordToPlay(), theAction.getSpecialTiles(), theAction.getXArray(), theAction.getYArray(), theAction.getIsVertical());
                ((ScrabbleState)state).setIdNum(1);
            }
            else{
                ((ScrabbleState)state).playWord(theAction.getWordToPlay(), theAction.getSpecialTiles(), theAction.getXArray(), theAction.getYArray(), theAction.getIsVertical());
                ((ScrabbleState)state).setIdNum(0);
            }
            return true;
        }
        if(action instanceof Pass){
            //check if the player who sent action is it their turn
            // if yes then ((ScrabbleState)state).pass() and return true
            ((ScrabbleState)state).pass();
            return true;
        }
        if(action instanceof ExitGame){
            ((ScrabbleState)state).exitGame();
            return true;
        }
        return false;
    }




    //param should be an arraylist of scrabble letters
    //param which player



}

