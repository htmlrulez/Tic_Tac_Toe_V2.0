import java.util.Random;

public class Game {
    private int gameRow;
    private int gameColumn;
    private GridState[][] gameArray;
    public String humanVicotry = "Human WINS - Game Over!";
    public String computerVicotry = "Computer WINS - Game Over!";
    public String tie = "GAME IS TIE - Game Over!";

    public Game(int gameRow, int gameColumn) {
        this.gameRow = gameRow;
        this.gameColumn = gameColumn;
        this.gameArray = setGameArray();
    }

    enum WinState{
        NO_WIN,
        HUMAN_WIN,
        COMPUTER_WIN,
        TIE,
    }

    enum GridState {
        EMPTY,
        HUMAN,
        COMPUTER
    }

    public int getGameRow() {
        return gameRow;
    }

    public int getGameColumn() {
        return gameColumn;
    }

    public GridState[][] getGridState() {
        return gameArray;
    }

    public GridState getGameStateCellInfo(int row, int column) {
        return gameArray[row][column];
    }

    public GridState[][] setGameArray() {
        gameArray = new GridState[gameRow][gameColumn];
        for (int i = 0; i < gameRow; i++) {
            for (int j = 0; j < gameColumn; j++) {
                gameArray[i][j] = GridState.EMPTY;
            }
        }
        return gameArray;
    }

    public void setCellState(int row, int col, GridState state) {
        gameArray[row][col] = state;
    }

    public int[] invokeAI() {
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

    public void fillGameArray() {
        for (int i = 0; i < gameRow; i++) {
            for (int j = 0; j < gameColumn; j++) {
                gameArray[i][j] = GridState.EMPTY;
            }
        }
    }

    public WinState checkForWinEnum(GridState player) {
        if (checkRows(player) || checkColumns(player) || checkDiagonals(player)) {
            return player == GridState.HUMAN ? WinState.HUMAN_WIN : WinState.COMPUTER_WIN;
        }
        if (gameIsTie()) {
            return WinState.TIE;
        }
        return WinState.NO_WIN;
    }

    private boolean checkRows(GridState player) {
        for (int i = 0; i < gameRow; i++) {
            boolean win = true;
            for (int j = 0; j < gameColumn; j++) {
                if (gameArray[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(GridState player) {
        for (int i = 0; i < gameColumn; i++) {
            boolean win = true;
            for (int j = 0; j < gameRow; j++) {
                if (gameArray[j][i] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    private boolean checkDiagonals(GridState player) {
        boolean win = true;
        for (int i = 0; i < gameRow; i++) {
            if (gameArray[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return true;

        win = true;
        for (int i = 0; i < gameRow; i++) {
            if (gameArray[i][gameColumn - 1 - i] != player) {
                win = false;
                break;
            }
        }
        return win;
    }

    public boolean gameIsTie() {
        for (int row = 0; row < gameRow; row++) {
            for (int column = 0; column < gameColumn; column++) {

                if (gameArray[row][column].equals(GridState.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}