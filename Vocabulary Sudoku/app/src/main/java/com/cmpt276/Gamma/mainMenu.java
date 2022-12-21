package com.cmpt276.Gamma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.cmpt276.Gamma.R;

public class mainMenu extends AppCompatActivity {

    private Button help_button;
    private Button newGame;
    private Button continueGame;
    private Boolean showContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.main_menu);


        Intent intent = getIntent();
        boolean showContinueButton = intent.getBooleanExtra("showContinueButton", false);

        continueGame = (Button)findViewById(R.id.continueGame);
        if(showContinueButton){
            continueGame.setVisibility(View.VISIBLE);
        }

        help_button = (Button)findViewById(R.id.Help_Button);
        help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mainMenu.this, helpWindow.class);
                if (showContinueButton) {
                    intent.putExtra("showContinueButton", true);
                }
                startActivity(intent);
            }
        });

        newGame = (Button)findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mainMenu.this, newGame.class);
                intent.putExtra("newGame",true);
                startActivity(intent);
            }
        });

        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mainMenu.this, gameController.class);
                intent.putExtra("initializeSavedState",true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        showContinue=sharedPreferences.getBoolean("showContinue",false);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("showContinue",showContinue);

    }
}