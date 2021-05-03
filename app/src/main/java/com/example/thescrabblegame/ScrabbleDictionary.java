package com.example.thescrabblegame;

/**
 * class ScrabbleState controls the state of the game
 *
 * @author Samone Watkins, Alec Uyematsu, and Tamsen Dean
 * @version April 2021
 */
public class ScrabbleDictionary {

    /**
     * Checks to see if tile spaces are next to each other
     *
     * @param myLetter
     * @param row
     * @param col
     * @param isVertical
     * @return boolean
     */
    public boolean isNeighbors(ScrabbleLetter[][] myLetter, int row, int col, boolean isVertical){
        if(row == myLetter.length && col == myLetter[row].length){
            return false;
        }
        else {
            for (int i = 0; i < myLetter.length; i++) {
                if(isVertical) {
                    if (row > 0 && row < 14) {
                        if (myLetter[row - 1][col].getLetter() == ' ' || myLetter[row + 1][col].getLetter() == ' ') {
                            return true;
                        }
                        isNeighbors(myLetter, i + row, col, isVertical);
                    }
                }
                else {
                    if (col > 0 && col < 14) {
                        if (myLetter[row][col - 1] == null || myLetter[row][col + 1] == null) {
                            return false;
                        }
                        if (myLetter[row][col - 1].getLetter() == ' ' || myLetter[row][col + 1].getLetter() == ' ') {
                            return true;
                        }
                        isNeighbors(myLetter, row, i + col, isVertical);
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks to see if word played is in the dictionary
     *
     * @param word
     * @return boolean
     */
    public boolean isLegal(String word) {
        if(HumanScrabblePlayer.dictionary.contains(word)){
            return true;
        }
        return false;
    }

}
