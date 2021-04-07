package com.example.thescrabblegame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

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
}
