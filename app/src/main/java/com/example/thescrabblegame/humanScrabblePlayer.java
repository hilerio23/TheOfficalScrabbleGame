package com.example.thescrabblegame;

import android.graphics.Color;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

public class humanScrabblePlayer extends GameHumanPlayer {

    private TextView score = null;
    private ScrabbleSurfaceView surfaceView;

    private GameMainActivity mActivity;
    private Button exchange = null;
    private Button passButton = null;
    private Button playWordButton = null;
    private Button exitButton = null;
    public ScrabbleState scrabbleCopy;




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
        if (info instanceof ScrabbleState) {
            score.setText(Integer.toString(((ScrabbleState) info).getPlayer1Score()));
            this.scrabbleCopy = new ScrabbleState((ScrabbleState) info);
            if (playerNum == ((ScrabbleState) info).getIdNum()) {
                score.setTextColor(Color.BLUE);
            } else {

                if (allPlayerNames.length == 2) {
                    score.setTextColor(Color.GREEN);
                }
            }
        }
        else{
            flash(Color.RED, 5);
            return;
        }
    }


    @Override
    public void setAsGui(GameMainActivity activity) {
        mActivity = activity;

        activity.setContentView(R.layout.activity_main);
        this.surfaceView = (ScrabbleSurfaceView)activity.findViewById(R.id.scrabbleSurfaceView);
        this.score = (TextView)activity.findViewById(R.id.scoreNumber);
        this.exchange = (Button)activity.findViewById(R.id.exchange);
        this.passButton = (Button)activity.findViewById(R.id.pass);
        this.playWordButton = (Button)activity.findViewById(R.id.playword);
        this.exitButton = (Button)activity.findViewById(R.id.exitGame);

    }
    public void onClick(View button) {

        boolean isVertical = scrabbleCopy.isVertical(surfaceView.getXY());

        if (button.getId() == exchange.getId()) {
            Exchange exchange = new Exchange(this, surfaceView.getScrabbleLetter());
            game.sendAction(exchange);
        }
        else if(button.getId() == passButton.getId()){
            Pass pass = new Pass(this);
            game.sendAction(pass);
        }
        else if(button.getId() == playWordButton.getId()){
            PlayWord playWord = new PlayWord(this, surfaceView.getScrabbleLetter(), surfaceView.getxCoord(), surfaceView.getyCoord(), isVertical);
            game.sendAction(playWord);
        }
        else if(button.getId() == exitButton.getId()){
            ExitGame exitGame = new ExitGame(this);
            game.sendAction(exitGame);
        }
        //surfaceView.onClick(view);
    }
}
