package com.example.thescrabblegame;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class ScrabbleStateNewTest extends TestCase {

    public void testSetIdNum() throws Exception{
        ScrabbleState state = new ScrabbleState();
        state.setIdNum(1);
        int id = state.getIdNum();
        assertEquals(id,1);
    }

    public void testSetPlayer1Score() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer1Score(-20);
        assertEquals(-20, scrabbleState.getPlayer1Score());
    }

    public void testSetPlayer2Score() throws Exception {
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

    public void testSetOver() {
    }

    public void testSetNumPasses() {
    }

    public void testSetPlayer1Hand() {
    }

    public void testSetPlayer2Hand() {
    }

    public void testSetPlayer3Hand() {
    }

    public void testSetPlayer4Hand() {
    }

    public void testGetIdNum() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        assertEquals(id, 0);
    }

    public void testGetPlayer1Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 20;
        state.setPlayer1Score(newScore);
        int score = state.getPlayer1Score();
        assertEquals(score,newScore);
    }

    public void testGetPlayer2Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 30;
        state.setPlayer2Score(newScore);
        int score = state.getPlayer2Score();
        assertEquals(score,newScore);
    }

    public void testGetPlayer3Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 33;
        state.setPlayer3Score(newScore);
        int score = state.getPlayer3Score();
        assertEquals(score,newScore);
    }

    public void testGetPlayer4Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 44;
        state.setPlayer4Score(newScore);
        int score = state.getPlayer4Score();
        assertEquals(score,newScore);
    }

    public void testGetBoard() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = null;
        scrabbleState.setBoard(board);
        assertEquals(null, scrabbleState.getBoard());
    }

    public void testGetOver() {
    }

    public void testGetNumPasses() {
    }

    public void testGetPlayer1Hand() {
    }

    public void testGetPlayer2Hand() {
    }

    public void testGetPlayer3Hand() {
    }

    public void testGetPlayer4Hand() {
    }

    public void testPlayWord() {
    }

    public void testExchange() throws Exception{
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters = new ScrabbleLetter[state.getPlayer1Hand().length];
        for(int i = 0; i < letters.length; i++){
            letters[i] = state.getPlayer1Hand()[i];
        }
        state.exchange(state.getPlayer1Hand());
        ScrabbleLetter[] newLetters = new ScrabbleLetter[state.getPlayer1Hand().length];
        for(int i = 0; i < newLetters.length; i++){
            newLetters[i] = state.getPlayer1Hand()[i];
        }
        assertNotEquals(letters, newLetters);
    }

    public void testIsVertical() throws Exception{
    }

    public void testPass() {
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        state.pass();;
        int newId = state.getIdNum();
        assertEquals(0, newId);
    }

    public void testExitGame() {
    }
}