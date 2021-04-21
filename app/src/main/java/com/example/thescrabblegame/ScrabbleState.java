/**
 * @author Tamsen Dean
 * @author Anabel Hilerio
 * @author Alec Uyematsu
 * @author Samone Watkins
 */
package com.example.thescrabblegame;

import android.util.Log;
import android.widget.ImageView;

import com.example.thescrabblegame.game.GameFramework.infoMessage.GameState;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ScrabbleState  extends GameState {

    private ScrabbleSurfaceView mSurfaceView;

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

    //track how empty the percent is
    double percentOfPool = 0.0;

    //id of players and num of players
    private int id;
    private int numPlayers;

    //tracks if game is over
    private int over;

    //count passes
    private int numPasses;

    private int firstTurn; //0 for first turn

    //extra variable for tracking if a move is possible
    boolean isPossible;
    private ScrabbleMainActivity myActivity;

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
        numPlayers = 2;
        over = 0;
        numPasses = 0;
        firstTurn = 0;
        poolCounter = 0;

    }
    /*public ScrabbleState(ScrabbleSurfaceView scrabbleSurfaceView){
        mSurfaceView = scrabbleSurfaceView;
        mSurfaceView.drawHand(this);
    }*/



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

    public void playWord(ScrabbleLetter[] wordToPlay, int[] specialTiles, int[] xPositions, int[] yPositions, boolean isVertical){
        ScrabbleDictionary dict = new ScrabbleDictionary();
        numPasses = 0;
        ScrabbleLetter missingLetter = null;
        ScrabbleLetter[][] myBoard = this.board;


        //finds missing letter
        if(isVertical == true){
            for(int i = 0; i < wordToPlay.length; i ++){
                if(myBoard[xPositions[i]][(yPositions[i])+1] == null || myBoard[xPositions[i]][(yPositions[i])-1] == null){
                    return;
                }
                if(myBoard[xPositions[i]][yPositions[i]+1].getLetter() != ' '){
                    missingLetter = myBoard[xPositions[i]][yPositions[i]+1];
                }
                else if(myBoard[xPositions[i]][yPositions[i]-1].getLetter() != ' '){
                    missingLetter = myBoard[xPositions[i]][yPositions[i]-1];
                }
            }
        }
        else{
            for(int i = 0; i < wordToPlay.length; i ++){
                if(myBoard[xPositions[i]][yPositions[i]+1] == null || myBoard[xPositions[i]][yPositions[i]-1] == null){
                    return;
                }
                if(myBoard[xPositions[i]+1][yPositions[i]].getLetter() != ' '){
                    missingLetter = myBoard[xPositions[i]+1][yPositions[i]];
                }
                else if(myBoard[xPositions[i]-1][yPositions[i]].getLetter() != ' '){
                    missingLetter = myBoard[xPositions[i]-1][yPositions[i]];
                }
            }
        }

        //adds words to score and adds points as well
        score(wordToPlay, myBoard, xPositions, yPositions, missingLetter, specialTiles);

        //adds one to first turn
        this.firstTurn++;
        this.board = myBoard;


        //this probably needs bounds checking
        //change to a boolean return value
       /* if(over == 0) {
            if(dict.isLegal(word)) {
                if (isVertical && xCoord + wordToPlay.length < 15) {
                    //if vertical keep xCoord the same and get row - 1 to get the letter
                    for (int row = (int)xCoord; row < xCoord + wordToPlay.length; row++) {
                        board[row][(int)yCoord] = wordToPlay[row - (int)xCoord];
                        if (id == 1) {
                            player1Score += wordToPlay[row - (int)xCoord].getPoints();
                        } else {
                            player2Score += wordToPlay[row - (int)xCoord].getPoints();
                        }
                    }
                }
                else if(!isVertical && yCoord + wordToPlay.length < 15) {
                    for (int col = (int)yCoord; col < yCoord + wordToPlay.length; col++) {
                        board[(int)xCoord][col] = wordToPlay[col - (int)yCoord];
                        if (id == 1) {
                            player1Score += wordToPlay[col - (int)yCoord].getPoints();
                        } else {
                            player2Score += wordToPlay[col - (int)yCoord].getPoints();
                        }
                    }
                }
                else{
                    pass();
                    return;
                }
                mSurfaceView.drawBoard(this);
                mSurfaceView.drawHand(this);
            }
            else{
                pass();
                return;
            }
        }
        if(pool == null){
            over = 1;
        }*/
        //rewritten code
        /*while(over == 0){
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; i++){

                }
            }
            for(int i = 0; i < wordToPlay.length; i++){
                //word += wordToPlay[i].getLetter();
                if(1==1) {//if is Legal
                    int x = wordToPlay[i].getxCoord();
                    int y = wordToPlay[i].getyCoord();
                    board[x][y] = wordToPlay[i];
                    if (id == 1) {
                        player1Score += wordToPlay[i].getPoints();
                    } else {
                        player2Score += wordToPlay[i].getPoints();
                    }
                }
                else{
                    pass();
                    break;
                }
            }
            //dict.isLegal(word);
            if(pool == null){
                over = 1;
            }
        }*/
    }

    public void score(ScrabbleLetter[] wordToPlay, ScrabbleLetter[][] myBoard, int[] xPositions, int[] yPositions, ScrabbleLetter missingLetter, int[] specialTiles){
        int type = 0;
        int score = 0;
        for(int i = 0; i < wordToPlay.length; i++){
            myBoard[xPositions[i]][yPositions[i]] = wordToPlay[i];
            if(specialTiles[i] == 0) {
                score += wordToPlay[i].getPoints();
            }
            else if(specialTiles[i] == 1){
                type = 3;
                score += wordToPlay[i].getPoints();
            }
            else if(specialTiles[i] == 2){
                type = 2;
                score += wordToPlay[i].getPoints();
            }
            else if(specialTiles[i] == 3){
                score += 3*wordToPlay[i].getPoints();
            }
            else{
                score += 2*wordToPlay[i].getPoints();
            }
        }
        if(type == 3){
            score *= type;
        }
        else if(type == 2){
            score *= type;
        }

        if (id == 0) {
            this.player1Score += score;
        } else if (id == 1) {
            this.player2Score += score;
        }
        //adds missing lettter to points
        if(firstTurn != 0) {
            if (id == 0) {
                this.player1Score += missingLetter.getPoints();
            } else if (id == 1) {
                this.player2Score += missingLetter.getPoints();
            }
        }
    }

    public void exchange(ScrabbleLetter[] lettersToExchange){
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
     *
     * @param xPoints
     * @param yPoints
     * @return
     */
    public boolean isContinuous(int[] xPoints, int[] yPoints){
        int xPrev = -1;
        int xCurr = -1;
        int yPrev = -1;
        int yCurr = -1;
        boolean xChange = true;
        for(int i = 0; i < xPoints.length; i++){
            //setting current and previous values
            if(i == 0){
                xCurr = xPoints[i];
                yCurr = yPoints[i];
                yPrev = -1;
                xPrev = -1;
            }
            else if (i == 1){ //adjusting xChange
                if (xPrev == xCurr){
                    xChange = false;
                }
                else if (yCurr == yPrev){
                    xChange = true;
                }
                xPrev = xCurr;
                yPrev = yCurr;
                yCurr = yPoints[i];
                xCurr = xPoints[i];
            }
            else{
                xPrev = xCurr;
                yPrev = yCurr;
                yCurr = yPoints[i];
                xCurr = xPoints[i];
            }

            //determining if the word is continuous
            if (xChange && yCurr != yPrev){
                return false;
            }
            else if ( !xChange && xCurr != xPrev){
                return false;
            }
        }
        return true;
    }

    public boolean isVertical(int[] xArray, int[] yArray) {
        boolean isVertical = false;

        if (xArray[0] == xArray[1]) {
            isVertical = false;
        } else if (yArray[0] == yArray[1]) {
            isVertical = true;
        }

        return isVertical;
    }

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

    public void exitGame(){
        over = 1;
        if(over == 1){
            System.exit(0);
        }
    }

}
