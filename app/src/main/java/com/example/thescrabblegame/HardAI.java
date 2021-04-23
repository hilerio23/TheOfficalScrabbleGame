package com.example.thescrabblegame;

import android.view.View;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameComputerPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import static com.example.thescrabblegame.HumanScrabblePlayer.dictionary;

public class HardAI extends GameComputerPlayer {

    private ArrayList<String> sowpodsList; //for dictionary;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public HardAI(String name) {
        super(name);
    }

    /**
     * External Citation
     * Date: 18 April 2021
     * Problem: Needed to generate random numbers with exceptions.
     * Resource: https://stackoverflow.com/questions/6443176/how-can-i-generate-a-random-number-within-a-range-but-exclude-some/6443346
     * Solution: I used the example code from this post.
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


    //variables to be called in RecieveInfo
    private boolean playWordVertical;
    private int[] playX;
    private int[] playY;
    private ScrabbleLetter[] wordToPlay;
    private int[] specialTileArray;
    private int points;
    /*
     * Method sets variables to then play a word in RecieveInfo
     */
    public void findPlayWord(ScrabbleLetter[][] board, int x, int y, ScrabbleLetter letter, ArrayList<String> myWords) {
        ArrayList<Integer> compareX = new ArrayList<Integer>();
        ArrayList<Integer> compareY = new ArrayList<Integer>();
        //adds x's and y's that are occupied to an arraylist
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                if (board[row][col].getLetter() != ' ') {
                    if (row != x || col != y) {

                        compareX.add(row);
                        compareY.add(col);
                    }
                }
            }
        }
        //initalizing variables for the for loop
        int pointsToPlay = 0;
        boolean isVerticalToPlay = false;
        String stringToPlay = null;

        //going through words that have possibility to be played
        for (int j = 0; j < myWords.size(); j++) {
            //gets word at index j
            String word = myWords.get(j);
            //finds where the letter where the word needs to be played off of
            int index = word.indexOf(letter.getLetter());

            ArrayList<Integer> compareXCopy = new ArrayList<>();
            ArrayList<Integer> compareYCopy = new ArrayList<>();
            compareXCopy = compareX;
            compareYCopy = compareY;
            boolean isOverlap = false;
            //horizontal check to see if there is an overlap
            for (int i = 0; i < index; i++) {
                //before index
                if (board[x - index + i][y].getLetter() != ' ') {
                    isOverlap = true;
                }
            }
            for(int i = 0; i < word.length() - index; i++) {
                //after index
                if (board[x+1+i][y].getLetter() != ' '){
                    isOverlap = true;
                }
            }
            if (!isOverlap) {
                //find the largest value word
                if (toPoints(word) > pointsToPlay) {
                    //sets points to pointsToPlay inorder to find the largest value word
                    pointsToPlay = toPoints(word);
                    //setting values to private variables in order to be called in different method
                    stringToPlay = word;
                    isVerticalToPlay = false;
                    this.wordToPlay = toScrabbleLetter(stringToPlay);
                    this.playWordVertical = isVerticalToPlay;
                    int[] xArrayToPlay = new int[word.length()];
                    int[] yArrayToPlay = new int[word.length()];
                    for (int b = 0; b < word.length(); b++) {
                        xArrayToPlay[b] = x - index + b;
                        yArrayToPlay[b] = y;
                    }
                    this.playX = xArrayToPlay;
                    this.playY = yArrayToPlay;
                    this.points = pointsToPlay;
                }
            }
            isOverlap = false;
            for (int i = 0; i < index; i++) {
                //before index
                if (board[x][y - index + i].getLetter() != ' ') {
                    isOverlap = true;
                }
            }
            for(int i = 0; i < word.length() - index; i++) {
                //after index
                if (board[x][y+1+i].getLetter() != ' '){
                    isOverlap = true;
                }
            }
            if (!isOverlap) {
                //find the largest value word
                if (toPoints(word) > pointsToPlay) {
                    //sets points to pointsToPlay inorder to find the largest value word
                    pointsToPlay = toPoints(word);
                    //setting values to private variables in order to be called in different method
                    stringToPlay = word;
                    isVerticalToPlay = true;
                    this.wordToPlay = toScrabbleLetter(stringToPlay);
                    this.playWordVertical = isVerticalToPlay;
                    //setting x and y coord arrays
                    int[] xArrayToPlay = new int[word.length()];
                    int[] yArrayToPlay = new int[word.length()];
                    for (int b = 0; b < word.length(); b++) {
                        xArrayToPlay[b] = x;
                        yArrayToPlay[b] = y - index + b;
                    }
                    this.playX = xArrayToPlay;
                    this.playY = yArrayToPlay;
                    this.points = pointsToPlay;
                }
            }
        }
        //removing for next round
        compareX.removeAll(compareX);
        compareY.removeAll(compareY);


    }

    /*
     * Method takes in a string and turns it to an array of ScrabbleLetters
     */
    public ScrabbleLetter[] toScrabbleLetter(String myString) {
        ScrabbleLetter[] returnLetter = new ScrabbleLetter[myString.length()];
        for (int i = 0; i < myString.length(); i++) {
            returnLetter[i] = new ScrabbleLetter(myString.charAt(i));
        }
        return returnLetter;
    }

    /*
     *Method that given a string returns the amount of points it is given
     * does not take into consideration special letters/words
     */
    public int toPoints(String myString) {
        ScrabbleLetter[] returnLetter = new ScrabbleLetter[myString.length()];
        int points = 0;
        for (int i = 0; i < myString.length(); i++) {
            returnLetter[i] = new ScrabbleLetter(myString.charAt(i));
            points += returnLetter[i].getPoints();
        }
        return points;
    }

    /*
     *Method takes in a String of chars and a letter and
     * returns an ArrayList of all the words that contain that
     * char and are in the dictionary
     */

    /**
     * External Citation
     * Date: 22 April 2021
     * Problem: Couldn't search through a list of English words
     * Resource: https://stackoverflow.com/questions/31623184/find-all-words-in-dictionary-given-a-string-of-words
     * Solution: I used the example code from this post.
     */

    public ArrayList<String> getAllWordsInDic(String input, char letter) {
        ArrayList<String> matches = new ArrayList<String>();
        sowpodsList = dictionary;
        // for each word in dict
        for (String word : sowpodsList) {

            // match flag
            Boolean nonMatch = true;

            // for each character of dict word
            for (char chW : word.toCharArray()) {

                String w = Character.toString(chW);

                // if the count of chW in word is equal to its count in input,
                // then, they are match
                if (word.length() - word.replace(w, "").length() !=
                        input.length() - input.replace(w, "").length()) {
                    nonMatch = false;
                    break;
                }
            }
            if (nonMatch) {
                String temp = Character.toString(letter);
                //word has to contain the letter its being played on
                if (word.contains(temp)) {
                    matches.add(word);
                }
            }
        }
        return matches;
    }

    /*
     *Method recieves info and then either plays word or exchanges
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        ScrabbleState scrabbleCopy = new ScrabbleState((ScrabbleState) info);

        //if our turn
        if (scrabbleCopy.getIdNum() == 1) {
            ScrabbleDictionary scrabbleDic = new ScrabbleDictionary();

            //creates copy of board
            ScrabbleLetter[][] board;
            board = scrabbleCopy.getBoard();
            ScrabbleLetter[][] playBoard;
            playBoard = scrabbleCopy.getBoard();
            //creates copy of hand
            ScrabbleLetter[] hand = scrabbleCopy.getPlayer2Hand();

            //initializing variables
            ScrabbleLetter testLetter = null;
            String playString = null;
            double myRow;
            double myCol;
            boolean foundWord = false;
            boolean foundSpace = false;
            boolean vertical = false;
            int tempX = -1;
            int tempY = -1;
            int tempPoints = 0;
            ScrabbleLetter[] myWordToPlay = null;
            int[] myPlayX = null;
            int[] myPlayY = null;
            boolean myPlayVertical = false;

            //nested for loop goes through board and sets testLetter to a non null Letter
            for (int row = 0; row < 15; row++) {
                for (int col = 0; col < 15; col++) {
                    //finds letters played on board
                    if (board[row][col] != null && !foundSpace) {
                        //if there is space for the word to be played around it
                        if (board[row][col].getLetter() != ' ') {
                            testLetter = board[row][col]; //creates scrabble letter
                            foundSpace = true;
                            tempX = row;
                            tempY = col;
                            board[row][col].setName(' '); //sets it to empty to find all possible words
                            //adds all possible letters to a string to find all possible words
                            String handString = Character.toString(testLetter.getLetter());
                            for (int i = 0; i < hand.length; i++) {
                                handString += Character.toString(hand[i].getLetter());
                            }
                            this.wordToPlay = null;
                            ArrayList<String> dicWords = new ArrayList<>();
                            //finds all words in dictionary given hand and test letter
                            dicWords = getAllWordsInDic(handString, testLetter.getLetter());
                            findPlayWord(playBoard, tempX, tempY, testLetter, dicWords);
                            //finds highest value word
                            if(this.points > tempPoints){
                                myWordToPlay = this.wordToPlay;
                                myPlayX = this.playX;
                                myPlayY = this.playY;
                                myPlayVertical = this.playWordVertical;
                                tempPoints = this.points;
                            }
                        }
                    }
                }
            }//is there a better way to do this? probably...

            if(this.wordToPlay != null) {
                int[] specialPlay = new int[this.wordToPlay.length];
                for (int i = 0; i < this.wordToPlay.length; i++) {
                    specialPlay[i] = 0;
                }
                PlayWord playWord = new PlayWord(this,myWordToPlay, specialPlay, myPlayX, myPlayY, myPlayVertical);
                game.sendAction(playWord);
                foundWord = true;
            }



            //exchange if we couldn't find word
            if (!foundWord) {
                Random rand = new Random();
                //creating random ints 0 through 6 each one is its own exclusive int
                int random1 = rand.nextInt(7);
                int random2 = getRandomWithExclusion(rand, 0, 6, random1);
                int random3 = getRandomWithExclusion(rand, 0, 6, random1, random2);
                int random4 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3);
                int random5 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4);
                int random6 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5);
                int random7 = getRandomWithExclusion(rand, 0, 6, random1, random2, random3, random4, random5, random6);

                /** External Citation
                 Date: 18 April 2021
                 Problem: Needed to create a randomized array list
                 Resource: https://stackoverflow.com/questions/28970799/how-to-create-a-array-with-n-random-integers/44487538
                 Solution: I used the example code from this post.
                 */
                int n = rand.nextInt(7);
                int x = n + 1;
                ArrayList<ScrabbleLetter> list = new ArrayList<ScrabbleLetter>();
                ScrabbleLetter[] tempHand = new ScrabbleLetter[x];

                //sets random ScrabbleLetters to be exchanged
                if (x == 1) {
                    tempHand[0] = hand[random1];
                } else if (x == 2) {
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                } else if (x == 3) {
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                } else if (x == 4) {
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                } else if (x == 5) {
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                    tempHand[4] = hand[random5];
                } else if (x == 6) {
                    tempHand[0] = hand[random1];
                    tempHand[1] = hand[random2];
                    tempHand[2] = hand[random3];
                    tempHand[3] = hand[random4];
                    tempHand[4] = hand[random5];
                    tempHand[5] = hand[random6];
                } else if (x == 7) {
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

    /*
     * Method gets changes the temp ints array to see
     * if there are any special tiles being played
     */

    public int[] getSpecialArray(ArrayList<Integer> tempInts) {
        specialTileArray = new int[tempInts.size()];

        for (int i = 0; i < tempInts.size(); i++) {
            int type = getSpecialTile(tempInts.get(i));
            specialTileArray[i] = type;
        }
        return specialTileArray;
    }

    /*
     *method gives us the special tiles
     */

    public int getSpecialTile(int tile) {
        switch (tile) {
            case 1:
            case 8:
            case 15:
            case 106:
            case 120:
            case 121:
            case 128:
            case 225:
                return 1;
            case 17:
            case 29:
            case 33:
            case 43:
            case 49:
            case 57:
            case 65:
            case 71:
            case 155:
            case 161:
            case 169:
            case 177:
            case 183:
            case 193:
            case 197:
            case 209:
                return 2;
            case 21:
            case 25:
            case 77:
            case 81:
            case 85:
            case 89:
            case 137:
            case 141:
            case 145:
            case 149:
            case 201:
            case 205:
                return 3;
            case 37:
            case 39:
            case 53:
            case 93:
            case 97:
            case 99:
            case 103:
            case 109:
            case 117:
            case 123:
            case 127:
            case 129:
            case 133:
            case 173:
            case 187:
            case 189:
                return 4;
            default:
                return 0;
        }

    }
}