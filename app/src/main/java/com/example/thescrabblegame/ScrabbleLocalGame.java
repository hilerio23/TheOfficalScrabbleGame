
package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.LocalGame;
import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;


public class ScrabbleLocalGame extends LocalGame {
    public ScrabbleState state;

    /*
    public ScrabbleLocalGame(ScrabbleSurfaceView view) {
        state = new ScrabbleState(view);
    }*/
    public ScrabbleLocalGame() {
        state = new ScrabbleState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == state.getIdNum()){
            return true;

        }
        else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {
        String winningStatement = null;
        if(state.getOver() == 1){
            if(state.getPlayer1Score() > state.getPlayer2Score()){
                winningStatement = "Player 1 won with a score of " + state.getPlayer1Score();
            }
            else if(state.getPlayer2Score() > state.getPlayer1Score()){
                winningStatement = "Player 2 won with a score of " + state.getPlayer2Score();
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
            if(state.getIdNum() == 1) {
                state.exchange(exchangeAction.getLettersToExchange());
                state.setIdNum(2);
            }
            else{
                state.exchange(exchangeAction.getLettersToExchange());
                state.setIdNum(1);
            }
            return true;
        }
        if(action instanceof PlayWord){
            PlayWord theAction = (PlayWord)action;
            //if it is this players turn
            if(state.getIdNum() == 1) {
                state.playWord(theAction.getWordToPlay(), theAction.getxCoord(), theAction.getyCoord(), theAction.getIsVertical());
                state.setIdNum(2);
            }
            else{
                state.playWord(theAction.getWordToPlay(), theAction.getxCoord(), theAction.getyCoord(), theAction.getIsVertical());
                state.setIdNum(1);
            }
            return true;
        }
        if(action instanceof Pass){
            //check if the player who sent action is it their turn
            // if yes then state.pass() and return true
            state.pass();
            return true;
        }
        if(action instanceof ExitGame){
            state.exitGame();
            return true;
        }
        return false;
    }




    //param should be an arraylist of scrabble letters
    //param which player



}

