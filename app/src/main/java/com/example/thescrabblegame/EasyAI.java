package com.example.thescrabblegame;

import android.view.View;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameComputerPlayer;

public class EasyAI extends GameComputerPlayer{

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public EasyAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        ScrabbleState scrabbleCopy = new ScrabbleState((ScrabbleState) info);

        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = scrabbleCopy.getBoard();
        char[] hand = new char[7];

        for(int i = 0; i < 15; i ++){
            for(int j = 0; j < 15; j++){
                //if board is not empty
                if(board[i][j] != null){
                    //checks to see if space around word to be played
                    if(board[i-1][j-1] == null || board[i][j-1] == null || board[i+1][j-1] == null || board[i-1][j] == null ||
                    board[i+1][j] == null || board[i-1][j+1] == null || board[i][j+1] == null|| board[i+1][j+1] == null){
                        //play piece
                    }
                    else{
                        //shuffle pieces
                    }
                }

            }
        }
    }
}
