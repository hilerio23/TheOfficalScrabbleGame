package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.LocalGame;
import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;


public class ScrabbleLocalGame extends LocalGame {
    public ScrabbleState state;


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
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action){
        if(action instanceof RandomShuffle){

        }
        if(action instanceof PlayWord){

        }
        if(action instanceof Pass){
            if (state.getIdNum() == 1) {
                state.setIdNum(2);
            }


            if (state.getIdNum() == 2) {
                state.setIdNum(1);
            }
        }
    }


    //param should be an arraylist of scrabble letters
    //param which player


}
