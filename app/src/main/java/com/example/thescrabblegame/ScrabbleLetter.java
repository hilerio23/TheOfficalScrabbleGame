/**
 *
 * @author Tamsen Dean
 * @author Anabel Hilerio
 * @author Alec Uyematsu
 * @author Samone Watkins
 */
package com.example.thescrabblegame;

/**
 * class ScrabbleLetter gives the letters values
 *
 * @author Tamsen Dean
 * @version April 2021
 */
public class ScrabbleLetter {
    private char letter;
    private int points;
    private int xCoord;
    private int yCoord;

    /**
     * constructor
     *
     * @param myLetter
     */
    public ScrabbleLetter(char myLetter) {
        this.letter = myLetter;
    }

    /**
     *Setter Methods
     */
    public void setName(char letter) { this.letter = letter; }
    public void setPoints(int points) { this.points = points; }
    public void setxCoord(int newXCoord){
        xCoord = newXCoord;
    }
    public void setyCoord(int newYCoord){
        yCoord = newYCoord;
    }

    /**
     *Getter methods
     */
    public char getLetter() { return letter; }
    public int getxCoord(){
        return xCoord;
    }
    public int getyCoord(){
        return yCoord;
    }

    /**
     * assigns point values to the letter tiles
     *
     * @return int
     */
    public int getPoints(){
        if(this.letter == 'a' || this.letter == 'e' || this.letter == 'i' || this.letter == 'l' ||
                this.letter == 'n' || this.letter == 'o' || this.letter == 'r' || this.letter == 's'
                || this.letter == 't' || this.letter == 'u'){
            return 1;
        }
        else if(this.letter == 'd' || this.letter == 'g'){
            return 2;
        }
        else if(this.letter == 'b' || this.letter == 'c' || this.letter == 'm' || this.letter == 'p'){
            return 3;
        }
        else if(this.letter == 'f' || this.letter == 'h' || this.letter == 'v' || this.letter == 'v'
                || this.letter == 'w' || this.letter == 'y'){
            return 4;
        }
        else if(this.letter == 'k'){
            return 5;
        }
        else if(this.letter == 'j' || this.letter == 'x'){
            return 8;
        }
        else if (this.letter == 'q' ||this.letter == 'z'){
            return 10;
        }
        else{
            return 0;
        }
    }
} // ScrabbleLetter

