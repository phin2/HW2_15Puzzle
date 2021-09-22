package com.example.hw2_15puzzle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BoardController implements View.OnClickListener, View.OnTouchListener{
    BoardView bView;
    BoardModel bModel;
    int moveCount;

    public BoardController(BoardView boardView) {
        bView = boardView;
        bModel = bView.getBoard();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.shuffleBoard) {
            bModel.shuffle(); //shuffles board
            boolean solvable = bModel.checkSolvable();
            while (!solvable) { //keeps shuffling until board is solvable
                bModel.shuffle();
                solvable = bModel.checkSolvable();
            }
            bView.invalidate();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getActionMasked() == motionEvent.ACTION_DOWN) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int xIndex;
            int yIndex;
            if(bView.inBoard(x,y)) {
                xIndex = bModel.findIndexX(x);
                yIndex = bModel.findIndexY(y);
                if(bModel.moveTile(xIndex,yIndex)) {
                moveCount++;
                MainActivity.setMoveCount(moveCount);
                bView.invalidate();
                }
            }
                return true;
            }
        return false;
        }
    }


