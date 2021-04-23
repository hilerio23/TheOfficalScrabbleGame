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


}
