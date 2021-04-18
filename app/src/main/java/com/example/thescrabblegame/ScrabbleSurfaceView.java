 package com.example.thescrabblegame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thescrabblegame.game.GameFramework.Game;
import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.actionMessage.GameAction;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScrabbleSurfaceView extends SurfaceView implements
         TextView.OnEditorActionListener, View.OnTouchListener{

    public ScrabbleState state;
    private ArrayList<ImageView> letters = new ArrayList<>();
    private ArrayList<Double> xcoords = new ArrayList<>();
    private ArrayList<Double> ycoords = new ArrayList<>();

    private double[][] xy;
    private double xCoord;
    private double yCoord;
    private ScrabbleLetter[] letter;
    private ScrabbleMainActivity myActivity;



    public ScrabbleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setOnTouchListener(this);
        state = new ScrabbleState();
    }
/*
    public void setMyActivity(ScrabbleMainActivity activity){
        myActivity = activity;
    }*/


    public double[][] toDouble(ArrayList<Double> x, ArrayList<Double> y){
        this.xy = new double[x.size()][2];

        this.xCoord = x.get(0);
        this.yCoord = y.get(0);

        for(int i = 0; i < x.size(); i++){
            this.xy[i][0] = x.get(i);
            this.xy[i][1] = y.get(i);
        }

        return xy;
    }

    public double[][] getXY(){
        return xy;
    }
    public double getxCoord(){
        return xCoord;
    }
    public double getyCoord(){
        return yCoord;
    }


   /* public void drawHand(ScrabbleState state){
        ScrabbleLetter[] hand = state.getPlayer1Hand();
        ImageView img;


        for(int i = 0; i < 7; i++){
            img = getHandImageView(i);
            if(img == null){
                int x = 2;
            }
            img.setImageDrawable(getDrawableLetter(hand[i].getLetter()));
        }
        invalidate();
    }

    */


/*
    public void makeMove(char[] word, int[][] points){

        String name;
        int x;
        int y;
        ScrabbleLetter board[][] = state.getBoard();

        for(int i = 0; i < word.length; i++){

            int LL = getLinearLayout(points[i][0], points[i][1]);
            setBackground(getDrawableLetter(word[i]));

        }

        for(int col = 0; col < board.length; col++){
            for(int row = 0; row < board[row].length; row++){

            }
        }
        invalidate();
    }
*/
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view instanceof ImageView) {
            xcoords.add((double) view.getX());
            ycoords.add((double) view.getY());
            return true;
        }
        else{
            return false;
        }
    }
/*
    public ImageView getHandImageView(int num){
        switch(num){
            case 0:
                return (ImageView)myActivity.findViewById(R.id.aButton);
            case 1:
                return (ImageView) myActivity.findViewById(R.id.bButton);
            case 2:
                return (ImageView)myActivity.findViewById(R.id.cButton);
            case 3:
                return (ImageView)myActivity.findViewById(R.id.dButton);
            case 4:
                return (ImageView)myActivity.findViewById(R.id.gButton);
            case 5:
                return (ImageView)myActivity.findViewById(R.id.eButton);
            case 6:
                return (ImageView)myActivity.findViewById(R.id.fButton);
            default:
                return null;
        }
    }*/

/*
    //this is a massive case switch to get the letter
    public Drawable getDrawableLetter(char letter){

        switch(letter){
            case 'a':
                return getResources().getDrawable(R.drawable.afinal);
            case 'b':
                return getResources().getDrawable(R.drawable.bfinal);
            case 'c':
                return getResources().getDrawable(R.drawable.cfinal);
            case 'd':
                return getResources().getDrawable(R.drawable.dfinal);
            case 'e':
                return getResources().getDrawable(R.drawable.efinal);
            case 'f':
                return getResources().getDrawable(R.drawable.ffinal);
            case 'g':
                return getResources().getDrawable(R.drawable.gfinal);
            case 'h':
                return getResources().getDrawable(R.drawable.hfinal);
            case 'i':
                return getResources().getDrawable(R.drawable.ifinal);
            case 'j':
                return getResources().getDrawable(R.drawable.jfinal);
            case 'k':
                return getResources().getDrawable(R.drawable.kfinal);
            case 'l':
                return getResources().getDrawable(R.drawable.lfinal);
            case 'm':
                return getResources().getDrawable(R.drawable.mfinal);
            case 'n':
                return getResources().getDrawable(R.drawable.nfinal);
            case 'o':
                return getResources().getDrawable(R.drawable.ofinal);
            case 'p':
                return getResources().getDrawable(R.drawable.pfinal);
            case 'q':
                return getResources().getDrawable(R.drawable.qfinal);
            case 'r':
                return getResources().getDrawable(R.drawable.rfinal);
            case 's':
                return getResources().getDrawable(R.drawable.sfinal);
            case 't':
                return getResources().getDrawable(R.drawable.tfinal);
            case 'u':
                return getResources().getDrawable(R.drawable.ufinal);
            case 'v':
                return getResources().getDrawable(R.drawable.vfinal);
            case 'w':
                return getResources().getDrawable(R.drawable.wfinal);
            case 'x':
                return getResources().getDrawable(R.drawable.xfinal);
            case 'y':
                return getResources().getDrawable(R.drawable.yfinal);
            case 'z':
                return getResources().getDrawable(R.drawable.zfinal);
            default:
                return getResources().getDrawable(R.drawable.backgroundsquare);
        }

    }*/

        //this gets the linear layout cell that we want to change
    private int getLinearLayout(int x, int y){
        int id = -1;
        return id;
    }

}
