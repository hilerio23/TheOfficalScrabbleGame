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

    //static variables for
    final static int AI = 1;
    final static int HUMAN = 0;
    final static int POOL_MAX = 100;
    final static int BOARD_MAX = 15;
    final static int HAND_MAX = 7;
    final static int PLAYING = 0;
    final static int PAUSE = 1;

    private ScrabbleLetter[][] board = new ScrabbleLetter[BOARD_MAX][BOARD_MAX]; //15 x 15 board
    private int playerToMove; //an int to tell whos move it is 0 for human 1 for AI
    //array of ScrabbleLetters for  each hand
    private ScrabbleLetter[] player1Hand = new ScrabbleLetter[HAND_MAX];
    private ScrabbleLetter[] player2Hand = new ScrabbleLetter[HAND_MAX];
    private ScrabbleLetter[] player3Hand = new ScrabbleLetter[HAND_MAX];
    private ScrabbleLetter[] player4Hand = new ScrabbleLetter[HAND_MAX];
    //array of ScrabbleLetters for Pool (100) total letters
    private ScrabbleLetter[] pool = new ScrabbleLetter[POOL_MAX];
    private int gamePause; //game pause: 1 for pause 0 for playing
    private int player1Score;
    private int player2Score;
    private int player3Score;
    private int player4Score;
    private int id; //id of players
    private int numPlayers; //num of players
    private int over; //tracks if game is over
    private int numPasses; //count passes
    private int firstTurn; //0 for first turn
    private boolean isCentered; //variable for checking if the first word played is centered
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

        //fills the board with 'blank' scrabble letters
        for(int i = 0; i < BOARD_MAX; i++) {
            for (int j = 0; j < BOARD_MAX; j++) {
                board[i][j] = new ScrabbleLetter(' ');
            }
        }

        //initializes the pool
        initPool();

        //initializes the player ones' hand by taking from the pool
        ArrayList<ScrabbleLetter> hand = new ArrayList<>();
        while(hand.size() != HAND_MAX){
            Random r = new Random();
            int num = r.nextInt(POOL_MAX);
            if(pool[num] != null){
                hand.add(pool[num]);
                pool[num] = null;
            }
        }
        player1Hand = hand.toArray(player1Hand);

        hand.clear();
        while(hand.size() != HAND_MAX){
            Random r = new Random();
            int num = r.nextInt(POOL_MAX);
            if(pool[num] != null){
                hand.add(pool[num]);
                pool[num] = null;
            }
        }
        player2Hand = hand.toArray(player2Hand);
        playerToMove = HUMAN;
        gamePause = PLAYING;

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

        //initializing each of the ScrabbleLetters
        ScrabbleLetter a = new ScrabbleLetter('a');
        ScrabbleLetter b = new ScrabbleLetter('b');
        ScrabbleLetter c = new ScrabbleLetter('c');
        ScrabbleLetter d = new ScrabbleLetter('d');
        ScrabbleLetter e = new ScrabbleLetter('e');
        ScrabbleLetter f = new ScrabbleLetter('f');
        ScrabbleLetter g = new ScrabbleLetter('g');
        ScrabbleLetter h = new ScrabbleLetter('h');
        ScrabbleLetter i = new ScrabbleLetter('i');
        ScrabbleLetter j = new ScrabbleLetter('j');
        ScrabbleLetter k = new ScrabbleLetter('k');
        ScrabbleLetter l = new ScrabbleLetter('l');
        ScrabbleLetter m = new ScrabbleLetter('m');
        ScrabbleLetter n = new ScrabbleLetter('n');
        ScrabbleLetter o = new ScrabbleLetter('o');
        ScrabbleLetter p = new ScrabbleLetter('p');
        ScrabbleLetter q = new ScrabbleLetter('q');
        ScrabbleLetter r = new ScrabbleLetter('r');
        ScrabbleLetter s = new ScrabbleLetter('s');
        ScrabbleLetter t = new ScrabbleLetter('t');
        ScrabbleLetter u = new ScrabbleLetter('u');
        ScrabbleLetter v = new ScrabbleLetter('v');
        ScrabbleLetter w = new ScrabbleLetter('w');
        ScrabbleLetter x = new ScrabbleLetter('x');
        ScrabbleLetter y = new ScrabbleLetter('y');
        ScrabbleLetter z = new ScrabbleLetter('z');

        //adding each letter to the pool according to Scrabble rules
        for(int num = 0; num < 12; num++){
            letterPool.add(e);
        }
        for(int num = 0; num < 9; num++){
            letterPool.add(a);
            letterPool.add(i);
        }
        for(int num = 0;  num < 8; num++){
            letterPool.add(o);
        }
        for(int num = 0; num < 6; num++){
            letterPool.add(n);
            letterPool.add(r);
            letterPool.add(t);
        }
        for(int num = 0; num < 4; num++){
            letterPool.add(l);
            letterPool.add(s);
            letterPool.add(u);
            letterPool.add(d);
        }
        for(int num = 0; num < 3; num++){
            letterPool.add(g);
            letterPool.add(m);
            letterPool.add(y);
        }
        for(int num = 0; num < 2; num++){
            letterPool.add(c);
            letterPool.add(p);
            letterPool.add(f);
            letterPool.add(h);
            letterPool.add(v);
            letterPool.add(w);
            letterPool.add(b);
        }
        letterPool.add(k);
        letterPool.add(x);
        letterPool.add(j);
        letterPool.add(q);
        letterPool.add(z);

        //setting the pool variable as the converted array
        this.pool = letterPool.toArray(this.pool);

    } // initPool

    /**
     * Convert an array of Scrabble Letters to a string by using the
     * getLetter() method for each element of the array.
     *
     * External Citation:
     * Date: 18 April 2021
     * Problem: Needed to convert array list to string
     * Resource: https://stackoverflow.com/questions/599161/best-way-to-convert-an-arraylist-to-a-string
     * Solution: I used the example code from this post.
     *
     * @author Anabel
     * @param arr a ScrabbleLetter array that needs to be converted to a string
     * @retrun word a string comprised of the array's character
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
     *
     * @author Samone
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
            if( !isCentered ||!isContinuous(xPositions, yPositions) || !dict.isLegal(arrToString(wordToPlay))){
                pass();
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

    /**
     * PlaceWord is a helper method that places the word on the board
     *
     * @author Samone
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
     *
     * @author Anabel
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
     *
     * @author Alec
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
                    ArrayList<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));  // Arrays.asList(pool);
                    poolArrayList.remove(randoNum);
                    pool = poolArrayList.toArray(pool);

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
                    pool = poolArrayList.toArray(pool);

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
     * @author Anabel
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
                    List<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));  // Arrays.asList(pool);
                    poolArrayList.remove(randoNum);
                    poolArrayList.add(lettersToExchange[count]);
                    pool = poolArrayList.toArray(pool);
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
                    List<ScrabbleLetter> poolArrayList = new ArrayList<ScrabbleLetter>(Arrays.asList(pool));  // Arrays.asList(pool);
                    poolArrayList.remove(randoNum);
                    poolArrayList.add(lettersToExchange[count]);
                    pool = poolArrayList.toArray(pool);
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
     *
     * EXTERNAL CITATION:
     * Date: April 26th, 2021
     * Problem: Needed help on remembering how to perform and insertion sorrt
     * Resource: https://www.geeksforgeeks.org/insertion-sort/
     * Solution: The code below is adapted from this resource
     *
     * @author Samone
     * @param points an array of various points that needs to be sorted
     * @return int[] a sorted array
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
     * @author Samone
     * @param xPoints
     * @param yPoints
     * @return true if isContinuous and false if not
     */
    public boolean isContinuous(int[] xPoints, int[] yPoints){

        //sorts the x and y arrays from lowest to highest
        xPoints = sort(xPoints);
        yPoints = sort(yPoints);

        //initializes variables for use later
        int xCurr = -1;
        int yCurr = -1;
        boolean xChange = true;

        for(int i = 0; i < xPoints.length; i++) {

            xCurr = xPoints[i];
            yCurr = yPoints[i];

            //if the for loop is at the beginning we must
            //see if the x or the y variable is changing
            if (i == 0) {
                if (xPoints[1] == xCurr) {
                    xChange = false;
                }
                else if (yCurr == yPoints[1]) {
                    xChange = true;
                }
            }

            //otherwise, once we know which variable is changing,
            //make sure that the current is one set aove the previous
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
     * Checks to see if the word is vertical, but assumes continuity.
     * Because of this, it only checks the first and second indexes
     *
     * @author Samone
     * @param xArray an array of the x values
     * @param yArray an array of the y values
     * @return isVertical a boolean that says whether the word is vertical
     */
    public boolean isVertical(int[] xArray, int[] yArray) {
        boolean isVertical = false;

        if (xArray[0] == xArray[1]) {
            isVertical = true;
        }
        else if (yArray[0] == yArray[1]) {
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
