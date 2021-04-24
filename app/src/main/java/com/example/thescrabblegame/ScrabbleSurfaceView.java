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

 /**
  * class ScrabbleSurfaceView controls the Surface View
  *
  * @author Samone Watkins
  * @version April 2021
  */
public class ScrabbleSurfaceView extends SurfaceView implements
         TextView.OnEditorActionListener, View.OnTouchListener{

    public ScrabbleState state;
    private ArrayList<Double> xcoords = new ArrayList<>();
    private ArrayList<Double> ycoords = new ArrayList<>();

     /**
      * The class's constructor
      *
      * @param context
      * @param attrs
      */
    public ScrabbleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setOnTouchListener(this);
        state = new ScrabbleState();
    }

     /**
      * sets the onEditorAction to false
      *
      * @param textView
      * @param i
      * @param keyEvent
      * @return
      */
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

     /**
      * It gets the coordinates of where the player touches the surface view
      *
      * @param view
      * @param motionEvent
      * @return boolean
      */
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


}
