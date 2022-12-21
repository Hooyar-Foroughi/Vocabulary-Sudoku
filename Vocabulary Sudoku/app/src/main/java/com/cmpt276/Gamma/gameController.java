package com.cmpt276.Gamma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelProvider;

import com.cmpt276.Gamma.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;


public class gameController extends AppCompatActivity {

    private GridLayout grid;
    private LinearLayout scroll;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private gameBoardInitial gbI;
    private ImageButton help_button;
    private ImageButton back_button;
    private ImageButton erase_button;
    private ImageView soundIcon;
    private TextView volume;
    private SeekBar volumeBar;
    private ArrayList<wordPair> finalWords; //final vocabulary words used in game boards

    private boolean EorS;
    private boolean initializeSavedState; //to load save data if continue is pressed
    private boolean startingGame;//starting new game
    private boolean listeningMode;//listening comprehension mode
    public int gridSize; //size of grid

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Simply set EorS to English for now and get a new board
        EorS = true;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        //extracting the shared data
        Intent intent = getIntent();
        gridSize = intent.getIntExtra("gridSize",0);
        if(intent.getExtras()!=null && getIntent().hasExtra("finalWords")) {
            finalWords = (ArrayList<wordPair>) intent.getExtras().getSerializable("finalWords");
        }
        if(gridSize == 0){
            SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
            gridSize = sharedPreferences.getInt("gridSize",0);
        }
        initializeSavedState = intent.getBooleanExtra("initializeSavedState",false);
        startingGame = intent.getBooleanExtra("startingGame",false);
        listeningMode = intent.getBooleanExtra("listeningMode",false);

        switch(gridSize){
            case 4:
                setContentView(R.layout.game_board_4x4);
                break;
            case 9:
                setContentView(R.layout.game_board_9x9);
                break;
            case 6:
                setContentView(R.layout.game_board_6x6);
                break;
            case 12:
                setContentView(R.layout.game_board_12x12);
                break;
            default:
                setContentView(R.layout.game_board_9x9);
                break;
        }


        helpButtonClickListener();
        backButtonClickListener();



        //Calling grid and scroll from UI with ID
        grid = findViewById(R.id.gridLayout);
        scroll = findViewById(R.id.scrollLinear);

        if (!initializeSavedState && startingGame) {
            Log.d("gameBoard", "creating new data");
            loadInitialData();
        }
    }

//function that loads the data when new game is started
    private void loadInitialData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        if (listeningMode){
            showAudios();
        }
        gbI = new ViewModelProvider(this,new ViewModelFactory(gridSize,finalWords)).get(gameBoardInitial.class);
            initText();
            insertWordPair();
            eraseWordPair();

    }

    //function that saves the data
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(gbI);
        editor.putString("InitialGameBoard", json);
        editor.putBoolean("initializeSavedState",true);
        editor.putInt("gridSize",gridSize);
        editor.putBoolean("listeningMode",listeningMode);
        editor.apply();
        Log.d("gameBoard","saving Data");

    }

    //function that loads the save data when continue the paused game
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("InitialGameBoard",null);
        Type type = new TypeToken<gameBoardInitial>(){}.getType();
        initializeSavedState = sharedPreferences.getBoolean("initializeSavedState",false);
        if (gbI==null || initializeSavedState) {
            listeningMode = sharedPreferences.getBoolean("listeningMode",false);
            if (listeningMode){
                showAudios();
            }
            gbI = gson.fromJson(json, type);
            Log.d("gameBoard","loading data");
            sharedPreferences.edit().clear().commit();
            initText();
            insertWordPair();
            eraseWordPair();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("gameBoard","pause");
        saveData();

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("gameBoard","resume");
        loadData();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(gameController.this, mainMenu.class);
        intent.putExtra("showContinueButton", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    //showing the audio labels when in listening comprehension mode
    private void showAudios(){
        soundIcon = (ImageView) findViewById(R.id.soundIcon);
        volume = (TextView) findViewById(R.id.volumeText);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        soundIcon.setVisibility(View.VISIBLE);
        volume.setVisibility(View.VISIBLE);
        volumeBar.setVisibility(View.VISIBLE);
        volumeBar.setProgress(200);
    }

    //help button click event
    private void helpButtonClickListener(){
        // Help Button Activity
        help_button = (ImageButton) findViewById(R.id.Help_Button2);
        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameController.this, helpWindow_GB.class);
                startActivity(intent);
            }
        });
    }

    //back button click event
    private void backButtonClickListener(){

        // Back Button Activity
        back_button = (ImageButton) findViewById(R.id.Back_Button2);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameController.this, mainMenu.class);
                intent.putExtra("showContinueButton", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
    // Converts from a 2d position to a linear array position
    // Added for parity to Pos2toPos1d (not super necessary)
    private int Pos2dtoPos1d(int x, int y)
    {
        return (x* gbI.msize + y);
    }

    // Converts from a linear array position to a 2d position
    private int[] Pos1dtoPos2d(int pos)
    {
        int[] arr = {(pos / gbI.msize), (pos % gbI.msize)};
        return arr;
    }

    // Instantiates the members of both the grid and the scroll bar
    private void initText()
    {
        initTSB(); // Scroll bar text
        initTGB(); // Gameboard text

    }

    // Initializes scrollbar text
    private void initTSB() {
        // Access the wordset
        wordPair[] ws = gbI.gb.getWordSet();

        if(startingGame && !initializeSavedState) {
            int numsFound = 0;
            gbI.gb.usedIndices = new ArrayList<Integer>();
            while (numsFound < gbI.msize) {
                int index = new Random().nextInt(gbI.msize);
                if (!gbI.gb.usedIndices.contains(index)) { // not already used?
                    TextView wt = (TextView) scroll.getChildAt(index);
                    if (EorS) wt.setText(ws[numsFound].Eword);
                    else wt.setText(ws[numsFound].Sword);
                    gbI.gb.usedIndices.add(index);
                    numsFound++;
                }
            }
        }else{
            for(int i=0; i<gbI.msize; i++){
                int index = gbI.gb.usedIndices.get(i);
                TextView wt = (TextView) scroll.getChildAt(index);
                if (EorS) wt.setText(ws[i].Eword);
                else wt.setText(ws[i].Sword);
            }
        }
    }


    // Initializes gameboard text
    private void initTGB()
    {
        // Access the gb cur
        wordPair[][] cur = gbI.gb.boardGet();

        // For all members of the grid
        for(int i = 0; i < gbI.msize2; i++)
        {
            // Access the text view, go from 1d to 2d
            TextView bt = (TextView) grid.getChildAt(i);
            int[] tmp = Pos1dtoPos2d(i);

            int x = tmp[0];
            int y = tmp[1];

            // Get the word pair at x and y, set accordingly
            // Note: do opposite of rest
            if(!listeningMode) {

                if (!EorS && cur[x][y].Eword.length() > 1)
                    bt.setText(cur[x][y].Eword.substring(0, 2));
                else if (!EorS) bt.setText(cur[x][y].Eword);
                else if (cur[x][y].Sword.length() > 1) bt.setText(cur[x][y].Sword.substring(0, 2));
                else bt.setText(cur[x][y].Sword);
            }else{
                if (cur[x][y].Sword.length() > 1) bt.setText(""+cur[x][y].subInt);

            }
            if(gbI.gb.isSelected[x][y]){
                highlightSimilar(x,y);
                showEnlargedWord(bt,i);
                bt.setSelected(true);
            }

            if(!gbI.gb.correctPosition[x][y] && !gbI.gb.lockGet()[x][y]){
                bt.setTextColor(Color.parseColor("red"));
            }
            else if(gbI.gb.correctPosition[x][y] && !gbI.gb.lockGet()[x][y]){
                bt.setTextColor(Color.parseColor("blue"));
            }
            clickingCellAction(bt, i);
        }

    }


    // Function perform action on the grid cell after it's clicked
    private void clickingCellAction(TextView textView,int pos)
    {
        // Set the listener
        textView.setOnClickListener(new View.OnClickListener() {

            // Override the onclick function
            @Override
            public void onClick(View v){
                int[] tmp = Pos1dtoPos2d(pos);
                int x = tmp[0];
                int y = tmp[1];
                // Reset all other cells
                resetCells();

                // Setting selected cell selection true
                textView.setSelected(true);
                showEnlargedWord(textView, pos);


                gbI.gb.isSelected[x][y] = true;
                highlightSimilar(x, y);
            }

        });
    }

    // Highlight cells with the same value
    private void highlightSimilar(int px, int py)
    {

        wordPair[][] cur = gbI.gb.boardGet();
        wordPair wpC = cur[px][py];

        if(wpC.subInt > -1) {
            for (int i = 0; i < gbI.msize2; i++) {

                int[] tmp = Pos1dtoPos2d(i);
                int x = tmp[0];
                int y = tmp[1];

                TextView tv = (TextView) grid.getChildAt(i);

                if (wpC.subInt == cur[x][y].subInt) {
                    tv.setActivated(true);
                } else tv.setActivated(false);
            }
        }
    }

    // Shows enlarged word of the selected cell
    private void showEnlargedWord(TextView textView, int pos)
    {

        int[] tmp = Pos1dtoPos2d(pos);
        int x = tmp[0];
        int y = tmp[1];

        // Get the context of the display and the current gameboard state
        TextView largeWord = findViewById(R.id.enlarged_word);
        wordPair[][] cur = gbI.gb.boardGet();

        // If the cell is not empty...
        if(!(cur[x][y] == gameBoard.nullPair)){
            if(!listeningMode) {

                largeWord.setVisibility(View.VISIBLE);

                // Determine english or spanish
                if (EorS) largeWord.setText(cur[x][y].Sword);
                else largeWord.setText(cur[x][y].Eword);
            }else{
                    String word = cur[x][y].Sword.toLowerCase();
                    playAudio(word);

            }
        }
        // If the cell is empty...
        else {
            largeWord.setVisibility(View.INVISIBLE);
        }
    }

    //function that plays audio when clicked on cells
    public void playAudio(String spanishword){

        String audioUrl = "https://translate.google.com/translate_tts?ie=UTF-&&client=tw-ob&tl=es&q="+ spanishword;
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Log.v("audiomax",""+"hey");

        setVolume();

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp){
                    mp.start();
                }
            });

            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Result of Dictionary","failaudio");

        }
    }

    //function that sets the volume according to the volume bar
    private void setVolume(){
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.v("audiomax",""+maxVolume);
        Log.v("audiocur",""+curVolume);
        volumeBar.setMax(maxVolume);
        volumeBar.setProgress(curVolume);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    // Resets all cells to a blank state (not selected, not highlighted)
    private void resetCells()
    {
        // For all cells in the grid, set to unselected
        for (int i =0; i< gbI.msize2; i++) {
            TextView textView1 = (TextView) grid.getChildAt(i);
            textView1.setSelected(false);
            textView1.setActivated(false);
            int[] tmp = Pos1dtoPos2d(i);
            int x = tmp[0];
            int y = tmp[1];
            gbI.gb.isSelected[x][y] = false;
        }
    }

    // Check if the same word is found in row/column/3x3 Square (bad placement)
    private boolean checkForBadPlacement(int px, int py)
    {
        if(!gbI.gb.allRow(py)) return true;
        if(!gbI.gb.allCol(px)) return true;
        if(!gbI.gb.allSB((px / gbI.gb.sbX), (py / gbI.gb.sbY))) return true;

        return false;
    }

    // Validate cell after insertion
    private void validateCell(TextView tv, int x, int y)
    {
        if(checkForBadPlacement(x,y)){
            tv.setTextColor(Color.parseColor("red"));
            gbI.gb.correctPosition[x][y] = false;
        }
        else{
            tv.setTextColor(Color.parseColor("blue"));
            gbI.gb.correctPosition[x][y] = true;
        }
    }

    // Validate all the cells after insertion
    private void validateOtherCells()
    {
        for(int i =0; i< gbI.msize; i++){
            for(int k=0; k< gbI.msize; k++){
                TextView tv1 = (TextView) grid.getChildAt(Pos2dtoPos1d(i,k));
                if(!gbI.gb.correctPosition[i][k]){
                    validateCell(tv1,i,k);
                }
            }
        }
    }

    // Function that fills an empty cell with spanish word by clicking on bottom scroll buttons
    private void insertWordPair()
    {

        wordPair[] ws = gbI.gb.getWordSet();

        for(int i = 0; i<gbI.msize; i++){

            TextView wt = (TextView) scroll.getChildAt(gbI.gb.usedIndices.get(i));
            wordPair wp = ws[i];
            int finalI = i;

            // The following sets the text for the given cells and updates it
            wt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    // Get the locked set
                    boolean[][] lk = gbI.gb.lockGet();

                    // For all cells in the grid
                    for (int j =0; j< gbI.msize2; j++){

                        // Get the position in 2d
                        int[] tmp = Pos1dtoPos2d(j);
                        int x = tmp[0];
                        int y = tmp[1];

                        // Get the textview
                        TextView tv = (TextView) grid.getChildAt(j);

                        // If a cell that can be edited is edited
                        if(tv.isSelected() && !lk[x][y]){

                            // Insert the wordpair to gb
                            try {
                                gbI.gb.insertWord(finalI, x, y);
                                System.out.println(gbI.gb.boardGet()[x][y].subInt);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Set the text to the appropriate language
                            if(!listeningMode) {
                                if (EorS) tv.setText(wp.Sword.substring(0, 2));
                                else tv.setText(wp.Eword.substring(0, 2));
                            }else{
                               tv.setText(""+wp.subInt);
                            }

                            // Show the enlarged version of this word and activate highlights
                            showEnlargedWord(tv, j);
                            highlightSimilar(x, y);
                            validateCell(tv,x,y);
                            validateOtherCells();

                            // Check for the win condition
                            if(gbI.gb.checkWin()) {
                                startActivity(new Intent(gameController.this, winPopup.class));
                                resetCells();
                            }
                        }
                        // Tell the player if it is locked
                        else if(tv.isSelected()){
                            Toast.makeText(gameController.this, "This cell is locked", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
        }
    }

    // Function that erases the content of the cell by clicking erase button
    private void eraseWordPair()
    {
        boolean[][] lk = gbI.gb.lockGet();
        erase_button = (ImageButton) findViewById(R.id.erase_button);
        erase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int j =0; j< gbI.msize2; j++){

                    // Get the position in 2d
                    int[] tmp = Pos1dtoPos2d(j);
                    int x = tmp[0];
                    int y = tmp[1];
                    // Get the textview
                    TextView tv = (TextView) grid.getChildAt(j);

                    // If a cell that can be edited is edited
                    if(tv.isSelected() && !lk[x][y]) {
                        gbI.gb.correctPosition[x][y] = true;
                        gbI.gb.removeWord(x,y);
                        tv.setText("");
                        resetCells();
                        tv.setSelected(true);
                        gbI.gb.isSelected[x][y]=true;
                        validateOtherCells();
                        showEnlargedWord(tv,j);
                    }
                }
            }
        });
    }


}
