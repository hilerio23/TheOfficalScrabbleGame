package com.example.thescrabblegame;

import android.graphics.Color;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

public class humanScrabblePlayer extends GameHumanPlayer implements View.OnClickListener {

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
    public humanScrabblePlayer(String name, ScrabbleSurfaceView surfaceView) {
        super(name);
        this.surfaceView = surfaceView;
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

        Button exchange = (Button)activity.findViewById(R.id.exchange);
        Button pass = (Button)activity.findViewById(R.id.pass);
        Button playword = (Button)activity.findViewById(R.id.playword);
        Button exit = (Button)activity.findViewById(R.id.exitGame);
        exchange.setOnClickListener(this);
        pass.setOnClickListener(this);
        playword.setOnClickListener(this);
        exit.setOnClickListener(this);

        //setting the score board's on click listener
        TextView scoreboard = (TextView)activity.findViewById(R.id.scoreText);
        //scoreboard.setOnEditorActionListener(scrabble);

        //setting the hand's on click listener
        ImageView first = (ImageView)activity.findViewById(R.id.aButton);
        ImageView second = (ImageView)activity.findViewById(R.id.bButton);
        ImageView third = (ImageView)activity.findViewById(R.id.cButton);
        ImageView fourth = (ImageView)activity.findViewById(R.id.dButton);
        ImageView fifth = (ImageView)activity.findViewById(R.id.eButton);
        ImageView sixth = (ImageView)activity.findViewById(R.id.gButton);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        fourth.setOnClickListener(this);
        fifth.setOnClickListener(this);
        sixth.setOnClickListener(this);
/*
        //setting the board's on click listener
        ImageView c0r0 = (ImageView)findViewById(R.id.imageView);
        c0r0.setOnClickListener(scrabble);
        ImageView c0r1 = (ImageView)findViewById(R.id.imageView2);
        c0r1.setOnClickListener(scrabble);
        ImageView c0r2 = (ImageView)findViewById(R.id.imageView3);
        c0r2.setOnClickListener(scrabble);
        ImageView c0r3 = (ImageView)findViewById(R.id.imageView4);
        c0r3.setOnClickListener(scrabble);
        ImageView c0r4 = (ImageView)findViewById(R.id.imageView5);
        c0r4.setOnClickListener(scrabble);
        ImageView c0r5 = (ImageView)findViewById(R.id.imageView6);
        c0r5.setOnClickListener(scrabble);
        ImageView c0r6 = (ImageView)findViewById(R.id.imageView7);
        c0r6.setOnClickListener(scrabble);
        ImageView c0r7 = (ImageView)findViewById(R.id.imageView8);
        c0r7.setOnClickListener(scrabble);
        ImageView c0r8 = (ImageView)findViewById(R.id.imageView9);
        c0r8.setOnClickListener(scrabble);
        ImageView c0r9 = (ImageView)findViewById(R.id.imageView10);
        c0r9.setOnClickListener(scrabble);
        ImageView c0r10 = (ImageView)findViewById(R.id.imageView11);
        c0r10.setOnClickListener(scrabble);
        ImageView c0r11 = (ImageView)findViewById(R.id.imageView12);
        c0r11.setOnClickListener(scrabble);
        ImageView c0r12 = (ImageView)findViewById(R.id.imageView13);
        c0r12.setOnClickListener(scrabble);
        ImageView c0r13 = (ImageView)findViewById(R.id.imageView14);
        c0r13.setOnClickListener(scrabble);
        ImageView c0r14 = (ImageView)findViewById(R.id.imageView15);
        c0r14.setOnClickListener(scrabble);

        //column 2
        ImageView c1r0 = (ImageView)findViewById(R.id.imageView16);
        c1r0.setOnClickListener(scrabble);
        ImageView c1r1 = (ImageView)findViewById(R.id.imageView17);
        c1r1.setOnClickListener(scrabble);
        ImageView c1r2 = (ImageView)findViewById(R.id.imageView18);
        c1r2.setOnClickListener(scrabble);
        ImageView c1r3 = (ImageView)findViewById(R.id.imageView19);
        c1r3.setOnClickListener(scrabble);
        ImageView c1r4 = (ImageView)findViewById(R.id.imageView20);
        c1r4.setOnClickListener(scrabble);
        ImageView c1r5 = (ImageView)findViewById(R.id.imageView21);
        c1r5.setOnClickListener(scrabble);
        ImageView c1r6 = (ImageView)findViewById(R.id.imageView22);
        c1r6.setOnClickListener(scrabble);
        ImageView c1r7 = (ImageView)findViewById(R.id.imageView23);
        c1r7.setOnClickListener(scrabble);
        ImageView c1r8 = (ImageView)findViewById(R.id.imageView24);
        c1r8.setOnClickListener(scrabble);
        ImageView c1r9 = (ImageView)findViewById(R.id.imageView25);
        c1r9.setOnClickListener(scrabble);
        ImageView c1r10 = (ImageView)findViewById(R.id.imageView26);
        c1r10.setOnClickListener(scrabble);
        ImageView c1r11 = (ImageView)findViewById(R.id.imageView27);
        c1r11.setOnClickListener(scrabble);
        ImageView c1r12 = (ImageView)findViewById(R.id.imageView28);
        c1r12.setOnClickListener(scrabble);
        ImageView c1r13 = (ImageView)findViewById(R.id.imageView29);
        c1r13.setOnClickListener(scrabble);
        ImageView c1r14 = (ImageView)findViewById(R.id.imageView30);
        c1r14.setOnClickListener(scrabble);

        //column 3
        ImageView c2r0 = (ImageView)findViewById(R.id.imageView31);
        c2r0.setOnClickListener(scrabble);
        ImageView c2r1 = (ImageView)findViewById(R.id.imageView32);
        c2r1.setOnClickListener(scrabble);
        ImageView c2r2 = (ImageView)findViewById(R.id.imageView33);
        c2r2.setOnClickListener(scrabble);
        ImageView c2r3 = (ImageView)findViewById(R.id.imageView34);
        c2r3.setOnClickListener(scrabble);
        ImageView c2r4 = (ImageView)findViewById(R.id.imageView35);
        c2r4.setOnClickListener(scrabble);
        ImageView c2r5 = (ImageView)findViewById(R.id.imageView36);
        c2r5.setOnClickListener(scrabble);
        ImageView c2r6 = (ImageView)findViewById(R.id.imageView37);
        c2r6.setOnClickListener(scrabble);
        ImageView c2r7 = (ImageView)findViewById(R.id.imageView38);
        c2r7.setOnClickListener(scrabble);
        ImageView c2r8 = (ImageView)findViewById(R.id.imageView39);
        c2r8.setOnClickListener(scrabble);
        ImageView c2r9 = (ImageView)findViewById(R.id.imageView40);
        c2r9.setOnClickListener(scrabble);
        ImageView c2r10 = (ImageView)findViewById(R.id.imageView41);
        c2r10.setOnClickListener(scrabble);
        ImageView c2r11 = (ImageView)findViewById(R.id.imageView42);
        c2r11.setOnClickListener(scrabble);
        ImageView c2r12 = (ImageView)findViewById(R.id.imageView43);
        c2r12.setOnClickListener(scrabble);
        ImageView c2r13 = (ImageView)findViewById(R.id.imageView44);
        c2r13.setOnClickListener(scrabble);
        ImageView c2r14 = (ImageView)findViewById(R.id.imageView45);
        c2r14.setOnClickListener(scrabble);

        //column4
        ImageView c3r0 = (ImageView)findViewById(R.id.imageView46);
        c3r0.setOnClickListener(scrabble);
        ImageView c3r1 = (ImageView)findViewById(R.id.imageView47);
        c3r1.setOnClickListener(scrabble);
        ImageView c3r2 = (ImageView)findViewById(R.id.imageView48);
        c3r2.setOnClickListener(scrabble);
        ImageView c3r3 = (ImageView)findViewById(R.id.imageView49);
        c3r3.setOnClickListener(scrabble);
        ImageView c3r4 = (ImageView)findViewById(R.id.imageView50);
        c3r4.setOnClickListener(scrabble);
        ImageView c3r5 = (ImageView)findViewById(R.id.imageView51);
        c3r5.setOnClickListener(scrabble);
        ImageView c3r6 = (ImageView)findViewById(R.id.imageView52);
        c3r6.setOnClickListener(scrabble);
        ImageView c3r7 = (ImageView)findViewById(R.id.imageView53);
        c3r7.setOnClickListener(scrabble);
        ImageView c3r8 = (ImageView)findViewById(R.id.imageView54);
        c3r8.setOnClickListener(scrabble);
        ImageView c3r9 = (ImageView)findViewById(R.id.imageView55);
        c3r9.setOnClickListener(scrabble);
        ImageView c3r10 = (ImageView)findViewById(R.id.imageView56);
        c3r10.setOnClickListener(scrabble);
        ImageView c3r11 = (ImageView)findViewById(R.id.imageView57);
        c3r11.setOnClickListener(scrabble);
        ImageView c3r12 = (ImageView)findViewById(R.id.imageView58);
        c3r12.setOnClickListener(scrabble);
        ImageView c3r13 = (ImageView)findViewById(R.id.imageView59);
        c3r13.setOnClickListener(scrabble);
        ImageView c3r14 = (ImageView)findViewById(R.id.imageView60);
        c3r14.setOnClickListener(scrabble);

        //column5
        ImageView c4r0 = (ImageView)findViewById(R.id.imageView61);
        c4r0.setOnClickListener(scrabble);
        ImageView c4r1 = (ImageView)findViewById(R.id.imageView62);
        c4r1.setOnClickListener(scrabble);
        ImageView c4r2 = (ImageView)findViewById(R.id.imageView63);
        c4r2.setOnClickListener(scrabble);
        ImageView c4r3 = (ImageView)findViewById(R.id.imageView64);
        c4r3.setOnClickListener(scrabble);
        ImageView c4r4 = (ImageView)findViewById(R.id.imageView65);
        c4r4.setOnClickListener(scrabble);
        ImageView c4r5 = (ImageView)findViewById(R.id.imageView66);
        c4r5.setOnClickListener(scrabble);
        ImageView c4r6 = (ImageView)findViewById(R.id.imageView67);
        c4r6.setOnClickListener(scrabble);
        ImageView c4r7 = (ImageView)findViewById(R.id.imageView68);
        c4r7.setOnClickListener(scrabble);
        ImageView c4r8 = (ImageView)findViewById(R.id.imageView69);
        c4r8.setOnClickListener(scrabble);
        ImageView c4r9 = (ImageView)findViewById(R.id.imageView70);
        c4r9.setOnClickListener(scrabble);
        ImageView c4r10 = (ImageView)findViewById(R.id.imageView71);
        c4r10.setOnClickListener(scrabble);
        ImageView c4r11 = (ImageView)findViewById(R.id.imageView72);
        c4r11.setOnClickListener(scrabble);
        ImageView c4r12 = (ImageView)findViewById(R.id.imageView73);
        c4r12.setOnClickListener(scrabble);
        ImageView c4r13 = (ImageView)findViewById(R.id.imageView74);
        c4r13.setOnClickListener(scrabble);
        ImageView c4r14 = (ImageView)findViewById(R.id.imageView75);
        c4r14.setOnClickListener(scrabble);

        //column 6
        ImageView c5r0 = (ImageView)findViewById(R.id.imageView76);
        c5r0.setOnClickListener(scrabble);
        ImageView c5r1 = (ImageView)findViewById(R.id.imageView77);
        c5r1.setOnClickListener(scrabble);
        ImageView c5r2 = (ImageView)findViewById(R.id.imageView78);
        c5r2.setOnClickListener(scrabble);
        ImageView c5r3 = (ImageView)findViewById(R.id.imageView79);
        c5r3.setOnClickListener(scrabble);
        ImageView c5r4 = (ImageView)findViewById(R.id.imageView80);
        c5r4.setOnClickListener(scrabble);
        ImageView c5r5 = (ImageView)findViewById(R.id.imageView81);
        c5r5.setOnClickListener(scrabble);
        ImageView c5r6 = (ImageView)findViewById(R.id.imageView82);
        c5r6.setOnClickListener(scrabble);
        ImageView c5r7 = (ImageView)findViewById(R.id.imageView83);
        c5r7.setOnClickListener(scrabble);
        ImageView c5r8 = (ImageView)findViewById(R.id.imageView84);
        c5r8.setOnClickListener(scrabble);
        ImageView c5r9 = (ImageView)findViewById(R.id.imageView85);
        c5r9.setOnClickListener(scrabble);
        ImageView c5r10 = (ImageView)findViewById(R.id.imageView86);
        c5r10.setOnClickListener(scrabble);
        ImageView c5r11 = (ImageView)findViewById(R.id.imageView87);
        c5r11.setOnClickListener(scrabble);
        ImageView c5r12 = (ImageView)findViewById(R.id.imageView88);
        c5r12.setOnClickListener(scrabble);
        ImageView c5r13 = (ImageView)findViewById(R.id.imageView89);
        c5r13.setOnClickListener(scrabble);
        ImageView c5r14 = (ImageView)findViewById(R.id.imageView90);
        c5r14.setOnClickListener(scrabble);

        //column 7
        ImageView c6r0 = (ImageView)findViewById(R.id.imageView91);
        c6r0.setOnClickListener(scrabble);
        ImageView c6r1 = (ImageView)findViewById(R.id.imageView92);
        c6r1.setOnClickListener(scrabble);
        ImageView c6r2 = (ImageView)findViewById(R.id.imageView93);
        c6r2.setOnClickListener(scrabble);
        ImageView c6r3 = (ImageView)findViewById(R.id.imageView94);
        c6r3.setOnClickListener(scrabble);
        ImageView c6r4 = (ImageView)findViewById(R.id.imageView95);
        c6r4.setOnClickListener(scrabble);
        ImageView c6r5 = (ImageView)findViewById(R.id.imageView96);
        c6r5.setOnClickListener(scrabble);
        ImageView c6r6 = (ImageView)findViewById(R.id.imageView97);
        c6r6.setOnClickListener(scrabble);
        ImageView c6r7 = (ImageView)findViewById(R.id.imageView98);
        c6r7.setOnClickListener(scrabble);
        ImageView c6r8 = (ImageView)findViewById(R.id.imageView99);
        c6r8.setOnClickListener(scrabble);
        ImageView c6r9 = (ImageView)findViewById(R.id.imageView100);
        c6r9.setOnClickListener(scrabble);
        ImageView c6r10 = (ImageView)findViewById(R.id.imageView101);
        c6r10.setOnClickListener(scrabble);
        ImageView c6r11 = (ImageView)findViewById(R.id.imageView102);
        c6r11.setOnClickListener(scrabble);
        ImageView c6r12 = (ImageView)findViewById(R.id.imageView103);
        c6r12.setOnClickListener(scrabble);
        ImageView c6r13 = (ImageView)findViewById(R.id.imageView104);
        c6r13.setOnClickListener(scrabble);
        ImageView c6r14 = (ImageView)findViewById(R.id.imageView105);
        c6r14.setOnClickListener(scrabble);

        //column 8
        ImageView c7r0 = (ImageView)findViewById(R.id.imageView106);
        c7r0.setOnClickListener(scrabble);
        ImageView c7r1 = (ImageView)findViewById(R.id.imageView107);
        c7r1.setOnClickListener(scrabble);
        ImageView c7r2 = (ImageView)findViewById(R.id.imageView108);
        c7r2.setOnClickListener(scrabble);
        ImageView c7r3 = (ImageView)findViewById(R.id.imageView109);
        c7r3.setOnClickListener(scrabble);
        ImageView c7r4 = (ImageView)findViewById(R.id.imageView110);
        c7r4.setOnClickListener(scrabble);
        ImageView c7r5 = (ImageView)findViewById(R.id.imageView111);
        c7r5.setOnClickListener(scrabble);
        ImageView c7r6 = (ImageView)findViewById(R.id.imageView112);
        c7r6.setOnClickListener(scrabble);
        ImageView c7r7 = (ImageView)findViewById(R.id.imageView113);
        c7r7.setOnClickListener(scrabble);
        ImageView c7r8 = (ImageView)findViewById(R.id.imageView114);
        c7r8.setOnClickListener(scrabble);
        ImageView c7r9 = (ImageView)findViewById(R.id.imageView115);
        c7r9.setOnClickListener(scrabble);
        ImageView c7r10 = (ImageView)findViewById(R.id.imageView116);
        c7r10.setOnClickListener(scrabble);
        ImageView c7r11 = (ImageView)findViewById(R.id.imageView117);
        c7r11.setOnClickListener(scrabble);
        ImageView c7r12 = (ImageView)findViewById(R.id.imageView118);
        c7r12.setOnClickListener(scrabble);
        ImageView c7r13 = (ImageView)findViewById(R.id.imageView119);
        c7r13.setOnClickListener(scrabble);
        ImageView c7r14 = (ImageView)findViewById(R.id.imageView120);
        c7r14.setOnClickListener(scrabble);

        //column 7
        ImageView c8r0 = (ImageView)findViewById(R.id.imageView121);
        c8r0.setOnClickListener(scrabble);
        ImageView c8r1 = (ImageView)findViewById(R.id.imageView122);
        c8r1.setOnClickListener(scrabble);
        ImageView c8r2 = (ImageView)findViewById(R.id.imageView123);
        c8r2.setOnClickListener(scrabble);
        ImageView c8r3 = (ImageView)findViewById(R.id.imageView124);
        c8r3.setOnClickListener(scrabble);
        ImageView c8r4 = (ImageView)findViewById(R.id.imageView125);
        c8r4.setOnClickListener(scrabble);
        ImageView c8r5 = (ImageView)findViewById(R.id.imageView126);
        c8r5.setOnClickListener(scrabble);
        ImageView c8r6 = (ImageView)findViewById(R.id.imageView127);
        c8r6.setOnClickListener(scrabble);
        ImageView c8r7 = (ImageView)findViewById(R.id.imageView128);
        c8r7.setOnClickListener(scrabble);
        ImageView c8r8 = (ImageView)findViewById(R.id.imageView129);
        c8r8.setOnClickListener(scrabble);
        ImageView c8r9 = (ImageView)findViewById(R.id.imageView130);
        c8r9.setOnClickListener(scrabble);
        ImageView c8r10 = (ImageView)findViewById(R.id.imageView131);
        c8r10.setOnClickListener(scrabble);
        ImageView c8r11 = (ImageView)findViewById(R.id.imageView132);
        c8r11.setOnClickListener(scrabble);
        ImageView c8r12 = (ImageView)findViewById(R.id.imageView133);
        c8r12.setOnClickListener(scrabble);
        ImageView c8r13 = (ImageView)findViewById(R.id.imageView134);
        c8r13.setOnClickListener(scrabble);
        ImageView c8r14 = (ImageView)findViewById(R.id.imageView135);
        c8r14.setOnClickListener(scrabble);

        //column 10
        ImageView c9r0 = (ImageView)findViewById(R.id.imageView136);
        c9r0.setOnClickListener(scrabble);
        ImageView c9r1 = (ImageView)findViewById(R.id.imageView137);
        c9r1.setOnClickListener(scrabble);
        ImageView c9r2 = (ImageView)findViewById(R.id.imageView138);
        c9r2.setOnClickListener(scrabble);
        ImageView c9r3 = (ImageView)findViewById(R.id.imageView139);
        c9r3.setOnClickListener(scrabble);
        ImageView c9r4 = (ImageView)findViewById(R.id.imageView140);
        c9r4.setOnClickListener(scrabble);
        ImageView c9r5 = (ImageView)findViewById(R.id.imageView141);
        c9r5.setOnClickListener(scrabble);
        ImageView c9r6 = (ImageView)findViewById(R.id.imageView142);
        c9r6.setOnClickListener(scrabble);
        ImageView c9r7 = (ImageView)findViewById(R.id.imageView143);
        c9r7.setOnClickListener(scrabble);
        ImageView c9r8 = (ImageView)findViewById(R.id.imageView144);
        c9r8.setOnClickListener(scrabble);
        ImageView c9r9 = (ImageView)findViewById(R.id.imageView145);
        c9r9.setOnClickListener(scrabble);
        ImageView c9r10 = (ImageView)findViewById(R.id.imageView146);
        c9r10.setOnClickListener(scrabble);
        ImageView c9r11 = (ImageView)findViewById(R.id.imageView147);
        c9r11.setOnClickListener(scrabble);
        ImageView c9r12 = (ImageView)findViewById(R.id.imageView148);
        c9r12.setOnClickListener(scrabble);
        ImageView c9r13 = (ImageView)findViewById(R.id.imageView149);
        c9r13.setOnClickListener(scrabble);
        ImageView c9r14 = (ImageView)findViewById(R.id.imageView150);
        c9r14.setOnClickListener(scrabble);

        //column 11
        ImageView c10r0 = (ImageView)findViewById(R.id.imageView151);
        c10r0.setOnClickListener(scrabble);
        ImageView c10r1 = (ImageView)findViewById(R.id.imageView152);
        c10r1.setOnClickListener(scrabble);
        ImageView c10r2 = (ImageView)findViewById(R.id.imageView153);
        c10r2.setOnClickListener(scrabble);
        ImageView c10r3 = (ImageView)findViewById(R.id.imageView154);
        c10r3.setOnClickListener(scrabble);
        ImageView c10r4 = (ImageView)findViewById(R.id.imageView155);
        c10r4.setOnClickListener(scrabble);
        ImageView c10r5 = (ImageView)findViewById(R.id.imageView156);
        c10r5.setOnClickListener(scrabble);
        ImageView c10r6 = (ImageView)findViewById(R.id.imageView157);
        c10r6.setOnClickListener(scrabble);
        ImageView c10r7 = (ImageView)findViewById(R.id.imageView158);
        c10r7.setOnClickListener(scrabble);
        ImageView c10r8 = (ImageView)findViewById(R.id.imageView159);
        c10r8.setOnClickListener(scrabble);
        ImageView c10r9 = (ImageView)findViewById(R.id.imageView160);
        c10r9.setOnClickListener(scrabble);
        ImageView c10r10 = (ImageView)findViewById(R.id.imageView161);
        c10r10.setOnClickListener(scrabble);
        ImageView c10r11 = (ImageView)findViewById(R.id.imageView162);
        c10r11.setOnClickListener(scrabble);
        ImageView c10r12 = (ImageView)findViewById(R.id.imageView163);
        c10r12.setOnClickListener(scrabble);
        ImageView c10r13 = (ImageView)findViewById(R.id.imageView164);
        c10r13.setOnClickListener(scrabble);
        ImageView c10r14 = (ImageView)findViewById(R.id.imageView165);
        c10r14.setOnClickListener(scrabble);

        //column12
        ImageView c11r0 = (ImageView)findViewById(R.id.imageView166);
        c11r0.setOnClickListener(scrabble);
        ImageView c11r1 = (ImageView)findViewById(R.id.imageView167);
        c11r1.setOnClickListener(scrabble);
        ImageView c11r2 = (ImageView)findViewById(R.id.imageView168);
        c11r2.setOnClickListener(scrabble);
        ImageView c11r3 = (ImageView)findViewById(R.id.imageView169);
        c11r3.setOnClickListener(scrabble);
        ImageView c11r4 = (ImageView)findViewById(R.id.imageView170);
        c11r4.setOnClickListener(scrabble);
        ImageView c11r5 = (ImageView)findViewById(R.id.imageView171);
        c11r5.setOnClickListener(scrabble);
        ImageView c11r6 = (ImageView)findViewById(R.id.imageView172);
        c11r6.setOnClickListener(scrabble);
        ImageView c11r7 = (ImageView)findViewById(R.id.imageView173);
        c11r7.setOnClickListener(scrabble);
        ImageView c11r8 = (ImageView)findViewById(R.id.imageView174);
        c11r8.setOnClickListener(scrabble);
        ImageView c11r9 = (ImageView)findViewById(R.id.imageView175);
        c11r9.setOnClickListener(scrabble);
        ImageView c11r10 = (ImageView)findViewById(R.id.imageView176);
        c11r10.setOnClickListener(scrabble);
        ImageView c11r11 = (ImageView)findViewById(R.id.imageView177);
        c11r11.setOnClickListener(scrabble);
        ImageView c11r12 = (ImageView)findViewById(R.id.imageView178);
        c11r12.setOnClickListener(scrabble);
        ImageView c11r13 = (ImageView)findViewById(R.id.imageView179);
        c11r13.setOnClickListener(scrabble);
        ImageView c11r14 = (ImageView)findViewById(R.id.imageView180);
        c11r14.setOnClickListener(scrabble);

        //column 13
        ImageView c12r0 = (ImageView)findViewById(R.id.imageView181);
        c12r0.setOnClickListener(scrabble);
        ImageView c12r1 = (ImageView)findViewById(R.id.imageView182);
        c12r1.setOnClickListener(scrabble);
        ImageView c12r2 = (ImageView)findViewById(R.id.imageView183);
        c12r2.setOnClickListener(scrabble);
        ImageView c12r3 = (ImageView)findViewById(R.id.imageView184);
        c12r3.setOnClickListener(scrabble);
        ImageView c12r4 = (ImageView)findViewById(R.id.imageView185);
        c12r4.setOnClickListener(scrabble);
        ImageView c12r5 = (ImageView)findViewById(R.id.imageView186);
        c12r5.setOnClickListener(scrabble);
        ImageView c12r6 = (ImageView)findViewById(R.id.imageView187);
        c12r6.setOnClickListener(scrabble);
        ImageView c12r7 = (ImageView)findViewById(R.id.imageView188);
        c12r7.setOnClickListener(scrabble);
        ImageView c12r8 = (ImageView)findViewById(R.id.imageView189);
        c12r8.setOnClickListener(scrabble);
        ImageView c12r9 = (ImageView)findViewById(R.id.imageView190);
        c12r9.setOnClickListener(scrabble);
        ImageView c12r10 = (ImageView)findViewById(R.id.imageView191);
        c12r10.setOnClickListener(scrabble);
        ImageView c12r11 = (ImageView)findViewById(R.id.imageView192);
        c12r11.setOnClickListener(scrabble);
        ImageView c12r12 = (ImageView)findViewById(R.id.imageView193);
        c12r12.setOnClickListener(scrabble);
        ImageView c12r13 = (ImageView)findViewById(R.id.imageView194);
        c12r13.setOnClickListener(scrabble);
        ImageView c12r14 = (ImageView)findViewById(R.id.imageView195);
        c12r14.setOnClickListener(scrabble);

        //column 14
        ImageView c13r0 = (ImageView)findViewById(R.id.imageView196);
        c13r0.setOnClickListener(scrabble);
        ImageView c13r1 = (ImageView)findViewById(R.id.imageView197);
        c13r1.setOnClickListener(scrabble);
        ImageView c13r2 = (ImageView)findViewById(R.id.imageView198);
        c13r2.setOnClickListener(scrabble);
        ImageView c13r3 = (ImageView)findViewById(R.id.imageView199);
        c13r3.setOnClickListener(scrabble);
        ImageView c13r4 = (ImageView)findViewById(R.id.imageView200);
        c13r4.setOnClickListener(scrabble);
        ImageView c13r5 = (ImageView)findViewById(R.id.imageView201);
        c13r5.setOnClickListener(scrabble);
        ImageView c13r6 = (ImageView)findViewById(R.id.imageView202);
        c13r6.setOnClickListener(scrabble);
        ImageView c13r7 = (ImageView)findViewById(R.id.imageView203);
        c13r7.setOnClickListener(scrabble);
        ImageView c13r8 = (ImageView)findViewById(R.id.imageView204);
        c13r8.setOnClickListener(scrabble);
        ImageView c13r9 = (ImageView)findViewById(R.id.imageView205);
        c13r9.setOnClickListener(scrabble);
        ImageView c13r10 = (ImageView)findViewById(R.id.imageView206);
        c13r10.setOnClickListener(scrabble);
        ImageView c13r11 = (ImageView)findViewById(R.id.imageView207);
        c13r11.setOnClickListener(scrabble);
        ImageView c13r12 = (ImageView)findViewById(R.id.imageView208);
        c13r12.setOnClickListener(scrabble);
        ImageView c13r13 = (ImageView)findViewById(R.id.imageView209);
        c13r13.setOnClickListener(scrabble);
        ImageView c13r14 = (ImageView)findViewById(R.id.imageView210);
        c13r14.setOnClickListener(scrabble);

        //column 15
        ImageView c14r0 = (ImageView)findViewById(R.id.imageView211);
        c14r0.setOnClickListener(scrabble);
        ImageView c14r1 = (ImageView)findViewById(R.id.imageView212);
        c14r1.setOnClickListener(scrabble);
        ImageView c14r2 = (ImageView)findViewById(R.id.imageView213);
        c14r2.setOnClickListener(scrabble);
        ImageView c14r3 = (ImageView)findViewById(R.id.imageView214);
        c14r3.setOnClickListener(scrabble);
        ImageView c14r4 = (ImageView)findViewById(R.id.imageView215);
        c14r4.setOnClickListener(scrabble);
        ImageView c14r5 = (ImageView)findViewById(R.id.imageView216);
        c14r5.setOnClickListener(scrabble);
        ImageView c14r6 = (ImageView)findViewById(R.id.imageView217);
        c14r6.setOnClickListener(scrabble);
        ImageView c14r7 = (ImageView)findViewById(R.id.imageView218);
        c14r7.setOnClickListener(scrabble);
        ImageView c14r8 = (ImageView)findViewById(R.id.imageView219);
        c14r8.setOnClickListener(scrabble);
        ImageView c14r9 = (ImageView)findViewById(R.id.imageView220);
        c14r9.setOnClickListener(scrabble);
        ImageView c14r10 = (ImageView)findViewById(R.id.imageView221);
        c14r10.setOnClickListener(scrabble);
        ImageView c14r11 = (ImageView)findViewById(R.id.imageView222);
        c14r11.setOnClickListener(scrabble);
        ImageView c14r12 = (ImageView)findViewById(R.id.imageView223);
        c14r12.setOnClickListener(scrabble);
        ImageView c14r13 = (ImageView)findViewById(R.id.imageView224);
        c14r13.setOnClickListener(scrabble);
        ImageView c14r14 = (ImageView)findViewById(R.id.imageView225);
        c14r14.setOnClickListener(scrabble);

        surfaceView = (ScrabbleSurfaceView)activity.findViewById(R.id.scrabbleSurfaceView);
        this.score = (TextView)activity.findViewById(R.id.scoreNumber);
        this.exchange = (Button)activity.findViewById(R.id.exchange);
        this.passButton = (Button)activity.findViewById(R.id.pass);
        this.playWordButton = (Button)activity.findViewById(R.id.playword);
        this.exitButton = (Button)activity.findViewById(R.id.exitGame);
    */
    }
    public void onClick(View button) {

        boolean isVertical;

        if (button.getId() == R.id.exchange) {
            Exchange exchange = new Exchange(this, surfaceView.getScrabbleLetter());
            game.sendAction(exchange);
        }
        else if(button.getId() == R.id.pass){
            Pass pass = new Pass(this);
            game.sendAction(pass);
        }
        else if(button.getId() == R.id.playword){
            isVertical = scrabbleCopy.isVertical(surfaceView.getXY());
            PlayWord playWord = new PlayWord(this, surfaceView.getScrabbleLetter(), surfaceView.getxCoord(), surfaceView.getyCoord(), isVertical);
            game.sendAction(playWord);
        }
        else if(button.getId() == R.id.exitGame){
            ExitGame exitGame = new ExitGame(this);
            game.sendAction(exitGame);
        }
        //surfaceView.onClick(view);
    }
}
