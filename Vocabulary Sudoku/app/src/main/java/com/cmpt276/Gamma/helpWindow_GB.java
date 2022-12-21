package com.cmpt276.Gamma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.cmpt276.Gamma.R;

public class helpWindow_GB extends AppCompatActivity {

    private ImageButton back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        System.out.println("hey in helpboard_gb");
        setContentView(R.layout.activity_help_window_game_board);

        back_button = (ImageButton) findViewById(R.id.Back_Button3);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(helpWindow_GB.this, gameController.class);
                startActivity(intent);
            }
        });
    }
}