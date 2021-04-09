package com.example.thescrabblegame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScrabbleSurfaceView extends SurfaceView implements View.OnClickListener, TextView.OnEditorActionListener{

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
        int score = state.getPlayer1Score();
        ScrabbleLetter[][] board = state.getBoard();
        ImageView img;

        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length; c++){
                img = getImageView(r,c);
                img.setImageDrawable(getDrawableLetter(board[r][c].getLetter()));
                score += board[r][c].getPoints();
            }
        }

        invalidate();
    }

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

    public ImageView getImageView(int row, int col){
        //this is going to be massive and messy. Sorry.
        if(row == 0){
            if(col == 0){
                return findViewById(R.id.imageView);
            }
            else if (col == 1){
                return findViewById(R.id.imageView2);
            }
            else if (col == 2){
                return findViewById(R.id.imageView3);
            }
            else if (col == 3){
                return findViewById(R.id.imageView4);
            }
            else if (col == 4){
                return findViewById(R.id.imageView5);
            }
            else if (col == 5){
                return findViewById(R.id.imageView6);
            }
            else if (col ==6){
                return findViewById(R.id.imageView7);
            }
            else if (col == 7){
                return findViewById(R.id.imageView8);
            }
            else if (col == 8){
                return findViewById(R.id.imageView9);
            }
            else if (col == 9){
                return findViewById(R.id.imageView10);
            }
            else if (col == 10){
                return findViewById(R.id.imageView11);
            }
            else if (col == 11){
                return findViewById(R.id.imageView12);
            }
            else if (col == 12){
                return findViewById(R.id.imageView13);
            }
            else if (col == 13){
                return findViewById(R.id.imageView14);
            }
            else if (col == 14){
                return findViewById(R.id.imageView15);
            }
            else{
                return null;
            }

        }
        else if (row == 1 ){
            if(col == 0){
                return findViewById(R.id.imageView16);
            }
            else if (col == 1){
                return findViewById(R.id.imageView17);
            }
            else if (col == 2){
                return findViewById(R.id.imageView18);
            }
            else if (col == 3){
                return findViewById(R.id.imageView19);
            }
            else if (col == 4){
                return findViewById(R.id.imageView20);
            }
            else if (col == 5){
                return findViewById(R.id.imageView21);
            }
            else if (col ==6){
                return findViewById(R.id.imageView22);
            }
            else if (col == 7){
                return findViewById(R.id.imageView23);
            }
            else if (col == 8){
                return findViewById(R.id.imageView24);
            }
            else if (col == 9){
                return findViewById(R.id.imageView25);
            }
            else if (col == 10){
                return findViewById(R.id.imageView26);
            }
            else if (col == 11){
                return findViewById(R.id.imageView27);
            }
            else if (col == 12){
                return findViewById(R.id.imageView28);
            }
            else if (col == 13){
                return findViewById(R.id.imageView29);
            }
            else if (col == 14){
                return findViewById(R.id.imageView30);
            }
            else{
                return null;
            }
        }
        else if(row == 2){
            if(col == 0){
                return findViewById(R.id.imageView31);
            }
            else if (col == 1){
                return findViewById(R.id.imageView32);
            }
            else if (col == 2){
                return findViewById(R.id.imageView33);
            }
            else if (col == 3){
                return findViewById(R.id.imageView34);
            }
            else if (col == 4){
                return findViewById(R.id.imageView35);
            }
            else if (col == 5){
                return findViewById(R.id.imageView36);
            }
            else if (col ==6){
                return findViewById(R.id.imageView37);
            }
            else if (col == 7){
                return findViewById(R.id.imageView38);
            }
            else if (col == 8){
                return findViewById(R.id.imageView39);
            }
            else if (col == 9){
                return findViewById(R.id.imageView40);
            }
            else if (col == 10){
                return findViewById(R.id.imageView41);
            }
            else if (col == 11){
                return findViewById(R.id.imageView42);
            }
            else if (col == 12){
                return findViewById(R.id.imageView43);
            }
            else if (col == 13){
                return findViewById(R.id.imageView44);
            }
            else if (col == 14){
                return findViewById(R.id.imageView45);
            }
            else{
                return null;
            }
        }
        else if(row == 3){
            if(col == 0){
                return findViewById(R.id.imageView46);
            }
            else if (col == 1){
                return findViewById(R.id.imageView47);
            }
            else if (col == 2){
                return findViewById(R.id.imageView48);
            }
            else if (col == 3){
                return findViewById(R.id.imageView49);
            }
            else if (col == 4){
                return findViewById(R.id.imageView50);
            }
            else if (col == 5){
                return findViewById(R.id.imageView51);
            }
            else if (col ==6){
                return findViewById(R.id.imageView52);
            }
            else if (col == 7){
                return findViewById(R.id.imageView53);
            }
            else if (col == 8){
                return findViewById(R.id.imageView54);
            }
            else if (col == 9){
                return findViewById(R.id.imageView55);
            }
            else if (col == 10){
                return findViewById(R.id.imageView56);
            }
            else if (col == 11){
                return findViewById(R.id.imageView57);
            }
            else if (col == 12){
                return findViewById(R.id.imageView58);
            }
            else if (col == 13){
                return findViewById(R.id.imageView59);
            }
            else if (col == 14){
                return findViewById(R.id.imageView60);
            }
            else{
                return null;
            }
        }
        else if(row == 4){
            if(col == 0){
                return findViewById(R.id.imageView61);
            }
            else if (col == 1){
                return findViewById(R.id.imageView62);
            }
            else if (col == 2){
                return findViewById(R.id.imageView63);
            }
            else if (col == 3){
                return findViewById(R.id.imageView64);
            }
            else if (col == 4){
                return findViewById(R.id.imageView65);
            }
            else if (col == 5){
                return findViewById(R.id.imageView66);
            }
            else if (col ==6){
                return findViewById(R.id.imageView67);
            }
            else if (col == 7){
                return findViewById(R.id.imageView68);
            }
            else if (col == 8){
                return findViewById(R.id.imageView69);
            }
            else if (col == 9){
                return findViewById(R.id.imageView70);
            }
            else if (col == 10){
                return findViewById(R.id.imageView71);
            }
            else if (col == 11){
                return findViewById(R.id.imageView72);
            }
            else if (col == 12){
                return findViewById(R.id.imageView73);
            }
            else if (col == 13){
                return findViewById(R.id.imageView74);
            }
            else if (col == 14){
                return findViewById(R.id.imageView75);
            }
            else{
                return null;
            }
        }
        else if(row == 5){
            if(col == 0){
                return findViewById(R.id.imageView76);
            }
            else if (col == 1){
                return findViewById(R.id.imageView77);
            }
            else if (col == 2){
                return findViewById(R.id.imageView78);
            }
            else if (col == 3){
                return findViewById(R.id.imageView79);
            }
            else if (col == 4){
                return findViewById(R.id.imageView80);
            }
            else if (col == 5){
                return findViewById(R.id.imageView81);
            }
            else if (col ==6){
                return findViewById(R.id.imageView82);
            }
            else if (col == 7){
                return findViewById(R.id.imageView83);
            }
            else if (col == 8){
                return findViewById(R.id.imageView84);
            }
            else if (col == 9){
                return findViewById(R.id.imageView85);
            }
            else if (col == 10){
                return findViewById(R.id.imageView86);
            }
            else if (col == 11){
                return findViewById(R.id.imageView87);
            }
            else if (col == 12){
                return findViewById(R.id.imageView88);
            }
            else if (col == 13){
                return findViewById(R.id.imageView89);
            }
            else if (col == 14){
                return findViewById(R.id.imageView90);
            }
            else{
                return null;
            }
        }
        else if (row == 6){
            if(col == 0){
                return findViewById(R.id.imageView91);
            }
            else if (col == 1){
                return findViewById(R.id.imageView92);
            }
            else if (col == 2){
                return findViewById(R.id.imageView93);
            }
            else if (col == 3){
                return findViewById(R.id.imageView94);
            }
            else if (col == 4){
                return findViewById(R.id.imageView95);
            }
            else if (col == 5){
                return findViewById(R.id.imageView96);
            }
            else if (col ==6){
                return findViewById(R.id.imageView97);
            }
            else if (col == 7){
                return findViewById(R.id.imageView98);
            }
            else if (col == 8){
                return findViewById(R.id.imageView99);
            }
            else if (col == 9){
                return findViewById(R.id.imageView100);
            }
            else if (col == 10){
                return findViewById(R.id.imageView101);
            }
            else if (col == 11){
                return findViewById(R.id.imageView102);
            }
            else if (col == 12){
                return findViewById(R.id.imageView103);
            }
            else if (col == 13){
                return findViewById(R.id.imageView104);
            }
            else if (col == 14){
                return findViewById(R.id.imageView105);
            }
            else{
                return null;
            }
        }
        else if (row == 7){
            if(col == 0){
                return findViewById(R.id.imageView106);
            }
            else if (col == 1){
                return findViewById(R.id.imageView107);
            }
            else if (col == 2){
                return findViewById(R.id.imageView108);
            }
            else if (col == 3){
                return findViewById(R.id.imageView109);
            }
            else if (col == 4){
                return findViewById(R.id.imageView110);
            }
            else if (col == 5){
                return findViewById(R.id.imageView111);
            }
            else if (col ==6){
                return findViewById(R.id.imageView112);
            }
            else if (col == 7){
                return findViewById(R.id.imageView113);
            }
            else if (col == 8){
                return findViewById(R.id.imageView114);
            }
            else if (col == 9){
                return findViewById(R.id.imageView115);
            }
            else if (col == 10){
                return findViewById(R.id.imageView116);
            }
            else if (col == 11){
                return findViewById(R.id.imageView117);
            }
            else if (col == 12){
                return findViewById(R.id.imageView118);
            }
            else if (col == 13){
                return findViewById(R.id.imageView119);
            }
            else if (col == 14){
                return findViewById(R.id.imageView120);
            }
            else{
                return null;
            }
        }
        else if (row == 8){
            if(col == 0){
                return findViewById(R.id.imageView121);
            }
            else if (col == 1){
                return findViewById(R.id.imageView122);
            }
            else if (col == 2){
                return findViewById(R.id.imageView123);
            }
            else if (col == 3){
                return findViewById(R.id.imageView124);
            }
            else if (col == 4){
                return findViewById(R.id.imageView125);
            }
            else if (col == 5){
                return findViewById(R.id.imageView126);
            }
            else if (col ==6){
                return findViewById(R.id.imageView127);
            }
            else if (col == 7){
                return findViewById(R.id.imageView128);
            }
            else if (col == 8){
                return findViewById(R.id.imageView129);
            }
            else if (col == 9){
                return findViewById(R.id.imageView130);
            }
            else if (col == 10){
                return findViewById(R.id.imageView131);
            }
            else if (col == 11){
                return findViewById(R.id.imageView132);
            }
            else if (col == 12){
                return findViewById(R.id.imageView133);
            }
            else if (col == 13){
                return findViewById(R.id.imageView134);
            }
            else if (col == 14){
                return findViewById(R.id.imageView135);
            }
            else{
                return null;
            }
        }
        else if (row == 9){
            if(col == 0){
                return findViewById(R.id.imageView136);
            }
            else if (col == 1){
                return findViewById(R.id.imageView137);
            }
            else if (col == 2){
                return findViewById(R.id.imageView138);
            }
            else if (col == 3){
                return findViewById(R.id.imageView139);
            }
            else if (col == 4){
                return findViewById(R.id.imageView140);
            }
            else if (col == 5){
                return findViewById(R.id.imageView141);
            }
            else if (col ==6){
                return findViewById(R.id.imageView142);
            }
            else if (col == 7){
                return findViewById(R.id.imageView143);
            }
            else if (col == 8){
                return findViewById(R.id.imageView144);
            }
            else if (col == 9){
                return findViewById(R.id.imageView145);
            }
            else if (col == 10){
                return findViewById(R.id.imageView146);
            }
            else if (col == 11){
                return findViewById(R.id.imageView147);
            }
            else if (col == 12){
                return findViewById(R.id.imageView148);
            }
            else if (col == 13){
                return findViewById(R.id.imageView149);
            }
            else if (col == 14){
                return findViewById(R.id.imageView150);
            }
            else{
                return null;
            }
        }
        else if (row == 10){
            if(col == 0){
                return findViewById(R.id.imageView151);
            }
            else if (col == 1){
                return findViewById(R.id.imageView152);
            }
            else if (col == 2){
                return findViewById(R.id.imageView153);
            }
            else if (col == 3){
                return findViewById(R.id.imageView154);
            }
            else if (col == 4){
                return findViewById(R.id.imageView155);
            }
            else if (col == 5){
                return findViewById(R.id.imageView156);
            }
            else if (col ==6){
                return findViewById(R.id.imageView157);
            }
            else if (col == 7){
                return findViewById(R.id.imageView158);
            }
            else if (col == 8){
                return findViewById(R.id.imageView159);
            }
            else if (col == 9){
                return findViewById(R.id.imageView160);
            }
            else if (col == 10){
                return findViewById(R.id.imageView161);
            }
            else if (col == 11){
                return findViewById(R.id.imageView162);
            }
            else if (col == 12){
                return findViewById(R.id.imageView163);
            }
            else if (col == 13){
                return findViewById(R.id.imageView164);
            }
            else if (col == 14){
                return findViewById(R.id.imageView165);
            }
            else{
                return null;
            }
        }
        else if (row == 11){
            if(col == 0){
                return findViewById(R.id.imageView166);
            }
            else if (col == 1){
                return findViewById(R.id.imageView167);
            }
            else if (col == 2){
                return findViewById(R.id.imageView168);
            }
            else if (col == 3){
                return findViewById(R.id.imageView169);
            }
            else if (col == 4){
                return findViewById(R.id.imageView170);
            }
            else if (col == 5){
                return findViewById(R.id.imageView171);
            }
            else if (col ==6){
                return findViewById(R.id.imageView172);
            }
            else if (col == 7){
                return findViewById(R.id.imageView173);
            }
            else if (col == 8){
                return findViewById(R.id.imageView174);
            }
            else if (col == 9){
                return findViewById(R.id.imageView175);
            }
            else if (col == 10){
                return findViewById(R.id.imageView176);
            }
            else if (col == 11){
                return findViewById(R.id.imageView177);
            }
            else if (col == 12){
                return findViewById(R.id.imageView178);
            }
            else if (col == 13){
                return findViewById(R.id.imageView79);
            }
            else if (col == 14){
                return findViewById(R.id.imageView180);
            }
            else{
                return null;
            }
        }
        else if (row == 12){
            if(col == 0){
                return findViewById(R.id.imageView181);
            }
            else if (col == 1){
                return findViewById(R.id.imageView182);
            }
            else if (col == 2){
                return findViewById(R.id.imageView183);
            }
            else if (col == 3){
                return findViewById(R.id.imageView184);
            }
            else if (col == 4){
                return findViewById(R.id.imageView185);
            }
            else if (col == 5){
                return findViewById(R.id.imageView186);
            }
            else if (col ==6){
                return findViewById(R.id.imageView187);
            }
            else if (col == 7){
                return findViewById(R.id.imageView188);
            }
            else if (col == 8){
                return findViewById(R.id.imageView189);
            }
            else if (col == 9){
                return findViewById(R.id.imageView190);
            }
            else if (col == 10){
                return findViewById(R.id.imageView191);
            }
            else if (col == 11){
                return findViewById(R.id.imageView192);
            }
            else if (col == 12){
                return findViewById(R.id.imageView193);
            }
            else if (col == 13){
                return findViewById(R.id.imageView194);
            }
            else if (col == 14){
                return findViewById(R.id.imageView195);
            }
            else{
                return null;
            }
        }
        else if (row == 13){
            if(col == 0){
                return findViewById(R.id.imageView196);
            }
            else if (col == 1){
                return findViewById(R.id.imageView197);
            }
            else if (col == 2){
                return findViewById(R.id.imageView198);
            }
            else if (col == 3){
                return findViewById(R.id.imageView199);
            }
            else if (col == 4){
                return findViewById(R.id.imageView200);
            }
            else if (col == 5){
                return findViewById(R.id.imageView201);
            }
            else if (col ==6){
                return findViewById(R.id.imageView202);
            }
            else if (col == 7){
                return findViewById(R.id.imageView203);
            }
            else if (col == 8){
                return findViewById(R.id.imageView204);
            }
            else if (col == 9){
                return findViewById(R.id.imageView205);
            }
            else if (col == 10){
                return findViewById(R.id.imageView206);
            }
            else if (col == 11){
                return findViewById(R.id.imageView207);
            }
            else if (col == 12){
                return findViewById(R.id.imageView208);
            }
            else if (col == 13){
                return findViewById(R.id.imageView209);
            }
            else if (col == 14){
                return findViewById(R.id.imageView210);
            }
            else{
                return null;
            }
        }
        else if (row == 14){
            if(col == 0){
                return findViewById(R.id.imageView211);
            }
            else if (col == 1){
                return findViewById(R.id.imageView212);
            }
            else if (col == 2){
                return findViewById(R.id.imageView213);
            }
            else if (col == 3){
                return findViewById(R.id.imageView214);
            }
            else if (col == 4){
                return findViewById(R.id.imageView215);
            }
            else if (col == 5){
                return findViewById(R.id.imageView216);
            }
            else if (col ==6){
                return findViewById(R.id.imageView217);
            }
            else if (col == 7){
                return findViewById(R.id.imageView218);
            }
            else if (col == 8){
                return findViewById(R.id.imageView219);
            }
            else if (col == 9){
                return findViewById(R.id.imageView220);
            }
            else if (col == 10){
                return findViewById(R.id.imageView221);
            }
            else if (col == 11){
                return findViewById(R.id.imageView222);
            }
            else if (col == 12){
                return findViewById(R.id.imageView223);
            }
            else if (col == 13){
                return findViewById(R.id.imageView224);
            }
            else if (col == 14){
                return findViewById(R.id.imageView225);
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

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
                return getResources().getDrawable(R.drawable.fFinal);
            case 'g':
                return getResources().getDrawable(R.drawable.gfinal);
            case 'h':
                return getResources().getDrawable(R.drawable.hFinal);
            case 'i':
                return getResources().getDrawable(R.drawable.iFinal);
            case 'j':
                return getResources().getDrawable(R.drawable.jFinal);
            case 'k':
                return getResources().getDrawable(R.drawable.kFinal);
            case 'l':
                return getResources().getDrawable(R.drawable.lFinal);
            case 'm':
                return getResources().getDrawable(R.drawable.mFinal);
            case 'n':
                return getResources().getDrawable(R.drawable.nFinal);
            case 'o':
                return getResources().getDrawable(R.drawable.oFinal);
            case 'p':
                return getResources().getDrawable(R.drawable.pFinal);
            case 'q':
                return getResources().getDrawable(R.drawable.qFinal);
            case 'r':
                return getResources().getDrawable(R.drawable.rFinal);
            case 's':
                return getResources().getDrawable(R.drawable.sFinal);
            case 't':
                return getResources().getDrawable(R.drawable.tFinal);
            case 'u':
                return getResources().getDrawable(R.drawable.uFinal);
            case 'v':
                return getResources().getDrawable(R.drawable.vFinal);
            case 'w':
                return getResources().getDrawable(R.drawable.wFinal);
            case 'x':
                return getResources().getDrawable(R.drawable.xFinal);
            case 'y':
                return getResources().getDrawable(R.drawable.yFinal);
            case 'z':
                return getResources().getDrawable(R.drawable.zFinal);
            default:
                return getResources().getDrawable(R.drawable.backgroundSquare);
        }

    }

    //this gets the linear layout cell that we want to change
    private int getLinearLayout(int x, int y){
        int id = -1;


        return id;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }
}
