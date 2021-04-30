package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.infoMessage.GameState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * class ScrabbleState controls the state of the game
 *
 * @author Anabel Hilerio, Alec Uyematsu, Samone Watkins
 * @version April 2021
 */
public class ScrabbleState  extends GameState {

    //15 x 15 board
    private ScrabbleLetter[][] board = new ScrabbleLetter[15][15];

    //an in to tell whos move it is 0 for human 1 for AI
    private int playerToMove;

    //array of ScrabbleLetters for hand
    private ScrabbleLetter[] player1Hand = new ScrabbleLetter[7];
    private ScrabbleLetter[] player2Hand = new ScrabbleLetter[7];
    private ScrabbleLetter[] player3Hand = new ScrabbleLetter[7];
    private ScrabbleLetter[] player4Hand = new ScrabbleLetter[7];

    //array of ScrabbleLetters for Pool (100) total letters
    private ScrabbleLetter[] pool = new ScrabbleLetter[100]; //arraylist

    //game pause: 1 for pause 0 for playing
    private int gamePause;

    //add score
    private int player1Score;
    private int player2Score;
    private int player3Score;
    private int player4Score;

    //id of players and num of players
    private int id;
    private int numPlayers;

    //tracks if game is over
    private int over;

    //count passes
    private int numPasses;

    private int firstTurn; //0 for first turn

    //variable for checking if the first word played is centered
    private boolean isCentered;
    private int poolCounter;

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

        //sets the id
        id = 0;
        player1Score = 0;
        player2Score = 0;
        player3Score = 0;
        player4Score = 0;

        //sets number of players
        numPlayers = 2;

        //sets game to not over
        over = 0;

        //sets number of passes
        numPasses = 0;

        //sets it to first turn
        firstTurn = 0;

        //sets the poolCounter to 0
        poolCounter = 0;

    }


    /**
     * Deep copy of the given Scrabble State
     */
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
        this.player2Hand = scrabbleStateCopy.player2Hand; //player1 can't see player 2 hand
        this.player3Hand = null;
        this.player4Hand = null;
        this.id = scrabbleStateCopy.id;
        this.player1Score = scrabbleStateCopy.player1Score;
        this.player2Score = scrabbleStateCopy.player2Score;
        this.player3Score = scrabbleStateCopy.player3Score;
        this.player4Score = scrabbleStateCopy.player4Score;
        this.numPlayers = scrabbleStateCopy.numPlayers;
        this.over = scrabbleStateCopy.over;
        this.numPasses = scrabbleStateCopy.numPasses;
        this.firstTurn = scrabbleStateCopy.firstTurn;
        this.poolCounter = scrabbleStateCopy.poolCounter;
    }

    /**
     * Setter methods
     */
    public void setIdNum(int num){
        id = num;
    }
    public void setPlayer1Score(int score){
        player1Score = score;
    }
    public void setPlayer2Score(int score){
        player2Score = score;
    }
    public void setPlayer3Score(int score){
        player3Score = score;
    }
    public void setPlayer4Score(int score){
        player4Score = score;
    }
    public void setBoard(ScrabbleLetter[][] newBoard){
        board = newBoard;
    }
    public void setOver(int isOver){
        over = isOver;
    }
    public void setNumPasses(int passes){
        numPasses = passes;
    }
    public void setPlayer1Hand(ScrabbleLetter[] hand){
        player1Hand = hand;
    }
    public void setPlayer2Hand(ScrabbleLetter[] hand){
        player2Hand = hand;
    }
    public void setPlayer3Hand(ScrabbleLetter[] hand){
        player3Hand = hand;
    }
    public void setPlayer4Hand(ScrabbleLetter[] hand){
        player4Hand = hand;
    }
    public void setIsCentered(boolean centered) {isCentered = centered;}
    public void setFirstTurn(int turn){firstTurn = turn;}

    /**
     * Getter methods
     */
    public int getIdNum(){
        return id;
    }
    public int getPlayer1Score(){
        return player1Score;
    }
    public int getPlayer2Score(){
        return player2Score;
    }
    public int getPlayer3Score(){
        return player3Score;
    }
    public int getPlayer4Score(){
        return player4Score;
    }
    public boolean getIsCentered() {return isCentered; }
    public ScrabbleLetter[][] getBoard() {
        return board;
    }
    public int getOver(){
        return over;
    }
    public int getNumPasses(){
        return numPasses;
    }
    public ScrabbleLetter[] getPlayer1Hand(){
        return player1Hand;
    }
    public ScrabbleLetter[] getPlayer2Hand(){
        return player2Hand;
    }
    public ScrabbleLetter[] getPlayer3Hand(){
        return player3Hand;
    }
    public ScrabbleLetter[] getPlayer4Hand(){
        return player4Hand;
    }

    /** External Citation
     Date: 18 April 2021
     Problem: Needed to convert array list to string
     Resource: https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string
     Solution: I used the example code from this post.
     */

    /**
     * Convert tiles placed on the board to a word
     * @param arr
     */
    public String arrToString(ScrabbleLetter[] arr){
        String word = "";
        for (int i = 0; i < arr.length; i++) {
            word += arr[i].getLetter();
        }
        return word;
    }

    /**
     * Check if word starts at the center for the first move
     * @param xPos
     * @param yPos
     */
    public void isCentered(int[] xPos, int[] yPos){

        for(int i = 0; i < xPos.length; i++){
            if(xPos[i] == 7 && yPos[i] == 7){
                isCentered = true;
            }
        }
    }

    /**
     * Allows a player to play a word
     * @param wordToPlay
     * @param specialTiles
     * @param xPositions
     * @param yPositions
     * @param isVertical
     */
    public void playWord(ScrabbleLetter[] wordToPlay, int[] specialTiles, int[] xPositions, int[] yPositions, boolean isVertical){
        ScrabbleDictionary dict = new ScrabbleDictionary();
        numPasses = 0;
        ScrabbleLetter missingLetter = null;
        ScrabbleLetter[][] myBoard = this.board;

        if(id == 0) {
            //isCentered(xPositions, yPositions);
            //if it's not continuous it's invalid so exit trying

            if( !isCentered ||!isContinuous(xPositions, yPositions) || !dict.isLegal(arrToString(wordToPlay))){
                return;
            }

            //finds missing letter
            if (isVertical == true) {
                for (int i = 0; i < wordToPlay.length; i++) {
                    if (myBoard[xPositions[i]][(yPositions[i]) + 1] == null || myBoard[xPositions[i]][(yPositions[i]) - 1] == null) {
                        return;
                    }
                    if (myBoard[xPositions[i]][yPositions[i] + 1].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i]][yPositions[i] + 1];
                    } else if (myBoard[xPositions[i]][yPositions[i] - 1].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i]][yPositions[i] - 1];
                    }
                }
            } else {
                for (int i = 0; i < wordToPlay.length; i++) {
                    if (myBoard[xPositions[i]][yPositions[i] + 1] == null || myBoard[xPositions[i]][yPositions[i] - 1] == null) {
                        return;
                    }
                    if (myBoard[xPositions[i] + 1][yPositions[i]].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i] + 1][yPositions[i]];
                    } else if (myBoard[xPositions[i] - 1][yPositions[i]].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i] - 1][yPositions[i]];
                    }
                }
            }


        }

        //adds words to score and adds points as well
        score(wordToPlay, myBoard, xPositions, yPositions, missingLetter, specialTiles);
        placeWord(wordToPlay, myBoard, xPositions, yPositions);
        replaceTiles(wordToPlay);

        //adds one to first turn
        this.firstTurn++;
        this.board = myBoard;

    }

    public void placeWord(ScrabbleLetter[] word, ScrabbleLetter[][] board, int[] x, int[] y){
        for(int i = 0; i < x.length; i++){
            int xPos = x[i];
            int yPos = y[i];
            board[xPos][yPos] = word[i];
        }
    }
    /**
     * This calculates the score of the word that was played
     * @param wordToPlay
     * @param myBoard
     * @param xPositions
     * @param yPositions
     * @param missingLetter
     * @param specialTiles
     */
    public void score(ScrabbleLetter[] wordToPlay, ScrabbleLetter[][] myBoard, int[] xPositions,
                      int[] yPositions, ScrabbleLetter missingLetter, int[] specialTiles){

        int type = 0;
        int score = 0;
        for(int i = 0; i < wordToPlay.length; i++){
            //calculates scores for the special tiles
            if(specialTiles[i] == 0) {
                score += wordToPlay[i].getPoints();
            }
            else if(specialTiles[i] == 1 || specialTiles[i] == 2){
                type = 1;
                score += wordToPlay[i].getPoints();
            }
            else if(specialTiles[i] == 3){
                score += 3*wordToPlay[i].getPoints();
            }
            else{
                score += 2*wordToPlay[i].getPoints();
            }
        }
        if(type == 1 || type == 2){
            score *= type;
        }


        //adds missing letter to points
        if(firstTurn != 0) {
            if (id == 0) {
                score += missingLetter.getPoints();
            }
        }

        //if the word is longer than 7 add 50 to the score
        if(wordToPlay.length > 7) {
            if (id == 0) {
                score += 50;
            }
        }

        if (id == 0) {
            this.player1Score += score;
        } else if (id == 1) {
            this.player2Score += score;
        }
    }

    public void scoreOver(){
        ScrabbleLetter[] hand = getPlayer1Hand();
        for(int i = 0; i < hand.length; i++){
            player1Score -= hand[i].getPoints();
        }
    }
    /**
     * This allows you to replace the tiles in the hand once you have
     * played a word
     * @param lettersToExchange
     */
    public void replaceTiles(ScrabbleLetter[] lettersToExchange){
        //replaces the player's deck of tiles with new random letters
        numPasses = 0;
        int count = 0;
        if(id == 0){
            for(int i = 0; i < lettersToExchange.length; i++){
                for(int j = 0; j < player1Hand.length; j++){
                    char tempChar1 = lettersToExchange[i].getLetter();
                    char tempChar2 = player1Hand[j].getLetter();
                    if(tempChar1 == tempChar2){
                        player1Hand[j].setName(' ');
                        //  break;
                    }
                }
            }
            for(int i = 0; i < player1Hand.length; i++) {
                if (player1Hand[i].getLetter() == ' ') {
                    player1Hand[i] = pool[poolCounter];
                    poolCounter++;
                    //possibly put in a negative num placeholder

                    if (count == lettersToExchange.length) {
                        break;
                    }
                }
            }
        }
        else{
            for(int i = 0; i < lettersToExchange.length; i++){
                for(int j = 0; j < player2Hand.length; j++){
                    if(lettersToExchange[i].equals(player2Hand[j])){
                        player2Hand[j].setName(' ');
                        break;
                    }
                }
            }
            for(int i = 0; i < player2Hand.length; i++){
                if(player2Hand[i].equals(' ')){
                    player2Hand[i].setName(pool[i].getLetter());
                    List<ScrabbleLetter> poolArrayList = Arrays.asList(pool);
                    poolArrayList.remove(i);
                    ScrabbleLetter[] newPool = new ScrabbleLetter[poolArrayList.size()];
                    newPool = poolArrayList.toArray(newPool);
                    pool = newPool;
                    count++;
                }
                if(count == lettersToExchange.length){
                    break;
                }
            }
        }
    }

    /**
     * This exchanges the letters in your hand
     * puts the letters that you wanted to exchange
     * back into the pool
     * @param lettersToExchange
     */
    public void exchange(ScrabbleLetter[] lettersToExchange){
        //give new letter tiles to player who wants to exchange
        numPasses = 0;
        int count = 0;
        if(id == 0){

            for(int i = 0; i < lettersToExchange.length; i++){
                for(int j = 0; j < player1Hand.length; j++){
                    char tempChar1 = lettersToExchange[i].getLetter();
                    char tempChar2 = player1Hand[j].getLetter();
                    if(tempChar1 == tempChar2){
                        player1Hand[j].setName(' ');
                    }
                }
            }
            for(int i = 0; i < player1Hand.length; i++) {
                if (player1Hand[i].getLetter() == ' ') {
                    player1Hand[i] = pool[poolCounter];
                    poolCounter++;

                    if (count == lettersToExchange.length) {
                        break;
                    }
                }
            }
        }
        else{
            for(int i = 0; i < lettersToExchange.length; i++){
                for(int j = 0; j < player2Hand.length; j++){
                    if(lettersToExchange[i].equals(player2Hand[j])){
                        player2Hand[j].setName(' ');
                        break;
                    }
                }
            }
            for(int i = 0; i < player2Hand.length; i++){
                if(player2Hand[i].equals(' ')){
                    player2Hand[i].setName(pool[i].getLetter());
                    List<ScrabbleLetter> poolArrayList = Arrays.asList(pool);
                    poolArrayList.remove(i);
                    poolArrayList.add(lettersToExchange[count]);
                    ScrabbleLetter[] newPool = new ScrabbleLetter[poolArrayList.size()];
                    newPool = poolArrayList.toArray(newPool);
                    pool = newPool;
                    count++;
                }
                if(count == lettersToExchange.length){
                    break;
                }
            }
        }
    }

    /**
     * Performs an insertion sort on an int array
     * @param points
     * @return int[]
     */
    public int[] sort(int[] points) {

        for (int i = 1; i < points.length; ++i) {
            int key = points[i];
            int j = i - 1;

            while (j >= 0 && points[j] > key) {
                points[j + 1] = points[j];
                j = j - 1;
            }
            points[j + 1] = key;

        }
        return points;
    }

    /**
     *Checks to see if the word played is continuous
     *
     * @param xPoints
     * @param yPoints
     * @return
     */
    public boolean isContinuous(int[] xPoints, int[] yPoints){
        //checks if word placed builds off of another word
        xPoints = sort(xPoints);
        yPoints = sort(yPoints);

        int xCurr = -1;
        int yCurr = -1;
        boolean xChange = true;
        for(int i = 0; i < xPoints.length; i++) {
            xCurr = xPoints[i];
            yCurr = yPoints[i];
            if (i == 0) { //adjusting xChange
                if (xPoints[0] == xCurr) {
                    xChange = false;
                }
                else if (yCurr == yPoints[0]) {
                    xChange = true;
                }
            }
            else {
                if(xChange){
                    if(xCurr != 1 + xPoints[i - 1]){
                        return false;
                    }
                }
                else{
                    if(yCurr != 1 + yPoints[i - 1]){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks to see if the word is vertical
     */
    public boolean isVertical(int[] xArray, int[] yArray) {
        boolean isVertical = false;

        if (xArray[0] == xArray[1]) {
            isVertical = true;
        } else if (yArray[0] == yArray[1]) {
            isVertical = false;
        }

        return isVertical;
    }

    /**
     * Allows the player to pass their turn
     */
    public void pass(){
        if(numPasses >= 3){
            over = 1;
        }
        if (id == 1) {
            id = 2;
        }

        if (id == 2) {
            id = 1;
        }
        numPasses++;
    }

    /**
     * Allows player to exit the game
     */
    public void exitGame(){
        scoreOver();
        over = 1;
        //if(over == 1){
        //    System.exit(0);
        //}
    }

}
