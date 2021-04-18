package com.example.thescrabblegame;

import android.view.View;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;
import java.util.List;
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
    /*
    *Method returns a random int and excludes ints that are specified
     */
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
        ScrabbleDictionary scrabbleDic = new ScrabbleDictionary();

        //creates copy of board
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = scrabbleCopy.getBoard();

        //creates copy of hand
        ScrabbleLetter[] hand = scrabbleCopy.getPlayer2Hand();

        //initializing variables
        ScrabbleLetter testLetter;
        String playString = null;
        double myRow;
        double myCol;
        boolean foundWord = false;
        if (scrabbleCopy.getIdNum() == 1) {

            /*
            //nested for loop goes through board and sets testLetter to a non null Letter
            for (int row = 0; row < 15; row++) {
                for (int col = 0; col < 15; col++) {
                    //finds letters played on board
                    if (board[row][col] != null) {
                        //if there is space for the word to be played around it
                        if (board[row][col - 1] == null || board[row][col + 1] == null || board[row - 1][col] == null || board[row + 1][col] == null) {
                            testLetter = board[row][col]; //creates scrabble letter
                            int x = 0;
                            //while loop to try a different combinations
                            while (x < 100) {
                                Random rand = new Random();
                                //creating random ints 0 through 6 each one is its own exclusive int
                                int random1 = rand.nextInt(7);
                                int random2 = getRandomWithExclusion(rand, 0, 6, random1);
                                int random3 = getRandomWithExclusion(rand, 0, 6, random1, random2);
                                int random4 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3);
                                int random5 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4);
                                int random6 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5);
                                int random7 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5, random6);

                                //converting chars to strings
                                String myString = String.valueOf(testLetter.getLetter());
                                String myString1 = String.valueOf(hand[random1].getLetter());
                                String myString2 = String.valueOf(hand[random2].getLetter());
                                String myString3 = String.valueOf(hand[random3].getLetter());
                                String myString4 = String.valueOf(hand[random4].getLetter());
                                String myString5 = String.valueOf(hand[random5].getLetter());
                                String myString6 = String.valueOf(hand[random6].getLetter());
                                String myString7 = String.valueOf(hand[random7].getLetter());

                                //creating random word
                                playString = myString + myString1 + myString2 + myString3;

                                if (scrabbleDic.isLegal(playString)) {
                                    myRow = row;
                                    myCol = col;
                                    ScrabbleLetter[] addToBoard = {testLetter, hand[random1], hand[random2], hand[random3]};
                                    foundWord = true;

                                    //PlayWord playWord = new PlayWord(this, addToBoard, myRow, myCol, true); //true  = vertical
                                    //game.sendAction(playWord);
                                    break; //break out of for loop
                                }
                            }
                        }
                    }
                }
            }*/

            //if we wern't able to find a word (Exchange)

            if (foundWord == false) {
                Random rand = new Random();
                //creating random ints 0 through 6 each one is its own exclusive int
                int random1 = rand.nextInt(7);
                int random2 = getRandomWithExclusion(rand, 0, 6, random1);
                int random3 = getRandomWithExclusion(rand, 0, 6, random1, random2);
                int random4 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3);
                int random5 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4);
                int random6 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5);
                int random7 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5, random6);

                //https://stackoverflow.com/questions/28970799/how-to-create-a-array-with-n-random-integers/44487538
                int n = rand.nextInt(7);
                int x = n + 1;
                ArrayList<ScrabbleLetter> list = new ArrayList<ScrabbleLetter>();
                ScrabbleLetter[] tempHand = new ScrabbleLetter[x];

                if (x == 1) {
                    tempHand[0] = hand[random1];
                }
                else if(x == 2){
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                }
                else if(x == 3){
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                }
                else if(x == 4){
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                }
                else if(x == 5){
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                    tempHand[4] = hand[random5];
                }
                else if(x == 6){
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                    tempHand[4] = hand[random5];
                    tempHand[5] = hand[random6];
                }
                else if(x == 7){
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                    tempHand[4] = hand[random5];
                    tempHand[5] = hand[random6];
                    tempHand[6] = hand[random7];
                }


                Exchange exchange = new Exchange(this, tempHand);
                game.sendAction(exchange);
            }
        }
    }
}
