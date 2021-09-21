package com.example.hw2_15puzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class BoardView extends SurfaceView {

    private BoardModel board = new BoardModel(4);
    Paint linePaint = new Paint();
    Paint matchPaint = new Paint();
    Paint matchNumberPaint = new Paint();
    Paint boardPaint = new Paint();
    Paint numberPaint = new Paint();
    public int leftBound = 500;
    public int rightBound = board.size * 150 + leftBound;
    public int topBound = 200;
    public int bottomBound = board.size * 150 + topBound;



    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        matchPaint.setARGB(255,41,130,255);
        matchPaint.setStyle(Paint.Style.FILL);
        boardPaint.setColor(Color.WHITE);
        boardPaint.setStyle(Paint.Style.FILL);
        numberPaint.setARGB(255,78,131,252);
        numberPaint.setTextSize(50);
        numberPaint.setTextAlign(Paint.Align.CENTER);
        matchNumberPaint.setColor(Color.WHITE);
        matchNumberPaint.setTextSize(50);
        matchNumberPaint.setTextAlign(Paint.Align.CENTER);
        setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onDraw(Canvas canvas){
        linePaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                canvas.drawRoundRect(leftBound + i * 150,topBound + j * 150,leftBound + (i + 1) * 150,
                        topBound + (j + 1) * 150,20,20,boardPaint);
                canvas.drawRoundRect(leftBound + i * 150,topBound + j * 150,leftBound + (i + 1) * 150,
                        topBound + (j + 1) * 150,20,20,linePaint);
                String number = String.valueOf(board.board[i][j]);
                canvas.drawText(number,leftBound + 75 + (i * 150), topBound + 95 + (j * 150),numberPaint);
            }
        }
    }

    public BoardModel getBoard() {
        return board;
    }

    //Checks if a valid location was pressed
    public boolean inBoard(float x, float y) {
        if( x > leftBound && x < rightBound && y > topBound && y < bottomBound) {
            return true;
        }
        return false;
    }
}




