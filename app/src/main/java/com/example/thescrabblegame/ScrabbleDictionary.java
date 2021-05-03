package com.example.thescrabblegame;

import android.content.res.Resources;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

import static com.example.thescrabblegame.HumanScrabblePlayer.dictionary;

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
        if(dictionary.contains(word)){
            return true;
        }
        return false;
    }

}
