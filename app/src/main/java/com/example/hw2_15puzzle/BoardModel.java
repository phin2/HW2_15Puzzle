package com.example.hw2_15puzzle;


import java.util.Random;

public class BoardModel {
    protected int[][] board;
    protected Random random = new Random();
    protected  int size;
    protected  int numTiles;

    //Constructor for the board
    public BoardModel(int s){
        size = s;
        board = new int[size][size];
        numTiles = (size * size) -1; //total number of tiles
        //numbers the array from 1 to size^2 - 1
        int tiles = 0; //number of current tile;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                tiles++;
                board[i][j] = tiles;
                if(tiles == numTiles) {
                    break;
                }
            }
        }
        //shuffles the board until a solvable combination is found
        shuffle();
        boolean solvable = checkSolvable();
        while(!solvable) {
            shuffle();
            solvable = checkSolvable();
        }

    }
    //Shuffles the board
    public void shuffle() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length ; j++) {
                int newRow = random.nextInt(i + 1);
                int newCol = random.nextInt(j + 1);

                int temp = board[i][j];
                board[i][j] = board[newRow][newCol];
                board[newRow][newCol] = temp;
            }
        }

    }
    //numInversions and findEmptyRow are helper methods for checkSolvable
    //calculates the number of inversions
    private int numInversions() { //Calculates number of possible inversions
        int[] invArr = new int[size * size];
        int numInv = 0;
        int newArrayIndex = 0;

        //converts 2d array into 1d
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                invArr[newArrayIndex] = board[i][j];
                newArrayIndex++;
            }
        }
        //counts inversions
        for(int i = 0; i < invArr.length;i++) {
            for(int j = i;j < invArr.length; j++ ) {
                if(invArr[i] > invArr[j] && invArr[i] != 0 && invArr[j] != 0) {
                    numInv++;
                }
            }
        }
        return numInv;
    }
    //finds the row that the empty tile is on
    private int findEmptyRow() {
        int emptyRow = 0;
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    emptyRow = size - (i + 1) ;
                    return emptyRow;
                }
            }
        }
        return emptyRow;
    }
    //Checks if the current board is solvable
    public boolean checkSolvable() {
        boolean solvable = false;
        int emptyR = findEmptyRow() + 1; //number of row that is empty
        int inv = numInversions();
        if(size % 2 == 0) {
            //array is solvable if the empty slot is even and inversions are odd
            //or if the empty row is odd and the inversion count is even
            if((emptyR % 2 == 0 && inv % 2 != 0) || (emptyR % 2 != 0 && inv % 2 == 0)) {
                solvable = true;
            }
        }

        return solvable;
    }
    //Converts the x,y values from onTouch into correct array indexes.
    public int findIndexX(float x) {
        int xIndex = 0;
        double xPercentage = (x - 500)/(150 * size);
        if(xPercentage < .25) {
            xIndex = 0;
        } else if(xPercentage < .5) {
            xIndex = 1;
        } else if(xPercentage < .75) {
            xIndex = 2;
        } else if(xPercentage < 1) {
            xIndex = 3;
        }
        return xIndex;
    }
    public int findIndexY(float y) {
        int yIndex = 0;
        double yPercentage = (y - 200)/(150 * size);
        if(yPercentage < .25) {
            yIndex = 0;
        } else if(yPercentage < .5) {
            yIndex = 1;
        } else if(yPercentage < .75) {
            yIndex = 2;
        } else if(yPercentage < 1) {
            yIndex = 3;
        }
        return yIndex;
    }

    //checks if any empty tile is around a tile and swaps them, returns true if move was successful
    public boolean moveTile(int x, int y) {
        int temp;
        boolean moveMade = false;
        if (x != size - 1 && board[x + 1][y] == 0) {
            temp = board[x][y];
            board[x][y] = board[x + 1][y];
            board[x + 1][y] = temp;
            moveMade = true;
        } else if (x != 0 && board[x - 1][y] == 0) {
            temp = board[x][y];
            board[x][y] = board[x - 1][y];
            board[x - 1][y] = temp;
            moveMade = true;
        } else if (y != size - 1 && board[x][y + 1] == 0) {
            temp = board[x][y];
            board[x][y] = board[x][y + 1];
            board[x][y + 1] = temp;
            moveMade = true;
        } else if (y!= 0 && board[x][y - 1] == 0) {
            temp = board[x][y];
            board[x][y] = board[x][y - 1];
            board[x][y - 1] = temp;
            moveMade = true;
        }
        return moveMade;
    }

    //generates a solved board
    public int[][]solvedBoard() {
        int[][] solvedBoard = new int[size][size];
        int tiles = 0;
        int numTiles = size * size -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles++;
                solvedBoard[i][j] = tiles;
                if (tiles == numTiles) {
                    break;
                }
            }
        }
        return solvedBoard;
    }
}
