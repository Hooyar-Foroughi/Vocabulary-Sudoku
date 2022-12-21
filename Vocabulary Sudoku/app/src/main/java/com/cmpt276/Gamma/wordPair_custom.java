package com.cmpt276.Gamma;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.cmpt276.Gamma.R;

public class wordPair_custom extends AppCompatActivity {


    private ImageButton back_button;
    private Button save_button;
    private TableLayout TB;
    private fileReader saveFile;
    private ArrayList<wordPair> customWords = new ArrayList<>();//custom words empty at at starting

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.custom_wordpair);
        saveFile = new fileReader("customWords",getApplicationContext());
        customWords = saveFile.parseWords();
        if(!customWords.isEmpty()){
            fillCustomWords();
        }
        backButtonClick();
        saveButtonClick();

    }

    //back button click event
    private void backButtonClick(){
        back_button = (ImageButton) findViewById(R.id.CusBack_Button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(wordPair_custom.this, newGame.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("customWords", customWords);//sending custom words back
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }

    //save Button click event that saves the data if it's valid
    private void saveButtonClick(){
        customWords = new ArrayList<>();
        save_button = (Button) findViewById(R.id.Custom_save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkData()){ // if data is not valid
                    return;
                } else { //if data is valid, save it in an arraylist of wordpairs
                    TB = findViewById(R.id.table_id);
                    int index = 0;
                    for (int i = 0; i < 12; i++) {
                        TableRow tmp = (TableRow) TB.getChildAt(i + 1);
                        EditText edit = (EditText) tmp.getChildAt(0);
                        EditText edit2 = (EditText) tmp.getChildAt(1);
                        String EnglishWord = edit.getText().toString();
                        String SpanishWord = edit2.getText().toString();
                        Log.v("result of words",EnglishWord);
                        Log.v("result of words",SpanishWord);
                       if (!EnglishWord.isEmpty() && !SpanishWord.isEmpty()) {
                            customWords.add(index, new wordPair(EnglishWord, SpanishWord,index));
                            index++;
                       }
                    }
                    saveFile.writeWords(customWords); //save custom words in the memory
                }

                Log.v("result of words","entered");
                Toast.makeText(wordPair_custom.this, "WordPairs are added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(wordPair_custom.this, newGame.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("customWords", customWords);//sending CustomWords to the front menu to send to the gamebaord if needed
                intent.putExtras(mBundle);
                startActivity(intent);
            }

        });
    }

    //checking data inside the edittexts if it's valid
    private Boolean checkData(){
        TB = findViewById(R.id.table_id);
        int emptyRows = 0;
        for(int i = 0; i < 12; i++) {
            TableRow tmp = (TableRow) TB.getChildAt(i + 1);
            EditText edit = (EditText) tmp.getChildAt(0);
            EditText edit2 = (EditText) tmp.getChildAt(1);
            String EnglishWord = edit.getText().toString();
            String SpanishWord = edit2.getText().toString();
            if (EnglishWord.isEmpty() && !SpanishWord.isEmpty() || !EnglishWord.isEmpty() && SpanishWord.isEmpty()) {
                Toast.makeText(wordPair_custom.this, "Invalid WordPair", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(EnglishWord.isEmpty() && SpanishWord.isEmpty()){
                emptyRows++;
            }
        }
        if (emptyRows==12){
            Toast.makeText(wordPair_custom.this, "No wordpairs to add", Toast.LENGTH_SHORT).show();
            saveFile.writeWords(customWords);
            return false;
        }
        return true;
    }

    //filling the saved custom words in the UI
    private void fillCustomWords(){
        TB = findViewById(R.id.table_id);

        for (int i = 0; i < customWords.size(); i++) {
            TableRow tmp = (TableRow) TB.getChildAt(i+1);
            EditText edit = (EditText) tmp.getChildAt(0);
            EditText edit2 = (EditText) tmp.getChildAt(1);
            edit.setText(customWords.get(i).Eword);
            edit2.setText(customWords.get(i).Sword);
        }
    }




}



