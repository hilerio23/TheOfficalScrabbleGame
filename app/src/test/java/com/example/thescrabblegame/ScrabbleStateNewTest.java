package com.example.thescrabblegame;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class ScrabbleStateNewTest extends TestCase {

    public void testSetIdNum() {
    }

    public void testSetPlayer1Score() {
    }

    public void testSetPlayer2Score() {
    }

    public void testSetPlayer3Score() {
    }

    public void testSetPlayer4Score() {
    }

    public void testSetBoard() {
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

    public void testGetIdNum() {
    }

    public void testGetPlayer1Score() {
    }

    public void testGetPlayer2Score() {
    }

    public void testGetPlayer3Score() {
    }

    public void testGetPlayer4Score() {
    }

    public void testGetBoard() {
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

    public void testExchange() {
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

    public void testIsVertical() {
    }

    public void testPass() {
        ScrabbleState state = new ScrabbleState();
        int id = state.getIdNum();
        state.pass();;
        int newId = state.getIdNum();
        assertEquals(1, newId);
    }

    public void testExitGame() {
    }
}