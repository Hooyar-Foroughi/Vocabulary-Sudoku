package com.cmpt276.Gamma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cmpt276.Gamma.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class newGame extends AppCompatActivity {

    private Button play_button;
    private Button wp_button;
    private Chip wordMode;
    private Chip listenMode;
    private Vocabulary wordbank = new Vocabulary();
    private ArrayList<wordPair> customWords = new ArrayList<>();//custom words empty at starting.
    private ArrayList<wordPair> finalWords;
    private ImageButton back_button;
    private fileReader saveFile;


    private int numsFound;
    private int vocablength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.new_game);
        saveFile = new fileReader("customWords",getApplicationContext());
        customWords = saveFile.parseWords();

        backButtonClick();
        customWordsClick();
        playButtonClick();


    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(newGame.this, mainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    //Back button click event to get you back to the main menu
    private void backButtonClick(){
        back_button = (ImageButton) findViewById(R.id.Back_Button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(newGame.this, mainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    //Custom Words click event get you to new activity to create own custom words
    private void customWordsClick(){
        wp_button = (Button)findViewById(R.id.WordPair_button);
        wp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(newGame.this, wordPair_custom.class);
                startActivity(intent);
            }
        });
    }

    //play button click event to play according to the selected choices.
    private void playButtonClick(){
        listenMode = (Chip)findViewById(R.id.LMode);
        wordMode = (Chip)findViewById(R.id.customWP);
        play_button = (Button)findViewById(R.id.Play_Button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wordMode.isChecked()) { //if custom word pair mode is checked
                    if (customWords.size() < getGridSize()) { //if not enough custom words as compared to the grid size.
                        Toast.makeText(newGame.this, "Not enough Custom Words", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        setCustomFinalWords(); //initializing custom words in game
                    }
                } else {
                    setVocabWords(); //initializing random words from the vocabulary list
                }


                Intent intent = new Intent(newGame.this, gameController.class);
                Bundle mBundle = new Bundle();
                int gridSize = getGridSize();
                intent.putExtra("gridSize", gridSize); //sending grid size
                intent.putExtra("startingGame", true); //sending game started boolean
                mBundle.putSerializable("finalWords", finalWords); //send wordpairs to show in the gameboard
                intent.putExtras(mBundle);
                if(listenMode.isChecked()){
                    intent.putExtra("listeningMode",true);//sending listeningmode state if listening mode is checked
                }
                startActivity(intent);

            }

        });
    }

    //function that picks words from custom words list if custom mode is selected (only use when we have enough custom words as gridsize)
    private void setCustomFinalWords(){
        finalWords = new ArrayList<>();
        numsFound = 0;
        vocablength = getGridSize();
        List<Integer> usedIndices = new ArrayList<Integer>();
        while (numsFound < vocablength) {
            int index = new Random().nextInt(customWords.size());
            if (!usedIndices.contains(index)) { // not already used?
                wordPair wordset = customWords.get(index);
                finalWords.add(numsFound,wordset);
                usedIndices.add(index);
                numsFound++;
            }
        }
    }

    //function that picks random words from vocabulary list if normal mode is selected
    private void setVocabWords(){
        finalWords = new ArrayList<>();
        numsFound = 0;
        vocablength = getGridSize();
        List<Integer> usedIndices = new ArrayList<Integer>();
        while (numsFound < vocablength) {
            int index = new Random().nextInt(wordbank.getVocabulary().length);
            if (!usedIndices.contains(index)) { // not already used?
                String EnglishWord = wordbank.getVocabulary(index)[0];
                String SpanishWord = wordbank.getVocabulary(index)[1];
                wordPair wordset = new wordPair(EnglishWord,SpanishWord,numsFound);
                finalWords.add(numsFound,wordset);
                usedIndices.add(index);
                numsFound++;
            }
        }
    }

//function that picks the grid size
    private int getGridSize(){

        Chip four = findViewById(R.id.chip4x4);
        Chip six = findViewById(R.id.chip6x6);
        Chip nine = findViewById(R.id.chip9x9);
        Chip twelve = findViewById(R.id.chip12x12);

        if(four.isChecked()){
            return 4;
        }
        else if(six.isChecked()){
            return 6;
        }
        else if(nine.isChecked()){
            return 9;
        }
        else{
            return 12;
        }
    }
}