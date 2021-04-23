package com.example.thescrabblegame;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameInfo;
import com.example.thescrabblegame.game.GameFramework.players.GameHumanPlayer;

import java.util.ArrayList;

public class HumanScrabblePlayer extends GameHumanPlayer implements View.OnClickListener {

    private TextView score = null;
    private ScrabbleSurfaceView surfaceView;
    public ScrabbleState scrabbleCopy;
    public int layoutId;
    private ArrayList<String> letters = new ArrayList<>();
    private ScrabbleLetter[] letter;
    private int[] specialTileArray;
    private ArrayList<Integer> tempInts = new ArrayList<>();
    private ArrayList<Integer> tempXCords = new ArrayList<>();
    private ArrayList<Integer> tempYCords = new ArrayList<>();
    private ImageView first;
    private ImageView second;
    private ImageView third;
    private ImageView fourth;
    private ImageView fifth;
    private ImageView sixth;
    private ImageView seventh;
    private boolean isFirst;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public HumanScrabblePlayer(String name, int layoutId) {
        super(name);
        //this.surfaceView = surfaceView;
        this.layoutId = layoutId;
        scrabbleCopy = new ScrabbleState();
        isFirst = false;
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        first.invalidate();
        second.invalidate();
        third.invalidate();
        fourth.invalidate();
        fifth.invalidate();
        sixth.invalidate();
        seventh.invalidate();
        ScrabbleState infoScrabbleState = (ScrabbleState) info;
        this.scrabbleCopy = infoScrabbleState;
        drawHand(infoScrabbleState);
        drawBoard(infoScrabbleState);

        if (info instanceof ScrabbleState) {
            if((ScrabbleState) info == null){
                return;
            }
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
        activity.setContentView(layoutId);

        //activity.setContentView(R.layout.activity_main);
       // surfaceView = activity.findViewById(R.id.scrabbleSurfaceView);
        myActivity = ((ScrabbleMainActivity) activity);

        Button exchange = (Button)activity.findViewById(R.id.exchange);
        Button pass = (Button)activity.findViewById(R.id.pass);
        Button playword = (Button)activity.findViewById(R.id.playword);
        Button exit = (Button)activity.findViewById(R.id.exitGame);
        exchange.setOnClickListener(this);
        pass.setOnClickListener(this);
        playword.setOnClickListener(this);
        exit.setOnClickListener(this);

        //drawHand(scrabbleCopy);
        //setting the score board's on click listener
        TextView scoreboard = (TextView)activity.findViewById(R.id.scoreNumber);
        this.score = scoreboard;
        //scoreboard.setOnEditorActionListener(this);

        //setting the hand's on click listener
        first = (ImageView)activity.findViewById(R.id.aButton);
        second = (ImageView)activity.findViewById(R.id.bButton);
        third = (ImageView)activity.findViewById(R.id.cButton);
        fourth = (ImageView)activity.findViewById(R.id.dButton);
        fifth = (ImageView)activity.findViewById(R.id.eButton);
        sixth = (ImageView)activity.findViewById(R.id.fButton);
        seventh = (ImageView)activity.findViewById(R.id.gButton);
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        fourth.setOnClickListener(this);
        fifth.setOnClickListener(this);
        sixth.setOnClickListener(this);
        seventh.setOnClickListener(this);


        //setting the board's on click listener
        ImageView c0r0 = (ImageView)activity.findViewById(R.id.imageView);
        c0r0.setOnClickListener(this);
        ImageView c0r1 = (ImageView)activity.findViewById(R.id.imageView2);
        c0r1.setOnClickListener(this);
        ImageView c0r2 = (ImageView)activity.findViewById(R.id.imageView3);
        c0r2.setOnClickListener(this);
        ImageView c0r3 = (ImageView)activity.findViewById(R.id.imageView4);
        c0r3.setOnClickListener(this);
        ImageView c0r4 = (ImageView)activity.findViewById(R.id.imageView5);
        c0r4.setOnClickListener(this);
        ImageView c0r5 = (ImageView)activity.findViewById(R.id.imageView6);
        c0r5.setOnClickListener(this);
        ImageView c0r6 = (ImageView)activity.findViewById(R.id.imageView7);
        c0r6.setOnClickListener(this);
        ImageView c0r7 = (ImageView)activity.findViewById(R.id.imageView8);
        c0r7.setOnClickListener(this);
        ImageView c0r8 = (ImageView)activity.findViewById(R.id.imageView9);
        c0r8.setOnClickListener(this);
        ImageView c0r9 = (ImageView)activity.findViewById(R.id.imageView10);
        c0r9.setOnClickListener(this);
        ImageView c0r10 = (ImageView)activity.findViewById(R.id.imageView11);
        c0r10.setOnClickListener(this);
        ImageView c0r11 = (ImageView)activity.findViewById(R.id.imageView12);
        c0r11.setOnClickListener(this);
        ImageView c0r12 = (ImageView)activity.findViewById(R.id.imageView13);
        c0r12.setOnClickListener(this);
        ImageView c0r13 = (ImageView)activity.findViewById(R.id.imageView14);
        c0r13.setOnClickListener(this);
        ImageView c0r14 = (ImageView)activity.findViewById(R.id.imageView15);
        c0r14.setOnClickListener(this);

        //column 2
        ImageView c1r0 = (ImageView)activity.findViewById(R.id.imageView16);
        c1r0.setOnClickListener(this);
        ImageView c1r1 = (ImageView)activity.findViewById(R.id.imageView17);
        c1r1.setOnClickListener(this);
        ImageView c1r2 = (ImageView)activity.findViewById(R.id.imageView18);
        c1r2.setOnClickListener(this);
        ImageView c1r3 = (ImageView)activity.findViewById(R.id.imageView19);
        c1r3.setOnClickListener(this);
        ImageView c1r4 = (ImageView)activity.findViewById(R.id.imageView20);
        c1r4.setOnClickListener(this);
        ImageView c1r5 = (ImageView)activity.findViewById(R.id.imageView21);
        c1r5.setOnClickListener(this);
        ImageView c1r6 = (ImageView)activity.findViewById(R.id.imageView22);
        c1r6.setOnClickListener(this);
        ImageView c1r7 = (ImageView)activity.findViewById(R.id.imageView23);
        c1r7.setOnClickListener(this);
        ImageView c1r8 = (ImageView)activity.findViewById(R.id.imageView24);
        c1r8.setOnClickListener(this);
        ImageView c1r9 = (ImageView)activity.findViewById(R.id.imageView25);
        c1r9.setOnClickListener(this);
        ImageView c1r10 = (ImageView)activity.findViewById(R.id.imageView26);
        c1r10.setOnClickListener(this);
        ImageView c1r11 = (ImageView)activity.findViewById(R.id.imageView27);
        c1r11.setOnClickListener(this);
        ImageView c1r12 = (ImageView)activity.findViewById(R.id.imageView28);
        c1r12.setOnClickListener(this);
        ImageView c1r13 = (ImageView)activity.findViewById(R.id.imageView29);
        c1r13.setOnClickListener(this);
        ImageView c1r14 = (ImageView)activity.findViewById(R.id.imageView30);
        c1r14.setOnClickListener(this);

        //column 3
        ImageView c2r0 = (ImageView)activity.findViewById(R.id.imageView31);
        c2r0.setOnClickListener(this);
        ImageView c2r1 = (ImageView)activity.findViewById(R.id.imageView32);
        c2r1.setOnClickListener(this);
        ImageView c2r2 = (ImageView)activity.findViewById(R.id.imageView33);
        c2r2.setOnClickListener(this);
        ImageView c2r3 = (ImageView)activity.findViewById(R.id.imageView34);
        c2r3.setOnClickListener(this);
        ImageView c2r4 = (ImageView)activity.findViewById(R.id.imageView35);
        c2r4.setOnClickListener(this);
        ImageView c2r5 = (ImageView)activity.findViewById(R.id.imageView36);
        c2r5.setOnClickListener(this);
        ImageView c2r6 = (ImageView)activity.findViewById(R.id.imageView37);
        c2r6.setOnClickListener(this);
        ImageView c2r7 = (ImageView)activity.findViewById(R.id.imageView38);
        c2r7.setOnClickListener(this);
        ImageView c2r8 = (ImageView)activity.findViewById(R.id.imageView39);
        c2r8.setOnClickListener(this);
        ImageView c2r9 = (ImageView)activity.findViewById(R.id.imageView40);
        c2r9.setOnClickListener(this);
        ImageView c2r10 = (ImageView)activity.findViewById(R.id.imageView41);
        c2r10.setOnClickListener(this);
        ImageView c2r11 = (ImageView)activity.findViewById(R.id.imageView42);
        c2r11.setOnClickListener(this);
        ImageView c2r12 = (ImageView)activity.findViewById(R.id.imageView43);
        c2r12.setOnClickListener(this);
        ImageView c2r13 = (ImageView)activity.findViewById(R.id.imageView44);
        c2r13.setOnClickListener(this);
        ImageView c2r14 = (ImageView)activity.findViewById(R.id.imageView45);
        c2r14.setOnClickListener(this);

        //column4
        ImageView c3r0 = (ImageView)activity.findViewById(R.id.imageView46);
        c3r0.setOnClickListener(this);
        ImageView c3r1 = (ImageView)activity.findViewById(R.id.imageView47);
        c3r1.setOnClickListener(this);
        ImageView c3r2 = (ImageView)activity.findViewById(R.id.imageView48);
        c3r2.setOnClickListener(this);
        ImageView c3r3 = (ImageView)activity.findViewById(R.id.imageView49);
        c3r3.setOnClickListener(this);
        ImageView c3r4 = (ImageView)activity.findViewById(R.id.imageView50);
        c3r4.setOnClickListener(this);
        ImageView c3r5 = (ImageView)activity.findViewById(R.id.imageView51);
        c3r5.setOnClickListener(this);
        ImageView c3r6 = (ImageView)activity.findViewById(R.id.imageView52);
        c3r6.setOnClickListener(this);
        ImageView c3r7 = (ImageView)activity.findViewById(R.id.imageView53);
        c3r7.setOnClickListener(this);
        ImageView c3r8 = (ImageView)activity.findViewById(R.id.imageView54);
        c3r8.setOnClickListener(this);
        ImageView c3r9 = (ImageView)activity.findViewById(R.id.imageView55);
        c3r9.setOnClickListener(this);
        ImageView c3r10 = (ImageView)activity.findViewById(R.id.imageView56);
        c3r10.setOnClickListener(this);
        ImageView c3r11 = (ImageView)activity.findViewById(R.id.imageView57);
        c3r11.setOnClickListener(this);
        ImageView c3r12 = (ImageView)activity.findViewById(R.id.imageView58);
        c3r12.setOnClickListener(this);
        ImageView c3r13 = (ImageView)activity.findViewById(R.id.imageView59);
        c3r13.setOnClickListener(this);
        ImageView c3r14 = (ImageView)activity.findViewById(R.id.imageView60);
        c3r14.setOnClickListener(this);

        //column5
        ImageView c4r0 = (ImageView)activity.findViewById(R.id.imageView61);
        c4r0.setOnClickListener(this);
        ImageView c4r1 = (ImageView)activity.findViewById(R.id.imageView62);
        c4r1.setOnClickListener(this);
        ImageView c4r2 = (ImageView)activity.findViewById(R.id.imageView63);
        c4r2.setOnClickListener(this);
        ImageView c4r3 = (ImageView)activity.findViewById(R.id.imageView64);
        c4r3.setOnClickListener(this);
        ImageView c4r4 = (ImageView)activity.findViewById(R.id.imageView65);
        c4r4.setOnClickListener(this);
        ImageView c4r5 = (ImageView)activity.findViewById(R.id.imageView66);
        c4r5.setOnClickListener(this);
        ImageView c4r6 = (ImageView)activity.findViewById(R.id.imageView67);
        c4r6.setOnClickListener(this);
        ImageView c4r7 = (ImageView)activity.findViewById(R.id.imageView68);
        c4r7.setOnClickListener(this);
        ImageView c4r8 = (ImageView)activity.findViewById(R.id.imageView69);
        c4r8.setOnClickListener(this);
        ImageView c4r9 = (ImageView)activity.findViewById(R.id.imageView70);
        c4r9.setOnClickListener(this);
        ImageView c4r10 = (ImageView)activity.findViewById(R.id.imageView71);
        c4r10.setOnClickListener(this);
        ImageView c4r11 = (ImageView)activity.findViewById(R.id.imageView72);
        c4r11.setOnClickListener(this);
        ImageView c4r12 = (ImageView)activity.findViewById(R.id.imageView73);
        c4r12.setOnClickListener(this);
        ImageView c4r13 = (ImageView)activity.findViewById(R.id.imageView74);
        c4r13.setOnClickListener(this);
        ImageView c4r14 = (ImageView)activity.findViewById(R.id.imageView75);
        c4r14.setOnClickListener(this);

        //column 6
        ImageView c5r0 = (ImageView)activity.findViewById(R.id.imageView76);
        c5r0.setOnClickListener(this);
        ImageView c5r1 = (ImageView)activity.findViewById(R.id.imageView77);
        c5r1.setOnClickListener(this);
        ImageView c5r2 = (ImageView)activity.findViewById(R.id.imageView78);
        c5r2.setOnClickListener(this);
        ImageView c5r3 = (ImageView)activity.findViewById(R.id.imageView79);
        c5r3.setOnClickListener(this);
        ImageView c5r4 = (ImageView)activity.findViewById(R.id.imageView80);
        c5r4.setOnClickListener(this);
        ImageView c5r5 = (ImageView)activity.findViewById(R.id.imageView81);
        c5r5.setOnClickListener(this);
        ImageView c5r6 = (ImageView)activity.findViewById(R.id.imageView82);
        c5r6.setOnClickListener(this);
        ImageView c5r7 = (ImageView)activity.findViewById(R.id.imageView83);
        c5r7.setOnClickListener(this);
        ImageView c5r8 = (ImageView)activity.findViewById(R.id.imageView84);
        c5r8.setOnClickListener(this);
        ImageView c5r9 = (ImageView)activity.findViewById(R.id.imageView85);
        c5r9.setOnClickListener(this);
        ImageView c5r10 = (ImageView)activity.findViewById(R.id.imageView86);
        c5r10.setOnClickListener(this);
        ImageView c5r11 = (ImageView)activity.findViewById(R.id.imageView87);
        c5r11.setOnClickListener(this);
        ImageView c5r12 = (ImageView)activity.findViewById(R.id.imageView88);
        c5r12.setOnClickListener(this);
        ImageView c5r13 = (ImageView)activity.findViewById(R.id.imageView89);
        c5r13.setOnClickListener(this);
        ImageView c5r14 = (ImageView)activity.findViewById(R.id.imageView90);
        c5r14.setOnClickListener(this);

        //column 7
        ImageView c6r0 = (ImageView)activity.findViewById(R.id.imageView91);
        c6r0.setOnClickListener(this);
        ImageView c6r1 = (ImageView)activity.findViewById(R.id.imageView92);
        c6r1.setOnClickListener(this);
        ImageView c6r2 = (ImageView)activity.findViewById(R.id.imageView93);
        c6r2.setOnClickListener(this);
        ImageView c6r3 = (ImageView)activity.findViewById(R.id.imageView94);
        c6r3.setOnClickListener(this);
        ImageView c6r4 = (ImageView)activity.findViewById(R.id.imageView95);
        c6r4.setOnClickListener(this);
        ImageView c6r5 = (ImageView)activity.findViewById(R.id.imageView96);
        c6r5.setOnClickListener(this);
        ImageView c6r6 = (ImageView)activity.findViewById(R.id.imageView97);
        c6r6.setOnClickListener(this);
        ImageView c6r7 = (ImageView)activity.findViewById(R.id.imageView98);
        c6r7.setOnClickListener(this);
        ImageView c6r8 = (ImageView)activity.findViewById(R.id.imageView99);
        c6r8.setOnClickListener(this);
        ImageView c6r9 = (ImageView)activity.findViewById(R.id.imageView100);
        c6r9.setOnClickListener(this);
        ImageView c6r10 = (ImageView)activity.findViewById(R.id.imageView101);
        c6r10.setOnClickListener(this);
        ImageView c6r11 = (ImageView)activity.findViewById(R.id.imageView102);
        c6r11.setOnClickListener(this);
        ImageView c6r12 = (ImageView)activity.findViewById(R.id.imageView103);
        c6r12.setOnClickListener(this);
        ImageView c6r13 = (ImageView)activity.findViewById(R.id.imageView104);
        c6r13.setOnClickListener(this);
        ImageView c6r14 = (ImageView)activity.findViewById(R.id.imageView105);
        c6r14.setOnClickListener(this);

        //column 8
        ImageView c7r0 = (ImageView)activity.findViewById(R.id.imageView106);
        c7r0.setOnClickListener(this);
        ImageView c7r1 = (ImageView)activity.findViewById(R.id.imageView107);
        c7r1.setOnClickListener(this);
        ImageView c7r2 = (ImageView)activity.findViewById(R.id.imageView108);
        c7r2.setOnClickListener(this);
        ImageView c7r3 = (ImageView)activity.findViewById(R.id.imageView109);
        c7r3.setOnClickListener(this);
        ImageView c7r4 = (ImageView)activity.findViewById(R.id.imageView110);
        c7r4.setOnClickListener(this);
        ImageView c7r5 = (ImageView)activity.findViewById(R.id.imageView111);
        c7r5.setOnClickListener(this);
        ImageView c7r6 = (ImageView)activity.findViewById(R.id.imageView112);
        c7r6.setOnClickListener(this);
        ImageView c7r7 = (ImageView)activity.findViewById(R.id.imageView113);
        c7r7.setOnClickListener(this);
        ImageView c7r8 = (ImageView)activity.findViewById(R.id.imageView114);
        c7r8.setOnClickListener(this);
        ImageView c7r9 = (ImageView)activity.findViewById(R.id.imageView115);
        c7r9.setOnClickListener(this);
        ImageView c7r10 = (ImageView)activity.findViewById(R.id.imageView116);
        c7r10.setOnClickListener(this);
        ImageView c7r11 = (ImageView)activity.findViewById(R.id.imageView117);
        c7r11.setOnClickListener(this);
        ImageView c7r12 = (ImageView)activity.findViewById(R.id.imageView118);
        c7r12.setOnClickListener(this);
        ImageView c7r13 = (ImageView)activity.findViewById(R.id.imageView119);
        c7r13.setOnClickListener(this);
        ImageView c7r14 = (ImageView)activity.findViewById(R.id.imageView120);
        c7r14.setOnClickListener(this);

        //column 7
        ImageView c8r0 = (ImageView)activity.findViewById(R.id.imageView121);
        c8r0.setOnClickListener(this);
        ImageView c8r1 = (ImageView)activity.findViewById(R.id.imageView122);
        c8r1.setOnClickListener(this);
        ImageView c8r2 = (ImageView)activity.findViewById(R.id.imageView123);
        c8r2.setOnClickListener(this);
        ImageView c8r3 = (ImageView)activity.findViewById(R.id.imageView124);
        c8r3.setOnClickListener(this);
        ImageView c8r4 = (ImageView)activity.findViewById(R.id.imageView125);
        c8r4.setOnClickListener(this);
        ImageView c8r5 = (ImageView)activity.findViewById(R.id.imageView126);
        c8r5.setOnClickListener(this);
        ImageView c8r6 = (ImageView)activity.findViewById(R.id.imageView127);
        c8r6.setOnClickListener(this);
        ImageView c8r7 = (ImageView)activity.findViewById(R.id.imageView128);
        c8r7.setOnClickListener(this);
        ImageView c8r8 = (ImageView)activity.findViewById(R.id.imageView129);
        c8r8.setOnClickListener(this);
        ImageView c8r9 = (ImageView)activity.findViewById(R.id.imageView130);
        c8r9.setOnClickListener(this);
        ImageView c8r10 = (ImageView)activity.findViewById(R.id.imageView131);
        c8r10.setOnClickListener(this);
        ImageView c8r11 = (ImageView)activity.findViewById(R.id.imageView132);
        c8r11.setOnClickListener(this);
        ImageView c8r12 = (ImageView)activity.findViewById(R.id.imageView133);
        c8r12.setOnClickListener(this);
        ImageView c8r13 = (ImageView)activity.findViewById(R.id.imageView134);
        c8r13.setOnClickListener(this);
        ImageView c8r14 = (ImageView)activity.findViewById(R.id.imageView135);
        c8r14.setOnClickListener(this);

        //column 10
        ImageView c9r0 = (ImageView)activity.findViewById(R.id.imageView136);
        c9r0.setOnClickListener(this);
        ImageView c9r1 = (ImageView)activity.findViewById(R.id.imageView137);
        c9r1.setOnClickListener(this);
        ImageView c9r2 = (ImageView)activity.findViewById(R.id.imageView138);
        c9r2.setOnClickListener(this);
        ImageView c9r3 = (ImageView)activity.findViewById(R.id.imageView139);
        c9r3.setOnClickListener(this);
        ImageView c9r4 = (ImageView)activity.findViewById(R.id.imageView140);
        c9r4.setOnClickListener(this);
        ImageView c9r5 = (ImageView)activity.findViewById(R.id.imageView141);
        c9r5.setOnClickListener(this);
        ImageView c9r6 = (ImageView)activity.findViewById(R.id.imageView142);
        c9r6.setOnClickListener(this);
        ImageView c9r7 = (ImageView)activity.findViewById(R.id.imageView143);
        c9r7.setOnClickListener(this);
        ImageView c9r8 = (ImageView)activity.findViewById(R.id.imageView144);
        c9r8.setOnClickListener(this);
        ImageView c9r9 = (ImageView)activity.findViewById(R.id.imageView145);
        c9r9.setOnClickListener(this);
        ImageView c9r10 = (ImageView)activity.findViewById(R.id.imageView146);
        c9r10.setOnClickListener(this);
        ImageView c9r11 = (ImageView)activity.findViewById(R.id.imageView147);
        c9r11.setOnClickListener(this);
        ImageView c9r12 = (ImageView)activity.findViewById(R.id.imageView148);
        c9r12.setOnClickListener(this);
        ImageView c9r13 = (ImageView)activity.findViewById(R.id.imageView149);
        c9r13.setOnClickListener(this);
        ImageView c9r14 = (ImageView)activity.findViewById(R.id.imageView150);
        c9r14.setOnClickListener(this);

        //column 11
        ImageView c10r0 = (ImageView)activity.findViewById(R.id.imageView151);
        c10r0.setOnClickListener(this);
        ImageView c10r1 = (ImageView)activity.findViewById(R.id.imageView152);
        c10r1.setOnClickListener(this);
        ImageView c10r2 = (ImageView)activity.findViewById(R.id.imageView153);
        c10r2.setOnClickListener(this);
        ImageView c10r3 = (ImageView)activity.findViewById(R.id.imageView154);
        c10r3.setOnClickListener(this);
        ImageView c10r4 = (ImageView)activity.findViewById(R.id.imageView155);
        c10r4.setOnClickListener(this);
        ImageView c10r5 = (ImageView)activity.findViewById(R.id.imageView156);
        c10r5.setOnClickListener(this);
        ImageView c10r6 = (ImageView)activity.findViewById(R.id.imageView157);
        c10r6.setOnClickListener(this);
        ImageView c10r7 = (ImageView)activity.findViewById(R.id.imageView158);
        c10r7.setOnClickListener(this);
        ImageView c10r8 = (ImageView)activity.findViewById(R.id.imageView159);
        c10r8.setOnClickListener(this);
        ImageView c10r9 = (ImageView)activity.findViewById(R.id.imageView160);
        c10r9.setOnClickListener(this);
        ImageView c10r10 = (ImageView)activity.findViewById(R.id.imageView161);
        c10r10.setOnClickListener(this);
        ImageView c10r11 = (ImageView)activity.findViewById(R.id.imageView162);
        c10r11.setOnClickListener(this);
        ImageView c10r12 = (ImageView)activity.findViewById(R.id.imageView163);
        c10r12.setOnClickListener(this);
        ImageView c10r13 = (ImageView)activity.findViewById(R.id.imageView164);
        c10r13.setOnClickListener(this);
        ImageView c10r14 = (ImageView)activity.findViewById(R.id.imageView165);
        c10r14.setOnClickListener(this);

        //column12
        ImageView c11r0 = (ImageView)activity.findViewById(R.id.imageView166);
        c11r0.setOnClickListener(this);
        ImageView c11r1 = (ImageView)activity.findViewById(R.id.imageView167);
        c11r1.setOnClickListener(this);
        ImageView c11r2 = (ImageView)activity.findViewById(R.id.imageView168);
        c11r2.setOnClickListener(this);
        ImageView c11r3 = (ImageView)activity.findViewById(R.id.imageView169);
        c11r3.setOnClickListener(this);
        ImageView c11r4 = (ImageView)activity.findViewById(R.id.imageView170);
        c11r4.setOnClickListener(this);
        ImageView c11r5 = (ImageView)activity.findViewById(R.id.imageView171);
        c11r5.setOnClickListener(this);
        ImageView c11r6 = (ImageView)activity.findViewById(R.id.imageView172);
        c11r6.setOnClickListener(this);
        ImageView c11r7 = (ImageView)activity.findViewById(R.id.imageView173);
        c11r7.setOnClickListener(this);
        ImageView c11r8 = (ImageView)activity.findViewById(R.id.imageView174);
        c11r8.setOnClickListener(this);
        ImageView c11r9 = (ImageView)activity.findViewById(R.id.imageView175);
        c11r9.setOnClickListener(this);
        ImageView c11r10 = (ImageView)activity.findViewById(R.id.imageView176);
        c11r10.setOnClickListener(this);
        ImageView c11r11 = (ImageView)activity.findViewById(R.id.imageView177);
        c11r11.setOnClickListener(this);
        ImageView c11r12 = (ImageView)activity.findViewById(R.id.imageView178);
        c11r12.setOnClickListener(this);
        ImageView c11r13 = (ImageView)activity.findViewById(R.id.imageView179);
        c11r13.setOnClickListener(this);
        ImageView c11r14 = (ImageView)activity.findViewById(R.id.imageView180);
        c11r14.setOnClickListener(this);

        //column 13
        ImageView c12r0 = (ImageView)activity.findViewById(R.id.imageView181);
        c12r0.setOnClickListener(this);
        ImageView c12r1 = (ImageView)activity.findViewById(R.id.imageView182);
        c12r1.setOnClickListener(this);
        ImageView c12r2 = (ImageView)activity.findViewById(R.id.imageView183);
        c12r2.setOnClickListener(this);
        ImageView c12r3 = (ImageView)activity.findViewById(R.id.imageView184);
        c12r3.setOnClickListener(this);
        ImageView c12r4 = (ImageView)activity.findViewById(R.id.imageView185);
        c12r4.setOnClickListener(this);
        ImageView c12r5 = (ImageView)activity.findViewById(R.id.imageView186);
        c12r5.setOnClickListener(this);
        ImageView c12r6 = (ImageView)activity.findViewById(R.id.imageView187);
        c12r6.setOnClickListener(this);
        ImageView c12r7 = (ImageView)activity.findViewById(R.id.imageView188);
        c12r7.setOnClickListener(this);
        ImageView c12r8 = (ImageView)activity.findViewById(R.id.imageView189);
        c12r8.setOnClickListener(this);
        ImageView c12r9 = (ImageView)activity.findViewById(R.id.imageView190);
        c12r9.setOnClickListener(this);
        ImageView c12r10 = (ImageView)activity.findViewById(R.id.imageView191);
        c12r10.setOnClickListener(this);
        ImageView c12r11 = (ImageView)activity.findViewById(R.id.imageView192);
        c12r11.setOnClickListener(this);
        ImageView c12r12 = (ImageView)activity.findViewById(R.id.imageView193);
        c12r12.setOnClickListener(this);
        ImageView c12r13 = (ImageView)activity.findViewById(R.id.imageView194);
        c12r13.setOnClickListener(this);
        ImageView c12r14 = (ImageView)activity.findViewById(R.id.imageView195);
        c12r14.setOnClickListener(this);

        //column 14
        ImageView c13r0 = (ImageView)activity.findViewById(R.id.imageView196);
        c13r0.setOnClickListener(this);
        ImageView c13r1 = (ImageView)activity.findViewById(R.id.imageView197);
        c13r1.setOnClickListener(this);
        ImageView c13r2 = (ImageView)activity.findViewById(R.id.imageView198);
        c13r2.setOnClickListener(this);
        ImageView c13r3 = (ImageView)activity.findViewById(R.id.imageView199);
        c13r3.setOnClickListener(this);
        ImageView c13r4 = (ImageView)activity.findViewById(R.id.imageView200);
        c13r4.setOnClickListener(this);
        ImageView c13r5 = (ImageView)activity.findViewById(R.id.imageView201);
        c13r5.setOnClickListener(this);
        ImageView c13r6 = (ImageView)activity.findViewById(R.id.imageView202);
        c13r6.setOnClickListener(this);
        ImageView c13r7 = (ImageView)activity.findViewById(R.id.imageView203);
        c13r7.setOnClickListener(this);
        ImageView c13r8 = (ImageView)activity.findViewById(R.id.imageView204);
        c13r8.setOnClickListener(this);
        ImageView c13r9 = (ImageView)activity.findViewById(R.id.imageView205);
        c13r9.setOnClickListener(this);
        ImageView c13r10 = (ImageView)activity.findViewById(R.id.imageView206);
        c13r10.setOnClickListener(this);
        ImageView c13r11 = (ImageView)activity.findViewById(R.id.imageView207);
        c13r11.setOnClickListener(this);
        ImageView c13r12 = (ImageView)activity.findViewById(R.id.imageView208);
        c13r12.setOnClickListener(this);
        ImageView c13r13 = (ImageView)activity.findViewById(R.id.imageView209);
        c13r13.setOnClickListener(this);
        ImageView c13r14 = (ImageView)activity.findViewById(R.id.imageView210);
        c13r14.setOnClickListener(this);

        //column 15
        ImageView c14r0 = (ImageView)activity.findViewById(R.id.imageView211);
        c14r0.setOnClickListener(this);
        ImageView c14r1 = (ImageView)activity.findViewById(R.id.imageView212);
        c14r1.setOnClickListener(this);
        ImageView c14r2 = (ImageView)activity.findViewById(R.id.imageView213);
        c14r2.setOnClickListener(this);
        ImageView c14r3 = (ImageView)activity.findViewById(R.id.imageView214);
        c14r3.setOnClickListener(this);
        ImageView c14r4 = (ImageView)activity.findViewById(R.id.imageView215);
        c14r4.setOnClickListener(this);
        ImageView c14r5 = (ImageView)activity.findViewById(R.id.imageView216);
        c14r5.setOnClickListener(this);
        ImageView c14r6 = (ImageView)activity.findViewById(R.id.imageView217);
        c14r6.setOnClickListener(this);
        ImageView c14r7 = (ImageView)activity.findViewById(R.id.imageView218);
        c14r7.setOnClickListener(this);
        ImageView c14r8 = (ImageView)activity.findViewById(R.id.imageView219);
        c14r8.setOnClickListener(this);
        ImageView c14r9 = (ImageView)activity.findViewById(R.id.imageView220);
        c14r9.setOnClickListener(this);
        ImageView c14r10 = (ImageView)activity.findViewById(R.id.imageView221);
        c14r10.setOnClickListener(this);
        ImageView c14r11 = (ImageView)activity.findViewById(R.id.imageView222);
        c14r11.setOnClickListener(this);
        ImageView c14r12 = (ImageView)activity.findViewById(R.id.imageView223);
        c14r12.setOnClickListener(this);
        ImageView c14r13 = (ImageView)activity.findViewById(R.id.imageView224);
        c14r13.setOnClickListener(this);
        ImageView c14r14 = (ImageView)activity.findViewById(R.id.imageView225);
        c14r14.setOnClickListener(this);

       // surfaceView = (ScrabbleSurfaceView)activity.findViewById(R.id.scrabbleSurfaceView);

    }
    public void onClick(View button) {

        boolean isVertical;

        if (button.getId() == R.id.exchange) {
            toScrabbleLetter(letters);
            if(letter == null){
                return;
            }
            Exchange exchange = new Exchange(this, letter);
            game.sendAction(exchange);
            //have to delete arrayLists otherwise they are stored and ruin future words
            tempInts.clear();
            tempXCords.clear();
            tempYCords.clear();
            letters.clear();

        }
        else if(button.getId() == R.id.pass){
            Pass pass = new Pass(this);
            game.sendAction(pass);
        }
        else if(button.getId() == R.id.playword){
            toScrabbleLetter(letters);
            getSpecialArray(tempInts);

            isVertical = scrabbleCopy.isVertical(getXCoord(tempInts), getYCoord(tempInts));
            int myTempXCoords[] = getXCoord(tempInts);
            int myTempYCoords[] = getYCoord(tempInts);
           // if(isFirst){
               //fillWord();

            //}

            PlayWord playWord = new PlayWord(this, letter, specialTileArray,getXCoord(tempInts), getYCoord(tempInts), isVertical);
            game.sendAction(playWord);
            isFirst = true;
            //have to delete arrayLists otherwise they are stored and ruin future words
            tempInts.clear();
            tempXCords.clear();
            tempYCords.clear();
            letters.clear();
        }
        else if(button.getId() == R.id.exitGame){
            ExitGame exitGame = new ExitGame(this);
            game.sendAction(exitGame);
        }
        else if(button.getId() == R.id.aButton || button.getId() == R.id.bButton ||
                button.getId() == R.id.cButton || button.getId() == R.id.dButton ||
                button.getId() == R.id.eButton || button.getId() == R.id.fButton ||
                button.getId() == R.id.gButton){
            char myChar = getCharacter(button);
            String myString = Character.toString(myChar);
            letters.add(myString);
        }
        else if(button instanceof ImageView){
            tempInts.add(getSquare(button));
            tempXCords.add(getSquare(button) / 15);
            tempYCords.add(getSquare(button) % 15);
            //surfaceView.invalidate();
        }

    }

    /**
     * Is called when playword is called in order to comprehensively fill various arrays with the
     * missing letters' information
     *
     *
     */
    public void fillWord(){
        int index = -1;
        int missingVal = -1;
        boolean xchanging;
        int prevX = tempXCords.get(0);
        int prevY = tempYCords.get(0);

        if(prevX == tempXCords.get(1) ){
            xchanging = true;
        }
        else{
            xchanging = false;
        }
        //finds the position in the array that needs to be found
        for(int i = 1; i < tempInts.size(); i++){
            if(xchanging){
                if(prevX != (1 + tempXCords.get(i))){
                    index = i;
                    missingVal = 1  + prevX;
                }
                else{
                    prevX = tempXCords.get(i);
                }
            }
            else{
                if(prevY != (1 + tempYCords.get(i))){
                    index = 1;
                    missingVal = 1  + prevY;
                }
                else{
                    prevY = tempYCords.get(i);
                }
            }
        }

        //gets the missing x and y position of the word
        if(xchanging){
            tempXCords.add(index, missingVal);
        }
        else{
            tempYCords.add(index, missingVal);
        }

        //gets the missing letter and adds it to the array
        if(xchanging){
            char c  = getCharacter( getImageView(missingVal, tempYCords.get(0)) );
            letters.add(index, "" + c);
        }
        else{
            char c  = getCharacter( getImageView(tempXCords.get(0), missingVal) );
            letters.add(index, "" + c);
        }

    }
    //updates the hand as it's played
    public void tmpAdd(View handButton){
        ScrabbleLetter[][] board = scrabbleCopy.getBoard();
        ScrabbleLetter[] hand = scrabbleCopy.getPlayer1Hand();

        scrabbleCopy.setPlayer1Hand(hand);
        scrabbleCopy.setBoard(board);

    }
    public int[] getXCoord(ArrayList<Integer> tempInts){
        int[] xArray;
        xArray = new int[tempInts.size()];
        for(int i = 0; i < tempInts.size(); i++){
            xArray[i] = tempInts.get(i)/15;
        }
        return xArray;
    }
    public int[] getYCoord(ArrayList<Integer> tempInts){
        int[] yArray;
        yArray = new int[tempInts.size()];
        for(int i = 0; i < tempInts.size(); i++){
            yArray[i] = (tempInts.get(i) % 15) - 1;
        }
        return yArray;
    }
    public int[] getIntVal(ArrayList<Integer> tempInts){
        int[] tempArray = new int[tempInts.size()];
        for(int i = 0; i < tempInts.size(); i++){
            tempArray[i] = tempInts.get(i);
        }
        return tempArray;
    }

    public ScrabbleLetter[] toScrabbleLetter(ArrayList<String> arrs){
        this.letter = new ScrabbleLetter[arrs.size()];

        for(int i = 0; i < arrs.size(); i++ ){
            ScrabbleLetter d = new ScrabbleLetter(arrs.get(i).charAt(0));
            this.letter[i] = d;
        }

        return letter;
    }

    public int[] getSpecialArray(ArrayList<Integer> tempInts){
        specialTileArray = new int[tempInts.size()];

        for(int i = 0; i < tempInts.size(); i++){
            int type = getSpecialTile(tempInts.get(i));
            specialTileArray[i] = type;
        }
        return specialTileArray;
    }

    public int getSpecialTile(int tile){
        switch (tile){
            case 1:
            case 8:
            case 15:
            case 106:
            case 120:
            case 121:
            case 128:
            case 225:
                return 1;
            case 17:
            case 29:
            case 33:
            case 43:
            case 49:
            case 57:
            case 65:
            case 71:
            case 155:
            case 161:
            case 169:
            case 177:
            case 183:
            case 193:
            case 197:
            case 209:
                return 2;
            case 21:
            case 25:
            case 77:
            case 81:
            case 85:
            case 89:
            case 137:
            case 141:
            case 145:
            case 149:
            case 201:
            case 205:
                return 3;
            case 37:
            case 39:
            case 53:
            case 93:
            case 97:
            case 99:
            case 103:
            case 109:
            case 117:
            case 123:
            case 127:
            case 129:
            case 133:
            case 173:
            case 187:
            case 189:
                return 4;
            default:
                return 0;
        }

    }


    public char getCharacter(View view) {

        ScrabbleLetter[] myHand = scrabbleCopy.getPlayer1Hand();
        switch (view.getId()) {

            case R.id.aButton:
                return myHand[0].getLetter();
            case R.id.bButton:
                return myHand[1].getLetter();
            case R.id.cButton:
                return myHand[2].getLetter();
            case R.id.dButton:
                return myHand[3].getLetter();
            case R.id.gButton:
                return myHand[4].getLetter();
            case R.id.eButton:
                return myHand[5].getLetter();
            case R.id.fButton:
                return myHand[6].getLetter();
                default:
                return ' ';
        }
    }
    public int getSquare(View view){
        switch(view.getId()){
            case R.id.imageView:
                return 1;
            case R.id.imageView2:
                return 2;
            case R.id.imageView3:
                return 3;
            case R.id.imageView4:
                return 4;
            case R.id.imageView5:
                return 5;
            case R.id.imageView6:
                return 6;
            case R.id.imageView7:
                return 7;
            case R.id.imageView8:
                return 8;
            case R.id.imageView9:
                return 9;
            case R.id.imageView10:
                return 10;
            case R.id.imageView11:
                return 11;
            case R.id.imageView12:
                return 12;
            case R.id.imageView13:
                return 13;
            case R.id.imageView14:
                return 14;
            case R.id.imageView15:
                return 15;
            case R.id.imageView16:
                return 16;
            case R.id.imageView17: return 17;
            case R.id.imageView18: return 18;
            case R.id.imageView19: return 19;
            case R.id.imageView20: return 20;
            case R.id.imageView21: return 21;
            case R.id.imageView22: return 22;
            case R.id.imageView23	: return	23	;
            case R.id.imageView24	: return	24	;
            case R.id.imageView25	: return	25	;
            case R.id.imageView26	: return	26	;
            case R.id.imageView27	: return	27	;
            case R.id.imageView28	: return	28	;
            case R.id.imageView29	: return	29	;
            case R.id.imageView30	: return	30	;
            case R.id.imageView31	: return	31	;
            case R.id.imageView32	: return	32	;
            case R.id.imageView33	: return	33	;
            case R.id.imageView34	: return	34	;
            case R.id.imageView35	: return	35	;
            case R.id.imageView36	: return	36	;
            case R.id.imageView37	: return	37	;
            case R.id.imageView38	: return	38	;
            case R.id.imageView39	: return	39	;
            case R.id.imageView40	: return	40	;
            case R.id.imageView41	: return	41	;
            case R.id.imageView42	: return	42	;
            case R.id.imageView43	: return	43	;
            case R.id.imageView44	: return	44	;
            case R.id.imageView45	: return	45	;
            case R.id.imageView46	: return	46	;
            case R.id.imageView47	: return	47	;
            case R.id.imageView48	: return	48	;
            case R.id.imageView49	: return	49	;
            case R.id.imageView50	: return	50	;
            case R.id.imageView51	: return	51	;
            case R.id.imageView52	: return	52	;
            case R.id.imageView53	: return	53	;
            case R.id.imageView54	: return	54	;
            case R.id.imageView55	: return	55	;
            case R.id.imageView56	: return	56	;
            case R.id.imageView57	: return	57	;
            case R.id.imageView58	: return	58	;
            case R.id.imageView59	: return	59	;
            case R.id.imageView60	: return	60	;
            case R.id.imageView61	: return	61	;
            case R.id.imageView62	: return	62	;
            case R.id.imageView63	: return	63	;
            case R.id.imageView64	: return	64	;
            case R.id.imageView65	: return	65	;
            case R.id.imageView66	: return	66	;
            case R.id.imageView67	: return	67	;
            case R.id.imageView68	: return	68	;
            case R.id.imageView69	: return	69	;
            case R.id.imageView70	: return	70	;
            case R.id.imageView71	: return	71	;
            case R.id.imageView72	: return	72	;
            case R.id.imageView73	: return	73	;
            case R.id.imageView74	: return	74	;
            case R.id.imageView75	: return	75	;
            case R.id.imageView76	: return	76	;
            case R.id.imageView77	: return	77	;
            case R.id.imageView78	: return	78	;
            case R.id.imageView79	: return	79	;
            case R.id.imageView80	: return	80	;
            case R.id.imageView81	: return	81	;
            case R.id.imageView82	: return	82	;
            case R.id.imageView83	: return	83	;
            case R.id.imageView84	: return	84	;
            case R.id.imageView85	: return	85	;
            case R.id.imageView86	: return	86	;
            case R.id.imageView87	: return	87	;
            case R.id.imageView88	: return	88	;
            case R.id.imageView89	: return	89	;
            case R.id.imageView90	: return	90	;
            case R.id.imageView91	: return	91	;
            case R.id.imageView92	: return	92	;
            case R.id.imageView93	: return	93	;
            case R.id.imageView94	: return	94	;
            case R.id.imageView95	: return	95	;
            case R.id.imageView96	: return	96	;
            case R.id.imageView97	: return	97	;
            case R.id.imageView98	: return	98	;
            case R.id.imageView99	: return	99	;
            case R.id.imageView100	: return	100	;
            case R.id.imageView101	: return	101	;
            case R.id.imageView102	: return	102	;
            case R.id.imageView103	: return	103	;
            case R.id.imageView104	: return	104	;
            case R.id.imageView105	: return	105	;
            case R.id.imageView106	: return	106	;
            case R.id.imageView107	: return	107	;
            case R.id.imageView108	: return	108	;
            case R.id.imageView109	: return	109	;
            case R.id.imageView110	: return	110	;
            case R.id.imageView111	: return	111	;
            case R.id.imageView112	: return	112	;
            case R.id.imageView113	: return	113	;
            case R.id.imageView114	: return	114	;
            case R.id.imageView115	: return	115	;
            case R.id.imageView116	: return	116	;
            case R.id.imageView117	: return	117	;
            case R.id.imageView118	: return	118	;
            case R.id.imageView119	: return	119	;
            case R.id.imageView120	: return	120	;
            case R.id.imageView121	: return	121	;
            case R.id.imageView122	: return	122	;
            case R.id.imageView123	: return	123	;
            case R.id.imageView124	: return	124	;
            case R.id.imageView125	: return	125	;
            case R.id.imageView126	: return	126	;
            case R.id.imageView127	: return	127	;
            case R.id.imageView128	: return	128	;
            case R.id.imageView129	: return	129	;
            case R.id.imageView130	: return	130	;
            case R.id.imageView131	: return	131	;
            case R.id.imageView132	: return	132	;
            case R.id.imageView133	: return	133	;
            case R.id.imageView134	: return	134	;
            case R.id.imageView135	: return	135	;
            case R.id.imageView136	: return	136	;
            case R.id.imageView137	: return	137	;
            case R.id.imageView138	: return	138	;
            case R.id.imageView139	: return	139	;
            case R.id.imageView140	: return	140	;
            case R.id.imageView141	: return	141	;
            case R.id.imageView142	: return	142	;
            case R.id.imageView143	: return	143	;
            case R.id.imageView144	: return	144	;
            case R.id.imageView145	: return	145	;
            case R.id.imageView146	: return	146	;
            case R.id.imageView147	: return	147	;
            case R.id.imageView148	: return	148	;
            case R.id.imageView149	: return	149	;
            case R.id.imageView150	: return	150	;
            case R.id.imageView151	: return	151	;
            case R.id.imageView152	: return	152	;
            case R.id.imageView153	: return	153	;
            case R.id.imageView154	: return	154	;
            case R.id.imageView155	: return	155	;
            case R.id.imageView156	: return	156	;
            case R.id.imageView157	: return	157	;
            case R.id.imageView158	: return	158	;
            case R.id.imageView159	: return	159	;
            case R.id.imageView160	: return	160	;
            case R.id.imageView161	: return	161	;
            case R.id.imageView162	: return	162	;
            case R.id.imageView163	: return	163	;
            case R.id.imageView164	: return	164	;
            case R.id.imageView165	: return	165	;
            case R.id.imageView166	: return	166	;
            case R.id.imageView167	: return	167	;
            case R.id.imageView168	: return	168	;
            case R.id.imageView169	: return	169	;
            case R.id.imageView170	: return	170	;
            case R.id.imageView171	: return	171	;
            case R.id.imageView172	: return	172	;
            case R.id.imageView173	: return	173	;
            case R.id.imageView174	: return	174	;
            case R.id.imageView175	: return	175	;
            case R.id.imageView176	: return	176	;
            case R.id.imageView177	: return	177	;
            case R.id.imageView178	: return	178	;
            case R.id.imageView179	: return	179	;
            case R.id.imageView180	: return	180	;
            case R.id.imageView181	: return	181	;
            case R.id.imageView182	: return	182	;
            case R.id.imageView183	: return	183	;
            case R.id.imageView184	: return	184	;
            case R.id.imageView185	: return	185	;
            case R.id.imageView186	: return	186	;
            case R.id.imageView187	: return	187	;
            case R.id.imageView188	: return	188	;
            case R.id.imageView189	: return	189	;
            case R.id.imageView190	: return	190	;
            case R.id.imageView191	: return	191	;
            case R.id.imageView192	: return	192	;
            case R.id.imageView193	: return	193	;
            case R.id.imageView194	: return	194	;
            case R.id.imageView195	: return	195	;
            case R.id.imageView196	: return	196	;
            case R.id.imageView197	: return	197	;
            case R.id.imageView198	: return	198	;
            case R.id.imageView199	: return	199	;
            case R.id.imageView200	: return	200	;
            case R.id.imageView201	: return	201	;
            case R.id.imageView202	: return	202	;
            case R.id.imageView203	: return	203	;
            case R.id.imageView204	: return	204	;
            case R.id.imageView205	: return	205	;
            case R.id.imageView206	: return	206	;
            case R.id.imageView207	: return	207	;
            case R.id.imageView208	: return	208	;
            case R.id.imageView209	: return	209	;
            case R.id.imageView210	: return	210	;
            case R.id.imageView211	: return	211	;
            case R.id.imageView212	: return	212	;
            case R.id.imageView213	: return	213	;
            case R.id.imageView214	: return	214	;
            case R.id.imageView215	: return	215	;
            case R.id.imageView216	: return	216	;
            case R.id.imageView217	: return	217	;
            case R.id.imageView218	: return	218	;
            case R.id.imageView219	: return	219	;
            case R.id.imageView220	: return	220	;
            case R.id.imageView221	: return	221	;
            case R.id.imageView222	: return	222	;
            case R.id.imageView223	: return	223	;
            case R.id.imageView224	: return	224	;
            case R.id.imageView225	: return	225	;
            default:
                return -1;
        }
    }
    public void drawHand(ScrabbleState state){
        ScrabbleLetter[] hand = state.getPlayer1Hand();
        ImageView img;


        for(int i = 0; i < 7; i++){
            img = getHandImageView(i);
            if(img == null){
                int x = 2;
            }
            img.setImageDrawable(getDrawableLetter(hand[i].getLetter()));
        }
    }

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
    }
    public Drawable getDrawableLetter(char letter){

        switch(letter){
            case 'a':
                return myActivity.getDrawable(R.drawable.afinal);
            case 'b':
                return myActivity.getResources().getDrawable(R.drawable.bfinal);
            case 'c':
                return myActivity.getResources().getDrawable(R.drawable.cfinal);
            case 'd':
                return myActivity.getResources().getDrawable(R.drawable.dfinal);
            case 'e':
                return myActivity.getResources().getDrawable(R.drawable.efinal);
            case 'f':
                return myActivity.getResources().getDrawable(R.drawable.ffinal);
            case 'g':
                return myActivity.getResources().getDrawable(R.drawable.gfinal);
            case 'h':
                return myActivity.getResources().getDrawable(R.drawable.hfinal);
            case 'i':
                return myActivity.getResources().getDrawable(R.drawable.ifinal);
            case 'j':
                return myActivity.getResources().getDrawable(R.drawable.jfinal);
            case 'k':
                return myActivity.getResources().getDrawable(R.drawable.kfinal);
            case 'l':
                return myActivity.getResources().getDrawable(R.drawable.lfinal);
            case 'm':
                return myActivity.getResources().getDrawable(R.drawable.mfinal);
            case 'n':
                return myActivity.getResources().getDrawable(R.drawable.nfinal);
            case 'o':
                return myActivity.getResources().getDrawable(R.drawable.ofinal);
            case 'p':
                return myActivity.getResources().getDrawable(R.drawable.pfinal);
            case 'q':
                return myActivity.getResources().getDrawable(R.drawable.qfinal);
            case 'r':
                return myActivity.getResources().getDrawable(R.drawable.rfinal);
            case 's':
                return myActivity.getResources().getDrawable(R.drawable.sfinal);
            case 't':
                return myActivity.getResources().getDrawable(R.drawable.tfinal);
            case 'u':
                return myActivity.getResources().getDrawable(R.drawable.ufinal);
            case 'v':
                return myActivity.getResources().getDrawable(R.drawable.vfinal);
            case 'w':
                return myActivity.getResources().getDrawable(R.drawable.wfinal);
            case 'x':
                return myActivity.getResources().getDrawable(R.drawable.xfinal);
            case 'y':
                return myActivity.getResources().getDrawable(R.drawable.yfinal);
            case 'z':
                return myActivity.getResources().getDrawable(R.drawable.zfinal);
            default:
                return myActivity.getResources().getDrawable(R.drawable.backgroundsquare);
        }

    }
    public void drawBoard(ScrabbleState state){
        ScrabbleLetter[][] board = state.getBoard();
        ImageView img;

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                img = getImageView(r,c);
                //https://stackoverflow.com/questions/3144940/set-imageview-width-and-height-programmatically
                int width = 60;
                int height = 60;
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
                img.setLayoutParams(parms);

                if(board[r][c].getLetter() == ' '){
                    img.setImageDrawable(img.getDrawable());
                }
                else{
                    img.setImageDrawable(getDrawableLetter(board[r][c].getLetter()));
                }

            }
        }
    }

    public ImageView getImageView(int row, int col){
        //this is going to be massive and messy. Sorry.
        if(row == 0){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView2);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView3);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView4);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView5);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView6);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView7);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView8);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView9);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView10);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView11);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView12);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView13);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView14);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView15);
            }
            else{
                return null;
            }

        }
        else if (row == 1 ){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView16);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView17);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView18);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView19);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView20);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView21);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView22);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView23);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView24);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView25);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView26);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView27);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView28);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView29);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView30);
            }
            else{
                return null;
            }
        }
        else if(row == 2){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView31);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView32);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView33);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView34);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView35);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView36);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView37);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView38);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView39);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView40);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView41);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView42);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView43);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView44);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView45);
            }
            else{
                return null;
            }
        }
        else if(row == 3){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView46);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView47);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView48);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView49);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView50);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView51);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView52);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView53);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView54);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView55);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView56);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView57);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView58);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView59);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView60);
            }
            else{
                return null;
            }
        }
        else if(row == 4){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView61);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView62);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView63);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView64);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView65);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView66);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView67);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView68);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView69);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView70);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView71);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView72);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView73);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView74);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView75);
            }
            else{
                return null;
            }
        }
        else if(row == 5){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView76);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView77);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView78);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView79);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView80);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView81);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView82);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView83);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView84);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView85);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView86);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView87);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView88);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView89);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView90);
            }
            else{
                return null;
            }
        }
        else if (row == 6){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView91);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView92);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView93);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView94);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView95);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView96);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView97);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView98);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView99);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView100);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView101);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView102);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView103);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView104);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView105);
            }
            else{
                return null;
            }
        }
        else if (row == 7){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView106);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView107);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView108);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView109);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView110);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView111);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView112);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView113);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView114);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView115);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView116);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView117);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView118);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView119);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView120);
            }
            else{
                return null;
            }
        }
        else if (row == 8){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView121);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView122);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView123);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView124);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView125);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView126);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView127);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView128);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView129);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView130);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView131);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView132);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView133);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView134);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView135);
            }
            else{
                return null;
            }
        }
        else if (row == 9){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView136);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView137);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView138);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView139);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView140);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView141);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView142);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView143);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView144);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView145);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView146);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView147);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView148);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView149);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView150);
            }
            else{
                return null;
            }
        }
        else if (row == 10){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView151);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView152);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView153);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView154);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView155);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView156);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView157);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView158);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView159);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView160);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView161);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView162);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView163);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView164);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView165);
            }
            else{
                return null;
            }
        }
        else if (row == 11){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView166);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView167);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView168);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView169);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView170);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView171);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView172);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView173);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView174);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView175);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView176);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView177);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView178);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView79);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView180);
            }
            else{
                return null;
            }
        }
        else if (row == 12){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView181);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView182);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView183);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView184);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView185);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView186);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView187);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView188);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView189);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView190);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView191);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView192);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView193);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView194);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView195);
            }
            else{
                return null;
            }
        }
        else if (row == 13){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView196);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView197);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView198);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView199);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView200);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView201);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView202);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView203);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView204);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView205);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView206);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView207);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView208);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView209);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView210);
            }
            else{
                return null;
            }
        }
        else if (row == 14){
            if(col == 0){
                return myActivity.findViewById(R.id.imageView211);
            }
            else if (col == 1){
                return myActivity.findViewById(R.id.imageView212);
            }
            else if (col == 2){
                return myActivity.findViewById(R.id.imageView213);
            }
            else if (col == 3){
                return myActivity.findViewById(R.id.imageView214);
            }
            else if (col == 4){
                return myActivity.findViewById(R.id.imageView215);
            }
            else if (col == 5){
                return myActivity.findViewById(R.id.imageView216);
            }
            else if (col ==6){
                return myActivity.findViewById(R.id.imageView217);
            }
            else if (col == 7){
                return myActivity.findViewById(R.id.imageView218);
            }
            else if (col == 8){
                return myActivity.findViewById(R.id.imageView219);
            }
            else if (col == 9){
                return myActivity.findViewById(R.id.imageView220);
            }
            else if (col == 10){
                return myActivity.findViewById(R.id.imageView221);
            }
            else if (col == 11){
                return myActivity.findViewById(R.id.imageView222);
            }
            else if (col == 12){
                return myActivity.findViewById(R.id.imageView223);
            }
            else if (col == 13){
                return myActivity.findViewById(R.id.imageView224);
            }
            else if (col == 14){
                return myActivity.findViewById(R.id.imageView225);
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

}
