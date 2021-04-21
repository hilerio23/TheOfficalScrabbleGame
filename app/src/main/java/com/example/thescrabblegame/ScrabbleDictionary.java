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

    //https://codereview.stackexchange.com/questions/44021/fast-way-of-searching-for-a-string-in-a-text-file
    public boolean isLegal(String word) {

        String file = "app/src/main/txt/words_alpha.txt";

        try{
            BufferedReader wordIn = new BufferedReader(new FileReader(file));
            String s;
            try {
                while ((s = wordIn.readLine()) != null) {
                    String[] words = s.split(" ");
                    for(String w : words){
                        if (s.equalsIgnoreCase(word)) {
                         return true;
                        }
                    }

                }
                wordIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
}
