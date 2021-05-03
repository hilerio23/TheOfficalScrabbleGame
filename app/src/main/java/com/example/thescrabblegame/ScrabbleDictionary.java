package com.example.thescrabblegame;

/**
 * class ScrabbleState controls the state of the game
 *
 * @author  Alec Uyematsu and Tamsen Dean
 * @version April 2021
 */
public class ScrabbleDictionary {

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
