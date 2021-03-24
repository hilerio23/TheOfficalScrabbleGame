/**
 * @author Tamsen Dean
 * @author Anabel Hilerio
 * @author Alec Uyematsu
 * @author Samone Watkins
 */
package com.example.thescrabblegame;
import java.util.Random;

public class ScrabbleState {

    //15 x 15 board
    private ScrabbleLetter[][] board = new ScrabbleLetter[15][15];

    //an in to tell whos move it is 0 for human 1 for AI
    private int playerToMove;

    //array of SrabbleLetters for hand
    private ScrabbleLetter[] player1Hand = new ScrabbleLetter[7];
    private ScrabbleLetter[] player2Hand = new ScrabbleLetter[7];
    private ScrabbleLetter[] player3Hand = new ScrabbleLetter[7];
    private ScrabbleLetter[] player4Hand = new ScrabbleLetter[7];

    //array of ScrabbleLetters for Pool (100) total letters
    private ScrabbleLetter[] pool = new ScrabbleLetter[100]; //arraylist

    //game pause: 1 for pause 0 for playing
    private int gamePause;

    //add score
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerThreeScore = 0;
    private int playerFourScore = 0;

    //track how empty the percent is
    double percentOfPool = 0.0;

    //extra variable for tracking if a move is possible
    boolean isPossible;

    //constructor
    public ScrabbleState(){

        //sets board to blanks
        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = new ScrabbleLetter(' ');
            }
        }

        for(int i = 0; i < 7; i++){
            //generating random chars for each player
            Random rnd = new Random();
            char randomChar1 = (char) ('a' + rnd.nextInt(26));
            char randomChar2 = (char) ('a' + rnd.nextInt(26));
            char randomChar3 = (char) ('a' + rnd.nextInt(26));
            char randomChar4 = (char) ('a' + rnd.nextInt(26));

            //creating ScrabbleLetters
            player1Hand[i] = new ScrabbleLetter(randomChar1);
            player2Hand[i] = new ScrabbleLetter(randomChar2);
            player3Hand[i] = new ScrabbleLetter(randomChar3);
            player4Hand[i] = new ScrabbleLetter(randomChar4);

        }

        //creating a random pool of all of the letter tiles
        for(int i = 0; i < 100; i++){
            Random rnd = new Random();
            char randomChar = (char) ('a' + rnd.nextInt(26));
            pool[i] = new ScrabbleLetter(randomChar);
        }

        //player 0 is human player
        playerToMove = 0;
        //0 = playing, 1 = pause
        gamePause = 0;

    }

    //Deep copy of the given Scrabble State
    public ScrabbleState(ScrabbleState scrabbleStateCopy){
        this.playerToMove = scrabbleStateCopy.playerToMove;
        this.gamePause = scrabbleStateCopy.gamePause;
        this.pool = scrabbleStateCopy.pool;
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                board[x][y] = scrabbleStateCopy.board[x][y];
            }
        }
        this.player1Hand = scrabbleStateCopy.player1Hand;
        this.player2Hand = null; //player1 can't see player 2 hand
        this.player3Hand = null;
        this.player4Hand = null;
    }

    @Override
    public String toString(){

        //the string to return
        String output;

        //print if the game is paused and who's move it is
        output = "\ngame pause: " + this.gamePause + "\n";
        output += "player to move: " + this.playerToMove + "\n";

        //adding the board to the string
        output += "board: The board is empty";
        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                output += this.board[i][j].getLetter() + " ";
            }
        }
        output += "\n";

        //adding the pool to the string
        output += "pool: \n";
        for(int i = 0; i < 100; i++){
            output += this.pool[i].getLetter() + " ";
        }
        output += "\n";

        //adding the hand to the string
        output += "player one hand: \n";
        for(int i = 0; i < 7; i++){
            output += this.player1Hand[i].getLetter() + " ";
        }
        output += "\n";

        //adding player 2's hand if it's not null
        if(this.player2Hand != null) {
            output += "player two hand: \n";
            for (int i = 0; i < 7; i++) {
                output += this.player2Hand[i].getLetter() + " ";
            }
        }

        //adding player 3's hand if it's not null
        if(this.player3Hand != null) {
            output += "player three hand: \n";
            for (int i = 0; i < 7; i++) {
                output += this.player3Hand[i].getLetter() + " ";
            }
        }

        //adding player 4's hand if it's not null
        if(this.player4Hand != null) {
            output += "player four hand: \n";
            for (int i = 0; i < 7; i++) {
                output += this.player4Hand[i].getLetter() + " ";
            }
        }
        output += "\n";
        return output;

    }

    /**
     *
     * Methods in actions.txt
     *Also got rid of challenge because I don't think it's necessary
     *
     */

    public boolean isLegal(ScrabbleState scrabbleState){
        return true;
    }

    //param should be an arraylist of scrabble letters
    //param which player
    public boolean playWord(ScrabbleState scrabbleState){
        if(isLegal(scrabbleState) == true){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean pass(ScrabbleState scrabbleState){
        if(isLegal(scrabbleState) == true){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean exchange(ScrabbleState scrabbleState){
        if(isLegal(scrabbleState) == true){
            return true;
        }
        else{
            return false;
        }
    }

}
