package com.example.thescrabblegame;

import com.example.thescrabblegame.game.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
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
    private ScrabbleLetter[] pool = new ScrabbleLetter[100];

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

    /**
     * Setter methods
     */
    public void setIdNum(int num){ id = num; }
    public void setPlayer1Score(int score){ player1Score = score; }
    public void setPlayer2Score(int score){ player2Score = score; }
    public void setPlayer3Score(int score){ player3Score = score; }
    public void setPlayer4Score(int score){ player4Score = score; }
    public void setBoard(ScrabbleLetter[][] newBoard){ board = newBoard; }
    public void setOver(int isOver){ over = isOver; }
    public void setNumPasses(int passes){ numPasses = passes; }
    public void setPlayer1Hand(ScrabbleLetter[] hand){ player1Hand = hand; }
    public void setPlayer2Hand(ScrabbleLetter[] hand){ player2Hand = hand; }
    public void setPlayer3Hand(ScrabbleLetter[] hand){ player3Hand = hand; }
    public void setPlayer4Hand(ScrabbleLetter[] hand){ player4Hand = hand; }
    public void setIsCentered(boolean centered) {isCentered = centered;}
    public void setFirstTurn(int turn){firstTurn = turn;}

    /**
     * Getter methods
     */
    public int getIdNum(){ return id; }
    public int getPlayer1Score(){ return player1Score; }
    public int getPlayer2Score(){ return player2Score; }
    public int getPlayer3Score(){ return player3Score; }
    public int getPlayer4Score(){ return player4Score; }
    public boolean getIsCentered() {return isCentered; }
    public ScrabbleLetter[][] getBoard() { return board; }
    public ScrabbleLetter[] getPool(){ return pool; }
    public int getOver(){ return over; }
    public int getNumPasses(){ return numPasses; }
    public ScrabbleLetter[] getPlayer1Hand(){ return player1Hand; }
    public ScrabbleLetter[] getPlayer2Hand(){ return player2Hand; }
    public ScrabbleLetter[] getPlayer3Hand(){ return player3Hand; }
    public ScrabbleLetter[] getPlayer4Hand(){ return player4Hand; }

    /**
     * This is the constructor for the ScrabbleState which (in order):
     *  -sets board to blanks
     *  -sets the players' hand by pulling from the pool
     *  -initializes the tile pool
     *  -sets the number of players
     *  -sets game to not over
     *  -sets number of passes
     *  -sets it to first turn
     *  -sets the poolCounter to 0
     *
     */
    public ScrabbleState(){

        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = new ScrabbleLetter(' ');
            }
        }

        initPool();

        ArrayList<ScrabbleLetter> hand = new ArrayList<>();
        while(hand.size() != 7){
            Random r = new Random();
            int high = pool.length;
            int num = r.nextInt(high);

            //if(pool[num] != null){
                hand.add(pool[num]);
                ArrayList<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));
                poolArrayList.remove(num);
                ScrabbleLetter[] tempArray = new ScrabbleLetter[1];
                pool = poolArrayList.toArray(tempArray);
            //}
        }
        player1Hand = hand.toArray(player1Hand);

        hand.clear();
        while(hand.size() != 7){
            Random r = new Random();
            int high = pool.length;
            int num = r.nextInt(high);
            //if(pool[num] != null){
                hand.add(pool[num]);
                ArrayList<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));
                poolArrayList.remove(num);
                ScrabbleLetter[] tempArray = new ScrabbleLetter[1];
                pool = poolArrayList.toArray(tempArray);
            //}
        }
        player2Hand = hand.toArray(player2Hand);
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

        numPlayers = 2;
        over = 0;
        numPasses = 0;
        firstTurn = 0;
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
     * This method initializes the pool to help a specific number of each tile,
     * according to this website: https://en.wikipedia.org/wiki/Scrabble_letter_distributions
     *
     * Works by creating an arrayList to add ScrabbleLetters to then adding a
     * specified number of each letter to the pool. Once done with that, the
     * array list is converted to an array and the pool instance variable is
     * initialized with that array.
     *
     * NOTE: Since blank tiles were not implemented, instead there is an extra
     *       M and Y tile in the pool.
     *
     * @author: Samone
     */
    public void initPool(){

        //initializing the arrayList
        ArrayList<ScrabbleLetter> letterPool = new ArrayList<>();

        for(int num = 0; num < 12; num++){
            letterPool.add(new ScrabbleLetter('e'));
        }
        for(int num = 0; num < 9; num++){
            letterPool.add(new ScrabbleLetter('a'));
            letterPool.add(new ScrabbleLetter('i'));
        }
        for(int num = 0;  num < 8; num++){
            letterPool.add(new ScrabbleLetter('o'));
        }
        for(int num = 0; num < 6; num++){
            letterPool.add(new ScrabbleLetter('n'));
            letterPool.add(new ScrabbleLetter('r'));
            letterPool.add(new ScrabbleLetter('t'));
        }
        for(int num = 0; num < 4; num++){
            letterPool.add(new ScrabbleLetter('l'));
            letterPool.add(new ScrabbleLetter('s'));
            letterPool.add(new ScrabbleLetter('u'));
            letterPool.add(new ScrabbleLetter('d'));
        }
        for(int num = 0; num < 3; num++){
            letterPool.add(new ScrabbleLetter('g'));
            letterPool.add(new ScrabbleLetter('m'));
            letterPool.add(new ScrabbleLetter('y'));
        }
        for(int num = 0; num < 2; num++){
            letterPool.add(new ScrabbleLetter('c'));
            letterPool.add(new ScrabbleLetter('p'));
            letterPool.add(new ScrabbleLetter('f'));
            letterPool.add(new ScrabbleLetter('h'));
            letterPool.add(new ScrabbleLetter('v'));
            letterPool.add(new ScrabbleLetter('w'));
            letterPool.add(new ScrabbleLetter('b'));
        }
        letterPool.add(new ScrabbleLetter('k'));
        letterPool.add(new ScrabbleLetter('x'));
        letterPool.add(new ScrabbleLetter('j'));
        letterPool.add(new ScrabbleLetter('q'));
        letterPool.add(new ScrabbleLetter('z'));

        //setting the pool variable as the converted array
        this.pool = letterPool.toArray(this.pool);
    }

    /** External Citation:
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

        //makes sure first word is centered
        if(this.firstTurn == 0){
            isCentered(xPositions, yPositions);
        }

        if(id == 0) {

            //if it's not continuous it's invalid so exit trying
            if( !isCentered ||!isContinuous(xPositions, yPositions)){
                pass();
                return;
            }
            if(firstTurn == 0){
                if(!dict.isLegal(arrToString(wordToPlay))){
                    pass();
                    return;
                }
            }
            int tmpX = -1;
            int tmpY = -1;
            //finds missing letter
            if (isVertical == true) {
                for (int i = 0; i < wordToPlay.length; i++) {
                    //edge case
                    if (myBoard[xPositions[i]][(yPositions[i]) + 1] == null || myBoard[xPositions[i]][(yPositions[i]) - 1] == null) {
                        return;
                    }
                    if (myBoard[xPositions[i]][yPositions[i] + 1].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i]][yPositions[i] + 1];
                        tmpY = yPositions[i] + 1;
                    } else if (myBoard[xPositions[i]][yPositions[i] - 1].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i]][yPositions[i] - 1];
                        tmpY = yPositions[i] - 1;
                    }
                }
            } else {
                for (int i = 0; i < wordToPlay.length; i++) {
                    if (myBoard[xPositions[i]][yPositions[i] + 1] == null || myBoard[xPositions[i]][yPositions[i] - 1] == null) {
                        return;
                    }
                    if (myBoard[xPositions[i] + 1][yPositions[i]].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i] + 1][yPositions[i]];
                        tmpX = yPositions[i] + 1;
                    } else if (myBoard[xPositions[i] - 1][yPositions[i]].getLetter() != ' ') {
                        missingLetter = myBoard[xPositions[i] - 1][yPositions[i]];
                        tmpX = yPositions[i] - 1;
                    }
                }
            }

            //gets full word to see if is in the dictionary
            if (firstTurn != 0) {
                String myWord = new String();
                ArrayList<Character> tmpCharArray = new ArrayList<>();
                if (isVertical) {
                    for (int i = 0; i < yPositions.length; i++) {
                        //adds chars to array list
                        char tmpChar = wordToPlay[i].getLetter();
                        tmpCharArray.add(tmpChar);
                        //inserts missing letter in the index of the array list
                        if (yPositions[i] - 1 == tmpY) {
                            char myChar = missingLetter.getLetter();
                            tmpCharArray.add(myChar);
                        } else if (yPositions[i] + 1 == tmpY) {
                            char myChar = missingLetter.getLetter();
                            tmpCharArray.add(myChar);
                        }
                    }
                    for (char c : tmpCharArray) {
                        myWord = myWord + c;
                    }
                }
                else{
                    for (int i = 0; i < xPositions.length; i++) {
                        //adds chars to array list
                        char tmpChar = wordToPlay[i].getLetter();
                        tmpCharArray.add(tmpChar);
                        //inserts missing letter in the index of the array list
                        if (xPositions[i] - 1 == tmpX) {
                            char myChar = missingLetter.getLetter();
                            tmpCharArray.add(myChar);
                        } else if (xPositions[i] + 1 == tmpX) {
                            char myChar = missingLetter.getLetter();
                            tmpCharArray.add(myChar);
                        }
                    }
                    for (char c : tmpCharArray) {
                        myWord = myWord + c;
                    }
                }
                if (!dict.isLegal(myWord)) {
                    pass();
                    return;
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

    /**
     * PlaceWord is a helper method that places the word on the board
     *
     * @param word
     * @param board
     * @param x
     * @param y
     */
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
        if(firstTurn != 0 && missingLetter != null) {
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
    } //score

    /**
     * Subtracts the number of tiles in the players' hand once the game is
     * finished per the official Scrabble Rules.
     */
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
        Random num = new Random();
        int randoNum;
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
                        break;
                    }
                }
            }
            for(int i = 0; i < player1Hand.length; i++) {
                if (player1Hand[i].getLetter() == ' ') {
                    randoNum = num.nextInt(pool.length);

                    player1Hand[i] = pool[randoNum];
                    ArrayList<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));
                    poolArrayList.remove(randoNum);
                    ScrabbleLetter[] tempArray = new ScrabbleLetter[1];
                    pool = poolArrayList.toArray(tempArray);
                    count++;

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
                    randoNum = num.nextInt(pool.length);

                    player1Hand[i] = pool[randoNum];
                    ArrayList<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));
                    poolArrayList.remove(randoNum);
                    ScrabbleLetter[] tempArray = new ScrabbleLetter[1];
                    pool = poolArrayList.toArray(tempArray);
                    count++;

                    if (count == lettersToExchange.length) {
                        break;
                    }
                }
            }
        }
    } //replaceTiles

    /**
     * This exchanges the letters in your hand
     * puts the letters that you wanted to exchange
     * back into the pool
     * @param lettersToExchange
     */
    public void exchange(ScrabbleLetter[] lettersToExchange){
        Random num = new Random();
        int randoNum;
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
                        break;
                    }
                }
            }
            for(int i = 0; i < player1Hand.length; i++) {
                if (player1Hand[i].getLetter() == ' ') {
                    randoNum = num.nextInt(pool.length);

                    player1Hand[i] = pool[randoNum];
                    List<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));
                    poolArrayList.remove(randoNum);
                    poolArrayList.add(lettersToExchange[count]);
                    ScrabbleLetter[] tempArray = new ScrabbleLetter[1];
                    pool = poolArrayList.toArray(tempArray);
                    count++;

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
                    randoNum = num.nextInt(pool.length);

                    player2Hand[i] = pool[randoNum];
                    List<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));
                    poolArrayList.remove(randoNum);
                    poolArrayList.add(lettersToExchange[count]);
                    ScrabbleLetter[] tempArray = new ScrabbleLetter[1];
                    pool = poolArrayList.toArray(tempArray);
                    count++;

                    if (count == lettersToExchange.length) {
                        break;
                    }
                }
            }
        }
    } //exchange

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
                if (xPoints[1] == xCurr) {
                    xChange = false;
                }
                else if (yCurr == yPoints[1]) {
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
    } //isContinuous

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
    }

}
