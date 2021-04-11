package com.example.thescrabblegame;

import android.view.View;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameComputerPlayer;

import java.util.Random;

public class EasyAI extends GameComputerPlayer {

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public EasyAI(String name) {
        super(name);
    }

    //https://stackoverflow.com/questions/6443176/how-can-i-generate-a-random-number-within-a-range-but-exclude-some/6443346
    public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        ScrabbleState scrabbleCopy = new ScrabbleState((ScrabbleState) info);
        ScrabbleDictonary scrabbleDic = new ScrabbleDictonary();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = scrabbleCopy.getBoard();
        char[] hand = new char[7];
        hand = scrabbleCopy.getHand();
        ScrabbleLetter testLetter;
        String playString = null;
        int myRow;
        int myCol;
        boolean foundWord = false;
        //for loop goes through board and sets testLetter to a non null Letter
        for(int row = 0; row < 15; row ++){
            for(int col = 0; col < 15; col++){
                if(board[row][col] != null){
                    if(board[row][col-1] == null || board[row][col+1] == null || board[row-1][col] == null || board[row+1][col] == null){
                        testLetter = board[row][col];
                        int x = 0;
                        while(x < 100) {
                            Random rand = new Random();

                            int random1 = rand.nextInt(7);
                            int random2 = getRandomWithExclusion(rand, 0, 6, random1);
                            int random3 = getRandomWithExclusion(rand, 0, 6, random1, random2);
                            int random4 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3);
                            int random5 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4);
                            int random6 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5);
                            int random7 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5, random6);

                            playString = testLetter.getLetter() + hand[random1] + hand[random2] + hand[random3] + hand[random4]
                                    + hand[random5] +hand[random6] + hand[random7];

                            if(ScrabbleDictonary.isLegal(playString, board, row, col)){
                                myRow = row;
                                myCol = col;
                                foundWord = true;
                                break;
                            }
                        }

                    }
                }
            }
        }
        if(foundWord == false){
            //shuffle hand
        }



    }
}
