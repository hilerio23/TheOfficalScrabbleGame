package com.example.thescrabblegame;

import android.util.Log;

import com.example.thescrabblegame.game.GameFramework.GameMainActivity;
import com.example.thescrabblegame.game.GameFramework.LocalGame;
import com.example.thescrabblegame.game.GameFramework.gameConfiguration.GameConfig;
import com.example.thescrabblegame.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameState;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class ScrabbleMainActivity is the primary activity for Scrabble
 *
 * @author Andrew M. Nuxoll, modified by Anabel Hilerio and Alec Uyematsu
 * @version April 2021
 */
public class ScrabbleMainActivity extends GameMainActivity {
    private static final int PORT_NUMBER = 2278;

    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new HumanScrabblePlayer(name, R.layout.activity_main);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new EasyAI(name);
            }});

        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Scrabble", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        //defaultConfig.addPlayer("Smart", 1); //adds smart player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player

        defaultConfig.setRemoteData("Remote Human Player", "", 1);

        return defaultConfig;

    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return new ScrabbleLocalGame();
    }
    public ScrabbleMainActivity getScrabbleMainActivity(){
        return(this);
    }
}
