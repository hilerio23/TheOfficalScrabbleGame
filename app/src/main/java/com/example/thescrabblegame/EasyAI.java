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

public class EasyAI extends GameComputerPlayer {

    private ArrayList<String> sowpodsList; //for dictionary;

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


    //variables to be called in RecieveInfo
    private boolean playWordVertical;
    private int[] playX;
    private int[] playY;
    private ScrabbleLetter[] wordToPlay;

    /*
     * Method sets variables to then play a word in RecieveInfo
     */
    public void findPlayWord(ScrabbleLetter[][] board, int x, int y, ScrabbleLetter letter, ArrayList<String> myWords) {
        ArrayList<Integer> compareX = new ArrayList<Integer>();
        ArrayList<Integer> compareY = new ArrayList<Integer>();
        //adds x's and y's that are occupied to an arraylist
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                if (board[row][col].getLetter() != ' ') ;
                compareX.add(row);
                compareY.add(col);
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
            //before and after letter helps find if the word can be played
            //by checking if there is an overlap
            int beforeLetter = index - 1;
            int afterLetter = word.length() - index;
            boolean isOverlap = false;
            //horizontal check to see if there is an overlap
            for (int a = 0; a < beforeLetter; a++) {
                if (x - beforeLetter + a == compareX.get(a) && y == compareY.get(a)) {
                    for (int i = 0; i < afterLetter; i++) {
                        if (x + afterLetter - i == compareX.get(i) && y == compareY.get(i)) {
                            isOverlap = true;
                        }
                    }
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
                }
            }
            //vertical check to see if there is an overlap
            for (int a = 0; a < beforeLetter; a++) {
                if (a == compareX.get(a) && y - beforeLetter + a == compareY.get(a)) {
                    for (int i = 0; i < afterLetter; i++) {
                        if (x == compareX.get(i) && y + afterLetter - i == compareY.get(i)) {
                            isOverlap = true;
                        }
                    }
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

    //https://stackoverflow.com/questions/31623184/find-all-words-in-dictionary-given-a-string-of-words
    public ArrayList<String> getAllWordsInDic(String input, char letter) {
        ArrayList<String> matches = new ArrayList<String>();
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
        ScrabbleDictionary scrabbleDic = new ScrabbleDictionary();

        //creates copy of board
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = scrabbleCopy.getBoard();

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

        //if our turn
        if (scrabbleCopy.getIdNum() == 1) {

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
                        }
                    }
                }
            }
            if (foundSpace) {
                //adds all possible letters to a string to find all possible words
                String handString = Character.toString(testLetter.getLetter());
                for (int i = 0; i < hand.length; i++) {
                    handString += Character.toString(hand[i].getLetter());
                }
                ArrayList<String> dicWords = new ArrayList<>();
                //finds all words in dictionary given hand and test letter
                dicWords = getAllWordsInDic(handString, testLetter.getLetter());
                findPlayWord(board, tempX, tempY, testLetter, dicWords);
                foundWord = true;
                PlayWord playWord = new PlayWord(this, this.wordToPlay, this.playX, this.playY, this.playWordVertical);
                game.sendAction(playWord);
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

                //https://stackoverflow.com/questions/28970799/how-to-create-a-array-with-n-random-integers/44487538
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
}