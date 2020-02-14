package edu.quinnipiac.ser210.mele_tictactoe;

/*
Author: Christian Mele
Title: TicTacToe
Submission Date: Feb 14, 2020
Home Screen Class
*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button start;
    EditText playerName;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        start = findViewById(R.id.start);
        playerName = findViewById(R.id.username1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                name = playerName.getText().toString();
                intent.putExtra("Name", name);
                startActivity(intent);
                finish();
            }
        });
    }
}