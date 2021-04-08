package com.example.thescrabblegame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

public class ScrabbleSurfaceView extends SurfaceView implements View.OnClickListener{

    private ScrabbleState state;

    public ScrabbleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        state = new ScrabbleState();
    }

    @Override
    public void onClick(View view) {
        int buttonClicked = view.getId();

        //determine what button was pushed and then call that method in the state
        if(buttonClicked == R.id.pauseGame){

        }
        else if(buttonClicked == R.id.challenge){

        }
        else if(buttonClicked == R.id.exchange){

        }
        else if(buttonClicked == R.id.exitGame){

        }
        else if(buttonClicked == R.id.pass){

        }
        else{

        }
    }

    public void drawBoard(ScrabbleState state){

        ImageView img = null;

        img.setImageDrawable(getResources().R.drawable.afinal);
    }

    public void makeMove(char[] word, int[][] points){

        for(int i = 0; i < word.length; i++){
            int LL = getLinearLayout(points[i][0], points[i][1]);
            setBackground(getDrawableLetter(word[i]));

        }
    }

    //this is a massive case switch to get the letter
    public Drawable getDrawableLetter(char letter){
        return getResources().R.drawable.kfinal;
    }

    //this gets the linear layout cell that we want to change
    private int getLinearLayout(int x, int y){
        int id = -1;


        return id;
    }
}
