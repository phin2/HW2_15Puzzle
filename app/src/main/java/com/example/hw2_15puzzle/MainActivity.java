package com.example.hw2_15puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
/* @Author: Phi Nguyen
   @Date: 21 Sept 2021
   Added method to make sure puzzle is solvable
 */
public class MainActivity extends AppCompatActivity {
    private static TextView moveCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoardView bView = findViewById(R.id.boardView);
        BoardController bController = new BoardController(bView);

        //creates button to shuffle board
        Button shuffleButton = findViewById(R.id.shuffleBoard);
        shuffleButton.setOnClickListener(bController);
        moveCount = findViewById(R.id.moveCounter);
        bView.setOnTouchListener(bController);
    }
    public static void setMoveCount(int moves) {
    moveCount.setText("Moves: " + moves);
    }
}


