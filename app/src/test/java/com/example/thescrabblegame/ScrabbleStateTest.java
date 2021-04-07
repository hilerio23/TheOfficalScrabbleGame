package com.example.thescrabblegame;

import junit.framework.TestCase;

public class ScrabbleStateTest extends TestCase {

    public void testTestToString() {
    }

    public void testSetIdNum() throws Exception{
        ScrabbleState state = new ScrabbleState();
        state.setIdNum(1);
        int id = state.getIdNum();
        assertEquals(id,1);
    }

    public void testSetPlayer1Score() {
    }

    public void testSetPlayer2Score() {
    }

    public void testSetPlayer3Score() {
    }

    public void testSetPlayer4Score() {
    }

    public void testSetBoard() throws Exception{
        ScrabbleState scrabbleState = new ScrabbleState();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        ScrabbleLetter[][] myBoard = new ScrabbleLetter[15][15];
        board = scrabbleState.getBoard();
        scrabbleState.setBoard(board);
        myBoard = scrabbleState.getBoard();
        assertEquals(board, myBoard);
    }

    public void testGetIdNum() {
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        assertEquals(id, 0);
    }

    public void testGetPlayer1Score() {
    }

    public void testGetPlayer2Score() {
    }

    public void testGetPlayer3Score() {
    }

    public void testGetPlayer4Score() {
    }

    public void testGetBoard() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = null;
        scrabbleState.setBoard(board);
        assertEquals(null, scrabbleState.getBoard());
    }
}