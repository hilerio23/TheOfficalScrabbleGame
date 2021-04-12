package com.example.thescrabblegame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;

public class ScrabbleDictionary {
    public boolean isNeighbors(ScrabbleLetter[][] myLetter, int row, int col, boolean isVertical){
        if(row == myLetter.length && col == myLetter[row].length){
            return false;
        }
        else {
            for (int i = 0; i < myLetter.length; i++) {
                if(isVertical){
                    if(myLetter[row-1][col] != null || myLetter[row+1][col] != null){
                        return true;
                    }
                    isNeighbors(myLetter, i+row, col, isVertical);
                }
                else{
                    if(myLetter[row][col-1] != null || myLetter[row][col+1] != null){
                        return true;
                    }
                    isNeighbors(myLetter, row, i+col, isVertical);
                }
            }
        }
        return false;
    }
    public boolean isLegal(String word) {

        try {
            BufferedReader wordIn = new BufferedReader(new FileReader(
                    "/usr/share/dict/american-english"));
            String s;
            while ((s = wordIn.readLine()) != null) {
                if (s.indexOf(word) != -1) {
                    return true;
                }
            }
            wordIn.close();
        } catch (IOException e) {
        }

        return false;
    }
}
