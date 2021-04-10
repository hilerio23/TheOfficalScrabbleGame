package com.example.thescrabblegame;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

public class humanScrabblePlayer extends GameHumanPlayer {

    private TextView score = null;
    private ScrabbleSurfaceView surfaceView;

    private GameMainActivity mActivity;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public humanScrabblePlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(info instanceof ScrabbleState){
            score.setText(Integer.toString(((ScrabbleState) info).getPlayer1Score()));
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        mActivity = activity;

        activity.setContentView(R.layout.activity_main);

        this.score = (TextView)activity.findViewById(R.id.scoreNumber);

    }
}
