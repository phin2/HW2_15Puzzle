package com.example.hw2_15puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoardView bView = findViewById(R.id.boardView);
        BoardController bController = new BoardController(bView);

        //creates button to shuffle board
        Button shuffleButton = findViewById(R.id.shuffleBoard);
        shuffleButton.setOnClickListener(bController);
        TextView moveCount = findViewById(R.id.moveCounter);
        bView.setOnTouchListener(bController);
    }
}