package com.example.thescrabblegame;

import junit.framework.TestCase;

import java.security.spec.ECField;

import static org.junit.Assert.assertNotEquals;

public class ScrabbleStateNewTest extends TestCase {

    //by Anabel
    public void testSetIdNum() throws Exception{
        ScrabbleState state = new ScrabbleState();
        state.setIdNum(1);
        int id = state.getIdNum();
        assertEquals(id,1);
    }

    //by Tamsen
    public void testSetPlayer1Score() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer1Score(-20);
        assertEquals(-20, scrabbleState.getPlayer1Score());
    }

    //by Tamsen
    public void testSetPlayer2Score() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer2Score(-20);
        assertEquals(-20, scrabbleState.getPlayer2Score());
    }

    //by Tamsen
    public void testSetPlayer3Score() throws Exception{
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer3Score(-20);
        assertEquals(-20, scrabbleState.getPlayer3Score());
    }

    //by Tamsen
    public void testSetPlayer4Score() {
        ScrabbleState scrabbleState = new ScrabbleState();
        scrabbleState.setPlayer4Score(-20);
        assertEquals(-20, scrabbleState.getPlayer4Score());
    }

    //by Alec
    public void testSetBoard() throws Exception{
        ScrabbleState scrabbleState = new ScrabbleState();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        ScrabbleLetter[][] myBoard = new ScrabbleLetter[15][15];
        board = scrabbleState.getBoard();
        scrabbleState.setBoard(board);
        myBoard = scrabbleState.getBoard();
        assertEquals(board, myBoard);
    }

    //by Anabel
    public void testSetOver() {
    }

    //by Samone
    public void testSetNumPasses() {
        ScrabbleState state = new ScrabbleState();
        state.setNumPasses(2);

        assertEquals(2, state.getNumPasses());

    }

    //by Samone
    public void testSetPlayer1Hand() {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer1Hand(letters);
        assertEquals(letters, state.getPlayer1Hand());
    }

    //by Samone
    public void testSetPlayer2Hand() {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer2Hand(letters);
        assertEquals(letters, state.getPlayer2Hand());
    }

    //by Samone
    public void testSetPlayer3Hand() {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer3Hand(letters);
        assertEquals(letters, state.getPlayer3Hand());
    }

    //by Samone
    public void testSetPlayer4Hand() {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer4Hand(letters);
        assertEquals(letters, state.getPlayer4Hand());
    }

    //by Anabel
    public void testGetIdNum() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        assertEquals(id, 0);
    }

    //by Anabel
    public void testGetPlayer1Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 20;
        state.setPlayer1Score(newScore);
        int score = state.getPlayer1Score();
        assertEquals(score,newScore);
    }

    //by Anabel
    public void testGetPlayer2Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 30;
        state.setPlayer2Score(newScore);
        int score = state.getPlayer2Score();
        assertEquals(score,newScore);
    }

    //by Anabel
    public void testGetPlayer3Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 33;
        state.setPlayer3Score(newScore);
        int score = state.getPlayer3Score();
        assertEquals(score,newScore);
    }

    //by Anabel
    public void testGetPlayer4Score() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int newScore = 44;
        state.setPlayer4Score(newScore);
        int score = state.getPlayer4Score();
        assertEquals(score,newScore);
    }

    //by Alec
    public void testGetBoard() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        board = null;
        scrabbleState.setBoard(board);
        assertEquals(null, scrabbleState.getBoard());
    }

    //by Anabel
    public void testGetOver() {
    }

    //by Anabel
    public void testGetNumPasses() {
    }

    //by Samone
    public void testGetPlayer1Hand() throws Exception{
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer1Hand(letters);
        assertEquals(letters, state.getPlayer1Hand());
    }

    //by Samone
    public void testGetPlayer2Hand() throws Exception{
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer2Hand(letters);
        assertEquals(letters, state.getPlayer2Hand());
    }

    //by Samone
    public void testGetPlayer3Hand() throws Exception {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer3Hand(letters);
        assertEquals(letters, state.getPlayer3Hand());
    }

    //by Samone
    public void testGetPlayer4Hand() throws Exception{
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;

        state.setPlayer4Hand(letters);
        assertEquals(letters, state.getPlayer4Hand());
    }

    //by Samone
    public void testPlayWord() throws Exception{
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters= new ScrabbleLetter[4];
        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        letters[0] = b;
        letters[1] = a;
        letters[2] = n;
        letters[3] = d;
        int[] xNums = {4, 5, 6, 7};
        int[] yNums = {7, 7, 7, 7};
        int[] special = {0,0,0,0};
        state.playWord(letters, special, xNums, yNums, false );
        ScrabbleLetter[][] board = state.getBoard();
        ScrabbleLetter[] word = {board[4][7], board[5][7], board[6][7], board[7][7]};
        assertEquals(letters, word);
    }

    //by Anabel
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

    //by Samone
    public void testIsVertical() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int[] x = {3, 4, 5, 6, 7};
        int[] y = {7, 7, 7, 7, 7};
        assertEquals(false, state.isVertical(x , y));
    }

    //by Anabel
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