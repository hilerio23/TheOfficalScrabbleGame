
package com.example.thescrabblegame.game.GameFramework;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.example.thescrabblegame.ScrabbleSurfaceView;
import com.example.thescrabblegame.game.GameFramework.gameConfiguration.GameConfig;
import com.example.thescrabblegame.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.thescrabblegame.game.GameFramework.infoMessage.GameState;
import com.example.thescrabblegame.game.GameFramework.players.GamePlayer;
import com.example.thescrabblegame.game.GameFramework.utilities.IPCoder;
import com.example.thescrabblegame.game.GameFramework.utilities.Logger;
import com.example.thescrabblegame.game.GameFramework.utilities.MessageBox;
import com.example.thescrabblegame.game.GameFramework.utilities.Saving;
import com.example.thescrabblegame.R;

/**
 * class GameMainActivity
 *
 * is the main activity for the game framework. To create a new game, create a
 * sub-class of this class that implements its abstract methods below.
 *
 * @author Andrew M. Nuxoll
 * @author Steven R. Vegdahl
 * @author Eric Imperio
 * @date Version 2020
 */
public abstract class GameMainActivity extends Activity implements
        View.OnClickListener {
    //Tag for Logging
    private static final String TAG = "GameMainActivity";
    /*
     * ====================================================================
     * Instance Variables
     * --------------------------------------------------------------------
     */

    // A reference to the object representing the game itself. This is the
    // object that knows the rules of the game. This variable is initialized in
    // launchGame.
    private Game game = null;

    // an array containing references to all the players that are playing the game
    private GamePlayer[] players = null;

    // tells which player, if any, is running in the GUI
    private GamePlayer guiPlayer = null;

    // whether the game is over
    private boolean gameIsOver = false;

    // whether it is so early in the game that the configuration screen may
    // not have been fully linked to the GUI
    private boolean justStarted = true;

    // whether the game is in the "configuration" stage, before the actual game
    // has started
    private boolean doingConfiguration = true;


    /**
     * contains the game configuration this activity will be used to initialize
     */
    GameConfig config = null;

    ScrabbleSurfaceView scrabble = findViewById(R.id.scrabbleSurfaceView);

    // Each of these is initialized to point to various GUI controls
    TableLayout playerTable = null;
    public ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

    public ArrayList<TableRow> test (){
        return tableRows;
    }

    //Keeping the user's configuration for restarting
    private GameConfig restartConfig = null;


    /*
     * ====================================================================
     * Abstract Methods
     *
     * To create a game using the game framework you must create a subclass of
     * GameMainActivity that implements the following methods.
     * --------------------------------------------------------------------
     */
    /**
     * Creates a default, game-specific configuration for the current game.
     *
     * IMPORTANT: The default configuration must be a legal configuration!
     *
     * @return an instance of the GameConfig class that defines a default
     *         configuration for this game. (The default may be subsequently
     *         modified by the user if this is allowed.)
     */
    public abstract GameConfig createDefaultConfig();

    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet. For example, if
     * you were creating tic-tac-toe, you would implement this method to return
     * an instance of your TTTLocalGame class which, in turn, would be a
     * subclass of {@link LocalGame}.
     * @param gameState
     *              The desired gameState to start at or null for new game
     *
     * @return a new, game-specific instance of a sub-class of the LocalGame
     *         class.
     */
    public abstract LocalGame createLocalGame(GameState gameState);

    /**
     * Creates a "proxy" game that acts as an intermediary between a local
     * player and a game that is somewhere else on the net.
     *
     * @param hostName
     *            the name of the machine where the game resides. (e.g.,
     *            "upibmg.egr.up.edu")
     * @return the ProxyGame object that was created
     */
    private ProxyGame createRemoteGame(String hostName) {
        int portNum = getPortNumber();
        return ProxyGame.create(portNum, hostName);
    }

    /*
     * ====================================================================
     * Public Methods
     * --------------------------------------------------------------------
     */
    /**
     * onCreate
     *
     * "main" for the game framework
     */
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set Context for Toast Logging
        Logger.setContext(getApplicationContext());

        // Initialize the layout
        setContentView(R.layout.activity_main);

        // create the default configuration for this game
        this.config = createDefaultConfig();

        /*
        // if there is a saved configuration, modify the default configuration accordingly
        if (!this.config.restoreSavedConfig(saveFileName(), this)) {
            MessageBox.popUpMessage(Resources.getSystem().getString(R.string.Config_Error_Msg),
                    this);
        }

         */

        if (this.config.isUserModifiable()) { // normal run: user has chance to modify configuration

            // initialize and show the GUI that allows the user to specify the game's
            // configuration
            initStarterGui();

            // hide the soft keyboard, so the that user does not need to dismiss it (which
            // he would often want to do)
            hideSoftKeyboard();

            // allow buttons to interact
            justStarted = false;
        }
        else { // special run (during debugging?): use the given configuration, unmodified
            String msg = launchGame(this.config, null);
            if (msg != null) {
                // we have an error message
                MessageBox.popUpMessage(msg, this);
            }
        }
        /*
        if (((CheckBox) findViewById(R.id.onScreenLogging)).isChecked()) {
            Logger.setToastValue(true);
        } else {
            Logger.setToastValue(false);
        }
        if (((CheckBox) findViewById(R.id.debugLogging)).isChecked()){
            Logger.setDebugValue(true);
        }else {
            Logger.setDebugValue(false);
        }

         */
        Logger.setDebugValue(false);
    }// onCreate

    /**
     * Returns the name of the configuration save-file.
     *
     * @return
     * 		the name of the configuration file for this application to use
     */
    private String saveFileName() {
        return "savedConfig"+getPortNumber()+".dat";
    }//saveFileName

    /**
     * hides the soft keyboard so that the use does not need to dismiss it
     */
    private void hideSoftKeyboard() {
        // create a runnable object that waits for things to settle down, and then
        // hides the window
        Runnable runner = new Runnable() {
            public void run() {
                try {
                    // wait for one second
                    Thread.sleep(1000);

                    // hide the keyboard
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                }
                catch (Exception x) {
                    // catch and ignore any exceptions we might encounter
                }
            }
        };

        // run the thread
        Thread t = new Thread(runner);
        t.start();
//		try {
//			// join the thread to that we don't get ahead of it
//			t.join();
//		} catch (InterruptedException e) {
//		}

    }//hideSoftKeyboard

    /**
     * Callback-method, called when the configuration changes--typically when the tablet
     * is rotated.
     */
    public void onConfigurationChanged(Configuration newConfig) {

        // Perform superclass configuration changes
        super.onConfigurationChanged(newConfig);

        // if still on the configuration screen, continue showing it;
        // otherwise, set the new GUI (which may have changed) for the
        // human player
        if (!doingConfiguration) {
            if (guiPlayer != null) {
                // if there is a GUI player, link it to the activity
                guiPlayer.gameSetAsGui(this);
            }
            else {
                // if there is no GUI player, set the layout to be one
                // with a "no GUI" message
                //setContentView(R.layout.game_no_gui);
            }
        }
    }//onConfigurationChanged

    /**
     * Creates the game and players, and starts the game.
     *
     * @param config
     *            is the configuration for this game
     * @param gameState
     *            the gameState for this game
     * @return
     * 			null if the launch was successful; otherwise a message telling
     * 			why game could not be launched
     */
    private final String launchGame(GameConfig config, GameState gameState) {

        // Set the title text with the game's name
        this.setTitle(config.getGameName());

        // create the game if it's local (we defer remote game creation
        // until further down so that we do not attempt to make the
        // network connection until other errors are checked)
        if (config.isLocal()) { // local game
            game = createLocalGame(gameState);
            // verify we have a game
            if (game == null) {
                //return Resources.getSystem().getString(R.string.Game_Creation_Error_Msg);
            }
        }

        //////////////////////////////////////
        // create the players
        //////////////////////////////////////
        int requiresGuiCount = 0; // the number of players that require a GUI
        guiPlayer = null; // the player that will be our GUI player
        players = new GamePlayer[config.getNumPlayers()]; // the array to contains our players

        // loop through each player
        for (int i = 0; i < players.length; i++) {
            String name = config.getSelName(i); // the player's name
            GamePlayerType gpt = config.getSelType(i); // the player's type
            GamePlayerType[] availTypes = config.getAvailTypes(); // the available player types
            players[i] = gpt.createPlayer(name); // create the player

            // check that the player name is legal
            if (name.length() <= 0 && gpt != availTypes[availTypes.length-1]) {
                // disallow an empty player name, unless it's a dummy (proxy) player
                //return getString(R.string.Local_Player_Name_Error_Msg);
            }

            // if the player requires a GUI, count and mark it; otherwise, if a player
            // supports a GUI and the "requires" count is zero, mark it
            if (players[i].requiresGui()) {
                requiresGuiCount++;
                guiPlayer = players[i];
            }
            else if (guiPlayer == null && players[i].supportsGui()) {
                guiPlayer = players[i];
            }
        }

        // create the game if it's remote
        if (!config.isLocal()) { // remote game
            game = createRemoteGame(config.getIpCode());
            // verify we have a game
            if (game == null) {
                //return getString(R.string.Game_Server_Error_Msg);
            }
        }

        // if there is more than one player that requires a GUI, abort
        if (requiresGuiCount >= 2) {
            //return getString(R.string.Mult_GUI_Tabl_Error_Msg);
        }

        // if there is a player that supports a GUI, link it to the activity,
        // otherwise set the GUI to be a "dummy" one with a "no GUI" message
        if (guiPlayer != null) {
            guiPlayer.gameSetAsGui(this);
        }
        else {
            // set the layout to be one with a "no GUI" message
            //setContentView(R.layout.game_no_gui);
        }

        // mark the configuration as being completed
        doingConfiguration = false;

        // start the game; then return null to indicate that the launch was
        // successful
        game.start(players);
        return null;

    }// launchGame

    /**
     * initializes the pages in the tabbed dialog
     */
    /*
    protected void initTabs() {
        // Setup the tabbed dialog on the layout and add the content of each tab
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        //Adding Local Tab for Local Game or Host Game
        TabSpec localTabSpec = tabHost.newTabSpec(localTabString());
        localTabSpec.setContent(R.id.localGameTab);
        localTabSpec.setIndicator(localTabString());
        //Adding Remote Tab for Remote WiFi Game connection
        TabSpec remoteTabSpec = tabHost.newTabSpec(remoteTabString());
        remoteTabSpec.setContent(R.id.remoteGameTab);
        remoteTabSpec.setIndicator(remoteTabString());
        //Adding Settings Tab that can be customized to allow for customized rules
        TabSpec settingsTabSpec = tabHost.newTabSpec(settingsTabString());
        settingsTabSpec.setContent(R.id.gameSettingsTab);
        settingsTabSpec.setIndicator(settingsTabString());
        tabHost.addTab(localTabSpec);
        tabHost.addTab(remoteTabSpec);
        tabHost.addTab(settingsTabSpec);

        // make sure the current tab is the right one
        tabHost.setCurrentTab(config.isLocal() ? 0 : 1);

    }// initTabs

     */

    /**
     * initialize the rows in the player table
     */
    protected void initTableRows() {

        // save away the information about whether we're on the local tab;
        // set things temporarily ab being true so that the rows end up in
        // the first tab
        boolean savedIsLocal = config.isLocal();
        config.setLocal(true);

        /*
        // put a row in the table for each player in the config
        this.playerTable = (TableLayout) findViewById(R.id.configTableLayout);
        int numPlayers = config.getNumPlayers();
        for (int i = 0; i < numPlayers; ++i) {

            // add the row
            TableRow row = addPlayer();

            // Set the player name
            TextView playerName = (TextView) row
                    .findViewById(R.id.playerNameEditText);
            playerName.setText(config.getSelName(i));

            // Set the initial selection for the spinner
            GamePlayerType[] selTypes = config.getSelTypes(); // the player types in the config
            GamePlayerType[] availTypes = config.getAvailTypes(); // the available player types
            Spinner typeSpinner = (Spinner) row
                    .findViewById(R.id.playerTypeSpinner); // the spinner for the current player
            // search through to find the one whose label matches; set it as the selection
            for (int j = 0; j < availTypes.length; ++j) {
                if (selTypes[i].getTypeName().equals(availTypes[j].getTypeName())) {
                    typeSpinner.setSelection(j);
                    break;
                }
            }



            // set up our spinner so that when its last element ("Network Player") is selected,
            // the corresponding EditText (the player name) is disabled.
            typeSpinner.setOnItemSelectedListener(new SpinnerListListener(playerName, availTypes.length-1));

        }// for

        // restore the 'isLocal' property of the configuration object
        config.setLocal(savedIsLocal);
        */

    }// initTableRows

    /*
    protected void initRemoteWidgets() {
        //Set the remote name
        EditText remoteNameEditText = (EditText)findViewById(R.id.remoteNameEditText);
        remoteNameEditText.setText(config.getRemoteName());

        // index of remote player type
        GamePlayerType remotePlayerType = config.getRemoteSelType();
        GamePlayerType[] availTypes = config.getAvailTypes();
        Spinner remoteTypeSpinner = (Spinner)findViewById(R.id.remote_player_spinner);
        for (int j = 0; j < availTypes.length; ++j) {
            if (remotePlayerType.getTypeName().equals(availTypes[j].getTypeName())) {
                remoteTypeSpinner.setSelection(j);
                break;
            }
        }

        //Set the IP code
        EditText ipCodeEditText = (EditText)findViewById(R.id.remoteIPCodeEditText);
        ipCodeEditText.setText(config.getIpCode());
    }

     */

    protected void initSettingsTab(){
        //Override if the game has customizable rules
    }

    /**
     * places the data from this.config into the GUI.
     *
     */
    protected void initStarterGui() {
        // do nothing without a game config
        if (this.config == null)
            return;

        // Set the title text using the game's name
        this.setTitle(config.getGameName() + " Configuration");

        // place the pages in the tabbed dialog
        //initTabs();

        // Insert a row for each player in the current config
        initTableRows();

        // Set the remote widget data
        //initRemoteWidgets();

        //Set up the Settings Tab
        initSettingsTab();

        /*
        // Set myself as the listener for the buttons
        View v = findViewById(R.id.addPlayerButton);
        v.setOnClickListener(this);
        v = findViewById(R.id.saveConfigButton);
        v.setOnClickListener(this);
        v = findViewById(R.id.playGameButton);
        v.setOnClickListener(this);
        v = findViewById(R.id.onScreenLogging);
        v.setOnClickListener(this);
        v = findViewById(R.id.debugLogging);
        v.setOnClickListener(this);
         */


        String ipCode = IPCoder.encodeLocalIP();
        String ipAddress = IPCoder.getLocalIpAddress();
        //TextView ipText = (TextView)findViewById(R.id.ipCodeLabel);
        //ipText.setText(ipText.getText()+ipCode+" ("+ipAddress+") ");

    }// initStarterGui

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_main, menu);
        return true;
    }//onCreateOptionsMenu
     */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch (item.getItemId()) {
            case R.id.menu_help:
                Logger.log(TAG, "This is the help button!");
                return true;
            case R.id.save_game:
                Logger.log(TAG, "This is the save button!");
                if( this.game != null){
                    Logger.log(TAG, "The Game Exists!");
                    MessageBox.popUpSaveGame("Name your game:", this);
                } else {
                    Logger.log(TAG, "No Game Exists!");
                    MessageBox.popUpMessage("You cannot save a game without first starting a game (Click Anywhere to dismiss).", this);
                }
                return true;
            case R.id.load_game:
                Logger.log(TAG, "This is the loading button!");
                MessageBox.popUpLoadGame("Select Your Game: ", this);
                return true;
            case R.id.delete_game:
                Logger.log(TAG, "This is the delete button!");
                MessageBox.popUpDeleteGame("Select the Game to Delete: ", this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
         */
        return false;
    }


    /**
     * this method is called whenever the user clicks on a button.
     *
     * <p>
     * NOTE: With the current layout it could either be a Button or ImageButton.
     */

    public void onClick(View button) {

        Logger.log(TAG, "Clicked "+button);

        // if the GUI many not have been fully initialized, ignore
        if (justStarted) {
            return;
        }

        /*
        // Add Player Button
        if (button.getId() == R.id.addPlayerButton) {
            addPlayer();
            this.playerTable.invalidate(); // show the user the change
        }

        // Delete Player Button
        else if (button.getId() == R.id.delPlayerButton) {
            // Search the existing players to find out which delete button got
            // clicked
            for (int i = 0; i < this.tableRows.size(); i++) {
                TableRow row = tableRows.get(i);

                View v = row.findViewById(R.id.delPlayerButton);
                if (v == button) {
                    // found it! remove from the layout and the list
                    removePlayer(row);
                }
            }

        }// else if (delete button)

        //Save Config Button
        else if (button.getId() == R.id.saveConfigButton) {
            GameConfig configTemp = scrapeData();
            if (configTemp.saveConfig(saveFileName(), this)) {
                MessageBox.popUpMessage(getString(R.string.Saved_Config_Msg), this);
            }
            else {
                MessageBox.popUpMessage(getString(R.string.Saved_Config_Error_Msg), this);
            }
        }

        //Start Game Button
        else if (button.getId() == R.id.playGameButton) {
            String msg = startGame();
            if (msg != null) {
                // we have an error message
                MessageBox.popUpMessage(msg, this);
            }

        }
        //On-screen debugging checkbox
        else if(button.getId() == R.id.onScreenLogging){
            if(((CheckBox)button).isChecked()){
                Logger.setToastValue(true);
            }else{
                Logger.setToastValue(false);
            }
        }

        //Console debugging checkbox
        else if(button.getId() == R.id.debugLogging){
            if(((CheckBox)button).isChecked()){
                Logger.setDebugValue(true);
            }else{
                Logger.setDebugValue(false);
            }
        }

         */
        //setting all button's on click listener
        Button exchange = (Button)findViewById(R.id.exchange);
        Button pass = (Button)findViewById(R.id.pass);
        Button playword = (Button)findViewById(R.id.playword);
        Button exit = (Button)findViewById(R.id.exitGame);
        exchange.setOnClickListener(scrabble);
        pass.setOnClickListener(scrabble);
        playword.setOnClickListener(scrabble);
        exit.setOnClickListener(scrabble);

        //setting the score board's on click listener
        TextView scoreboard = (TextView)findViewById(R.id.scoreText);
        scoreboard.setOnEditorActionListener(scrabble);

        //setting the hand's on click listener
        ImageView first = (ImageView)findViewById(R.id.aButton);
        ImageView second = (ImageView)findViewById(R.id.bButton);
        ImageView third = (ImageView)findViewById(R.id.cButton);
        ImageView fourth = (ImageView)findViewById(R.id.dButton);
        ImageView fifth = (ImageView)findViewById(R.id.eButton);
        ImageView sixth = (ImageView)findViewById(R.id.gButton);
        first.setOnClickListener(scrabble);
        second.setOnClickListener(scrabble);
        third.setOnClickListener(scrabble);
        fourth.setOnClickListener(scrabble);
        fifth.setOnClickListener(scrabble);
        sixth.setOnClickListener(scrabble);

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

    }// onClick

/*
    private String startGame() {
        GameConfig finalConfig = scrapeData();
        //Saving the user's inputs in case they want to restart the game later
        this.restartConfig = finalConfig;
        return launchGame(finalConfig, null);
    }
 */
    /**
     * removePlayer
     *
     * removes the player in the table associated with a given TableRow object
     *
     * <p>
     * NOTE: this method will refuse to delete a row if the total would drop
     * below the minimum allowed by the game configuration.
     */
    /*
    private void removePlayer(TableRow row) {
        // first, make sure that we won't exceed the min number of players
        if (this.tableRows.size() <= config.getMinPlayers()) {
            MessageBox.popUpMessage(getString(R.string.Min_Player_Error_Msg),this);
            return;
        }
        this.playerTable.removeView(row);
        this.tableRows.remove(row);

    }// removePlayer

     */

    /**
     * addPlayer
     *
     * adds a new, blank row to the player table and initializes instance
     * variables and listeners appropriately
     *
     * @return a reference to the TableRow object that was created or null on
     *         failure
     */
    /*
    private TableRow addPlayer() {
        // first, make sure that we won't exceed the max number of players
        if (this.tableRows.size() >= config.getMaxPlayers()) {
            MessageBox.popUpMessage(getString(R.string.Max_Player_Error_Msg),	this);
            return null;
        }

        // add the row
        TableRow row = (TableRow) getLayoutInflater().inflate(
                R.layout.game_player_list_row, playerTable, false);

        // Initialize the values in the Spinner control
        //		GamePlayerType[] selTypes = config.getSelTypes();
        GamePlayerType[] availTypes = config.getAvailTypes();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (GamePlayerType gpt : availTypes) {
            adapter.add(gpt.getTypeName());
        }
        Spinner typeSpinner = (Spinner) row
                .findViewById(R.id.playerTypeSpinner);
        typeSpinner.setAdapter(adapter);
        // link player name field and spinner
        TextView playerName = (TextView) row
                .findViewById(R.id.playerNameEditText);
        typeSpinner.setOnItemSelectedListener(new SpinnerListListener(playerName, availTypes.length-1));
        typeSpinner.setSelection(0);

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(
                this, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int j = 0; j < availTypes.length-1; j++) {
            // leaves out the last item (network player)
            adapter2.add(availTypes[j].getTypeName());
        }
        Spinner remoteTypeSpinner = (Spinner)findViewById(R.id.remote_player_spinner);
        remoteTypeSpinner.setAdapter(adapter2);

        // set myself up as the button listener for the button
        ImageButton delButton = (ImageButton) row
                .findViewById(R.id.delPlayerButton);
        delButton.setOnClickListener(this);

        // add the row to the right lists and layout
        this.tableRows.add(row);
        playerTable.addView(row);

        return row;
    }// addPlayer
     */

    /**
     * scrapeData
     *
     * retrieves all the data from the GUI and creates a new GameConfig object
     * with it
     */
    /*
    public GameConfig scrapeData() {

        // First make a copy of the original config without the players
        GameConfig result = config.copyWithoutPlayers();

        // Set remote/local
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        result.setLocal(tabHost.getCurrentTab() == 0);

        // Retrieve the info for each player and add to the config
        for (TableRow row : this.tableRows) {
            //player name
            EditText nameEditor = (EditText) row
                    .findViewById(R.id.playerNameEditText);
            String name = nameEditor.getText().toString();

            //index of player type
            Spinner typeSpinner = (Spinner) row
                    .findViewById(R.id.playerTypeSpinner);
            int selIndex = typeSpinner.getSelectedItemPosition();

            //add to the config
            result.addPlayer(name, selIndex);
        }//for

        //Set the remote name
        EditText remoteNameEditText = (EditText)findViewById(R.id.remoteNameEditText);
        String remoteName = remoteNameEditText.getText().toString();
        result.setRemoteName(remoteName);

        //index of remote player type
        Spinner remoteTypeSpinner = (Spinner)findViewById(R.id.remote_player_spinner);
        int selIndex = remoteTypeSpinner.getSelectedItemPosition();
        result.setRemoteSelType(selIndex);

        //Set the IP code
        EditText ipCodeEditText = (EditText)findViewById(R.id.remoteIPCodeEditText);
        String ipCode = ipCodeEditText.getText().toString();
        result.setIpCode(ipCode);

        return result;
    }// scrapeData

     */

    /**
     * Call-back method when a soft key-event happens. Intercepts the "back" button
     * so that the activity is not killed with out user confirmation (unless the
     * game is already over).
     */
    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !gameIsOver) {
            // We have seen the back-key pressed, and the game is not over;
            // confirm with user that whether they want to quit
            String quitQuestion =
                    getString(R.string.dialog_quit_question);
            String posLabel =
                    getString(R.string.dialog_quit_label);
            String negLabel =
                    getString(R.string.dialog_continue_label);
            MessageBox.popUpChoice(quitQuestion, posLabel, negLabel,
                    new OnClickListener(){
                        public void onClick(DialogInterface di, int val) {
                            // if the user says that he wants to quit, exit the
                            // application
                            System.exit(0);
                        }},
                    null,
                    this);
            // return 'true' because we have handled this event
            return true;
        }
        else {
            // otherwise (not BACK key, or game is over), allow superclass method
            // to handle it
            return super.onKeyDown(keyCode, event);
        }
    }// onKeyDown

     */

    /**
     * Gets the port number for this configuration
     *
     * @return the configuration's port number
     */
    private int getPortNumber() {
        return config.getPortNum();
    }

    /**
     * marks the game as being over
     *
     * @param b
     * 			tells whether the game is over
     */
    public void setGameOver(boolean b) {
        gameIsOver = b;
    }// setGameOver

    public boolean getGameOver() { return gameIsOver; }


    /**
     *  the label for the local tab header
     *
     * @return
     * 		the label for the local tab header
     */
    /*
    private String localTabString() {
        return this.getResources().getString(R.string.local_tab);
    }// localTabString
    */

    /**
     *  the label for the remote tab header
     *
     * @return
     * 		the label for the remote tab header
     */
    /*
    private String remoteTabString() {
        return this.getResources().getString(R.string.remote_tab);
    }// remoteTabString
    /*

    /**
     *  the label for the settings tab header
     *
     * @return
     * 		the label for the settings tab header
     */
    /*
    private String settingsTabString(){
        return this.getResources().getString(R.string.settings_tab);
    }
    */

    /**
     * Helper-class so that we disable the name fields in the configuration
     * if the user has selected "Network player".
     */
    private static class SpinnerListListener implements OnItemSelectedListener {

        // the textView to disable
        private TextView correspondingTextField;

        // the position in the spinner of the "Network Player" selection
        private int disableIndex;

        /**
         * constructor
         *
         * @param txt
         * 			the TextView object
         * @param idxNum
         * 			the index of the "Network Player" item in the spinner
         */
        public SpinnerListListener(TextView txt, int idxNum) {
            correspondingTextField = txt;
            disableIndex = idxNum;
        }//constructor

        /**
         * callback method when an item is selected
         *
         * @param parent
         *		the AdapterView where the selection happened
         * @param view
         *		the view within the AdapterView that was clicked
         * @param position
         *		the position in the spinner of the new selection
         * @param id
         *		the row id of the item that is selected
         */
        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                   long id) {
            // enable the corresponding TextView depending on whether the "disabling"
            // position was selected
            correspondingTextField.setEnabled(position != disableIndex);
        }// onItemSelected

        /**
         * callback method when nothing is selected
         *
         * @param parent
         *		the AdapterView where the selection happened
         */
        public void onNothingSelected(AdapterView<?> parent) {
            // do nothing
        }// onNothingSelected

    }// class SpinnerListListener

    /**
     * finishes the activity
     *
     * @param v
     * 		the object that cause the callback
     */
    public void doFinish(View v) {
        finish();
    }

    /**
     * Restarts the activity (the game) with the configuration the user selected when they originally
     * started the game
     */
    public void restartGame(){
        //Might need to fake a configuration for the restart to work properly
        String msg = launchGame(this.restartConfig, null);
        if (msg != null) {
            // we have an error message
            MessageBox.popUpMessage(msg, this);
        }
    }

    /**
     * saveGame, saves the current configuration and gameState
     * @param gameName
     * @return String representation of the gameState
     */
    public GameState saveGame(String gameName) {
        Logger.log(TAG, "Saving: " + gameName);
        config.saveConfig(gameName + ".c" , this);
        GameState gameState = getGameState();
        Saving.writeToFile(gameState, gameName, this.getApplicationContext());
        return gameState;
    }

    /**
     * loadGame, load the config and gameState for selected gameName
     * NOTE: This should be called and overritten by your Game's MainActivity
     *
     * @param gameName
     * @return null
     */
    public GameState loadGame(String gameName) {
        config.restoreSavedConfig(gameName + ".c", this);
        // Sub class should do this part
        return null;
    }

    /**
     * Gets the current gameState to save
     * @return the current GameState
     */
    protected GameState getGameState(){
        return game.getGameState();
    }

    /**
     * startLoadedGame, starts the loaded GameState
     *
     * @param gameState
     */
    public void startLoadedGame(GameState gameState){
        String msg = launchGame(this.config, gameState);
        if (msg != null) {
            // we have an error message/
            MessageBox.popUpMessage(msg, this);
        }
    }

    public String getGameString(String gameName){
        return this.getString(R.string.app_name) + "_" + gameName;
    }

    //////////////////////
    // TESTING
    //////////////////////

    /*
     * gets the GameConfig for this game
     */
    public GameConfig getConfig() {
        return this.config;
    }

    public boolean isGameNull() {
        return game == null;
    }

    public Game getGame() {
        return game;
    }

    ///////////////////////
    //END TESTING
    ///////////////////////
}
