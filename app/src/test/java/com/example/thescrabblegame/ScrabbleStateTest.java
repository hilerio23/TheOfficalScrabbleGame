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

    public void testSetPlayer1Score() throws Exception{
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer1Score(-20);
        assertEquals(-20, scrabbleState.getPlayer1Score());
    }

    public void testSetPlayer2Score() throws Exception{
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer2Score(-20);
        assertEquals(-20, scrabbleState.getPlayer2Score());
    }

    public void testSetPlayer3Score() throws Exception{
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer3Score(-20);
        assertEquals(-20, scrabbleState.getPlayer3Score());
    }

    public void testSetPlayer4Score() {
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer4Score(-20);
        assertEquals(-20, scrabbleState.getPlayer4Score());
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

    public void testGetIdNum() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        assertEquals(id, 0);
    }

    public void testGetPlayer1Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int score = state.getPlayer1Score();
        assertEquals(score,0);
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