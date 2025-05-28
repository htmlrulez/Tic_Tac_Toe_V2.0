import java.util.Random;

public class Game {
    private int gameRow;
    private int gameColumn;
    private GridState[][] gameArray;

    public Game(int gameRow, int gameColumn) {
        this.gameRow = gameRow;
        this.gameColumn = gameColumn;
        this.gameArray = setGameArray();
    }

    enum WinState{
        NO_WIN(""),
        HUMAN_WIN("Human WINS - Game Over!"),
        COMPUTER_WIN("Computer WINS - Game Over!"),
        TIE("GAME IS TIE - Game Over!");

        private final String message;

        WinState(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
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


    public GridState getGameStateCellInfo(int row, int column) {
        return gameArray[row][column];
    }

    public GridState[][] setGameArray() {
        gameArray = new GridState[gameRow][gameColumn];
        fillGameArray();
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

    public boolean consoleVictory(Game.WinState state, ConsoleTicTacToe console) {
        Game.WinState result = enumWinChecker(Game.GridState.HUMAN);
        if (result == state) {
            console. drawConsoleGameBorder();
            System.out.println(state.getMessage());
            return true;
        }
        result = enumWinChecker(Game.GridState.COMPUTER);
        if (result == state) {
            console. drawConsoleGameBorder();
            System.out.println(state.getMessage());
            return true;
        }
        if (enumWinChecker(Game.GridState.HUMAN) == Game.WinState.TIE ||
                enumWinChecker(Game.GridState.COMPUTER) == Game.WinState.TIE) {
            console. drawConsoleGameBorder();
            System.out.println(Game.WinState.TIE.getMessage());
            return true;
        }
        return false;
    }

    public boolean GUIVictory(Game.WinState state, GUITicTacToe gui) {
        Game.WinState result = enumWinChecker(Game.GridState.HUMAN);
        if (result == state) {
            gui.setGameEndUIFrame(state.getMessage());
            return true;
        }
        result = enumWinChecker(Game.GridState.COMPUTER);
        if (result == state) {
            gui.setGameEndUIFrame(state.getMessage());
            return true;
        }
        if (enumWinChecker(Game.GridState.HUMAN) == Game.WinState.TIE ||
                enumWinChecker(Game.GridState.COMPUTER) == Game.WinState.TIE) {
            gui.setGameEndUIFrame(Game.WinState.TIE.getMessage());
            return true;
        }
        return false;
    }

    public WinState enumWinChecker(GridState player) {
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