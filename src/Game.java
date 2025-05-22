import javax.swing.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class Game {
    public Game(int gameRow, int gameColumn) {
        this.gameRow = gameRow;
        this.gameColumn = gameColumn;
    }

    private final int gameRow;
    private final int gameColumn;
    private int selectedRow;
    private int selectedColumn;
    private GridState[][] gameArray; // USE ENUM INSTEAD OF 0 AND 1 AND 2 EMPTY HUMAN PLAYER CHOICE AI CHOICE
    private Scanner myScanner = new Scanner(System.in);
    public String humanVicotry = "Human WINS - Game Over!";
    public String computerVicotry = "Computer WINS - Game Over!";
    public String tie = "GAME IS TIE - Game Over!";


    enum GridState {
        EMPTY,
        HUMAN,
        COMPUTER
    }

    public void humanConsoleTurn() {
        System.out.println("Where you want to place X, input ROW, COL");
        selectedRow = myScanner.nextInt();
        selectedColumn = myScanner.nextInt();
        gameArray[selectedRow][selectedColumn] = GridState.HUMAN;
    }

    public void humanGUITurn(int row, int col, GridState player) {
        if (gameArray[row][col] == GridState.EMPTY) {
            gameArray[row][col] = player;
        }
    }


    public void invokeDumbAI() {
        Random randNumber = new Random();
        boolean running = true;

        while (running) {
            int aiRow = randNumber.nextInt(gameRow);
            int aiColumn = randNumber.nextInt(gameColumn);

            if (gameArray[aiRow][aiColumn] == GridState.EMPTY) {
                gameArray[aiRow][aiColumn] = GridState.COMPUTER;
                running = false;
            }
        }
    }

    public int[] invokeUIAI() {
        Random randNumber = new Random();
        while (true) {
            int aiRow = randNumber.nextInt(gameRow);
            int aiColumn = randNumber.nextInt(gameColumn);

            if (gameArray[aiRow][aiColumn] == GridState.EMPTY) {
                gameArray[aiRow][aiColumn] = GridState.COMPUTER;
                return new int[]{aiRow, aiColumn};
            }
        }
    }


        public void fillGameArray () {
            for (int i = 0; i < gameRow; i++) {
                for (int j = 0; j < gameColumn; j++) {
                    gameArray[i][j] = GridState.EMPTY;
                }
            }
        }


        public boolean checkForWin (GridState player){
            boolean gameOver = false;

            for (int i = 0; i < gameRow; i++) {
                boolean xWins = true;

                for (int j = 0; j < gameColumn; j++) {

                    if (gameArray[i][j] != player) {
                        xWins = false;
                        break;
                    }
                }
                if (xWins) {
                    gameOver = true;
                }
            }

            for (int i = 0; i < gameColumn; i++) {
                boolean xWins = true;

                for (int j = 0; j < gameRow; j++) {
                    if (gameArray[j][i] != player) {
                        xWins = false;
                        break;
                    }
                }
                if (xWins) {
                    gameOver = true;
                }
            }

            for (int i = 0; i < gameRow; i++) {
                boolean xWins = true;
                int counter = 0;

                for (int j = 0; j < gameColumn; j++) {
                    if (gameArray[counter][counter] != player) {
                        xWins = false;
                        break;
                    }
                    counter++;
                }
                if (xWins) {
                    gameOver = true;
                }
            }

            for (int i = 0; i < gameRow; i++) {
                boolean xWins = true;
                int counter = 0;
                int counter2 = gameRow - 1;

                for (int j = 0; j < gameColumn; j++) {
                    if (gameArray[counter][counter2] != player) {
                        xWins = false;
                        break;
                    }
                    counter++;
                    counter2--;
                }
                if (xWins) {
                    gameOver = true;
                }
            }
            return gameOver;
        }

        public boolean gameIsTie () {
            for (int row = 0; row < gameRow; row++) {
                for (int column = 0; column < gameColumn; column++) {

                    if (gameArray[row][column].equals(GridState.EMPTY)) {
                        return false;
                    }
                }
            }
            return true;
        }


        public void setGameArray () {
            gameArray = new GridState[gameRow][gameColumn];
        }

        public int getGameRow () {
            return gameRow;
        }

        public int getGameColumn () {
            return gameColumn;
        }

        public GridState getGameStateCellInfo ( int row, int column){
            return gameArray[row][column];
        }


    }

