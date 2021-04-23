package com.example.thescrabblegame;

import android.content.res.Resources;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

import static com.example.thescrabblegame.HumanScrabblePlayer.dictionary;

public class ScrabbleDictionary {


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
//check if word is in dictionary
    public boolean isLegal(String word) {
        if(dictionary.contains(word)){
            return true;
        }
        return false;
    }

}
