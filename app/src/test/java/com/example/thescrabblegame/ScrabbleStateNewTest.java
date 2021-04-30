package com.example.thescrabblegame;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import java.lang.SecurityManager;

import java.security.spec.ECField;

import static org.junit.Assert.assertNotEquals;

public class ScrabbleStateNewTest extends TestCase {

    //by Anabel
    public void testSetIdNum() throws Exception {
        ScrabbleState state = new ScrabbleState();
        state.setIdNum(1);
        int id = state.getIdNum();
        assertEquals(id, 1);
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
    public void testSetPlayer3Score() throws Exception {
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
    public void testSetBoard() throws Exception {
        ScrabbleState scrabbleState = new ScrabbleState();
        ScrabbleLetter[][] board = new ScrabbleLetter[15][15];
        ScrabbleLetter[][] myBoard = new ScrabbleLetter[15][15];
        board = scrabbleState.getBoard();
        scrabbleState.setBoard(board);
        myBoard = scrabbleState.getBoard();
        assertEquals(board, myBoard);
    }

    //by Anabel
    public void testSetOver() throws Exception {
        ScrabbleState state = new ScrabbleState();
        state.setOver(1);
        int checkOver = state.getOver();
        assertEquals(1, checkOver);
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
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
    public void testGetIdNum() throws Exception {
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        assertEquals(id, 0);
    }

    //by Anabel
    public void testGetPlayer1Score() throws Exception {
        ScrabbleState state = new ScrabbleState();
        int newScore = 20;
        state.setPlayer1Score(newScore);
        int score = state.getPlayer1Score();
        assertEquals(score, newScore);
    }

    //by Anabel
    public void testGetPlayer2Score() throws Exception {
        ScrabbleState state = new ScrabbleState();
        int newScore = 30;
        state.setPlayer2Score(newScore);
        int score = state.getPlayer2Score();
        assertEquals(score, newScore);
    }

    //by Anabel
    public void testGetPlayer3Score() throws Exception {
        ScrabbleState state = new ScrabbleState();
        int newScore = 33;
        state.setPlayer3Score(newScore);
        int score = state.getPlayer3Score();
        assertEquals(score, newScore);
    }

    //by Anabel
    public void testGetPlayer4Score() throws Exception {
        ScrabbleState state = new ScrabbleState();
        int newScore = 44;
        state.setPlayer4Score(newScore);
        int score = state.getPlayer4Score();
        assertEquals(score, newScore);
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
        ScrabbleState state = new ScrabbleState();
        int checkOver = state.getOver();
        assertEquals(0, checkOver);
    }

    //by Anabel
    public void testGetNumPasses() {
        ScrabbleState state = new ScrabbleState();
        state.setNumPasses(2);
        int numPasses = state.getNumPasses();
        assertEquals(2, numPasses);
    }

    //by Samone
    public void testScoreOver() throws Exception{
        ScrabbleState state = new ScrabbleState();

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        ScrabbleLetter[] letters = {b,a,n,d,a,n,b};

        int oldScore = 88;
        int handScore = 12;
        int diff = (oldScore - handScore);

        state.setPlayer1Score(oldScore);
        state.setPlayer1Hand(letters);
        state.scoreOver();
        assertEquals(diff, state.getPlayer1Score() );


    }
    //by Samone
    public void testGetPlayer1Hand() throws Exception {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
    public void testGetPlayer2Hand() throws Exception {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
    public void testGetPlayer4Hand() throws Exception {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters = new ScrabbleLetter[4];

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
    public void testPlayWord() throws Exception {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters = new ScrabbleLetter[4];
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
        int[] special = {0, 0, 0, 0};
        state.playWord(letters, special, xNums, yNums, false);
        ScrabbleLetter[][] board = state.getBoard();
        ScrabbleLetter[] word = {board[4][7], board[5][7], board[6][7], board[7][7]};
        assertEquals(letters, word);
    }

    //by Anabel
    public void testExchange() throws Exception {
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter[] letters = new ScrabbleLetter[state.getPlayer1Hand().length];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = state.getPlayer1Hand()[i];
        }
        state.exchange(state.getPlayer1Hand());
        ScrabbleLetter[] newLetters = new ScrabbleLetter[state.getPlayer1Hand().length];
        for (int i = 0; i < newLetters.length; i++) {
            newLetters[i] = state.getPlayer1Hand()[i];
        }
        assertNotEquals(letters, newLetters);
    }

    //by Samone
    public void testIsVertical() throws Exception {
        ScrabbleState state = new ScrabbleState();
        int[] x = {3, 4, 5, 6, 7};
        int[] y = {7, 7, 7, 7, 7};
        assertEquals(false, state.isVertical(x, y));
    }

    //by Samone
    public void testArrToString() throws Exception{
        ScrabbleState state = new ScrabbleState();

        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter[] word = {b,b,b,b,b,b};
        String wordString = "bbbbbb";
        String arrString = state.arrToString(word);

        assertEquals(wordString, arrString);
    }

    //by Samone
    public void testIsCentered() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int[] xPos = {7,7,7,7};
        int[] yPos = {4,5,6,7};
        state.isCentered(xPos, yPos);
        boolean centered = state.getIsCentered();
        assertEquals(true, centered);

    }

    //by Samone
    public void testScore() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int score = 7;
        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter d = new ScrabbleLetter('d');
        ScrabbleLetter[] band = {a,n,d};
        int[] special = {0,0,0,0};
        int[] x = {4,6,7};
        int[] y = {7,7,7};
        state.score(band, state.getBoard(), x, y, b, special);
        int playerScore = state.getPlayer1Score();
        assertEquals(score, playerScore);
    }

    //by Samone
    public void testReplaceTiles() throws Exception{
        ScrabbleState state = new ScrabbleState();
        ScrabbleLetter t = new ScrabbleLetter('t');
        int isDifferent = 0;
        ScrabbleLetter[] before = {t,t,t,t,t,t,t};
        state.setPlayer1Hand(before);
        state.replaceTiles(state.getPlayer1Hand());
        ScrabbleLetter[] after = state.getPlayer1Hand();
        for(int i = 0; i < after.length; i++){
            if(!(t.equals(after[i]))){
                isDifferent = 1;
                break;
            }
        }
        assertNotEquals(0, isDifferent);
    }

    //by Samone
    public void testIsContinuous() throws Exception{
        ScrabbleState state = new ScrabbleState();
        int[] x = {1,3,4,5};
        int[] y = {7,7,7,7};
        boolean cont = state.isContinuous(x,y);
        assertEquals(false, cont);

    }

    //by Anabel
    public void testPass() {
        ScrabbleState state = new ScrabbleState();
        state.getIdNum();
        state.pass();
        ;
        int newId = state.getIdNum();
        assertEquals(0, newId);
    }

    //by Anabel
    public void testExitGame() {
        ScrabbleState state = new ScrabbleState();
        //System.exit(1);
        state.exitGame();
        int checkOver = state.getOver();
        assertEquals(1, checkOver);
    }
}