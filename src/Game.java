import java.util.Random;

public class Game {
  private final int gameRow;
  private final int gameColumn;
  private GridState[][] gameArray;

  public Game(int gameRow, int gameColumn) {
    this.gameRow = gameRow;
    this.gameColumn = gameColumn;
    this.gameArray = createAndPopulateGameGrid();
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

  public GridState[][] createAndPopulateGameGrid() {
    gameArray = new GridState[gameRow][gameColumn];
    clearGameGrid();
    return gameArray;
  }

  public void setCellState(int row, int col, GridState state) {
    gameArray[row][col] = state;
  }

  public AIMove generateRandomAIMove() {
    AIMove aiMove;
    Random randNumber = new Random();

    while (true) {
      int aiRow = randNumber.nextInt(gameRow);
      int aiColumn = randNumber.nextInt(gameColumn);

      if (gameArray[aiRow][aiColumn] == GridState.EMPTY) {
        gameArray[aiRow][aiColumn] = GridState.COMPUTER;
        aiMove = new AIMove(aiRow, aiColumn);
        return aiMove;
      }
    }
  }

  public void clearGameGrid() {
    for (int i = 0; i < gameRow; i++) {
      for (int j = 0; j < gameColumn; j++) {
        gameArray[i][j] = GridState.EMPTY;
      }
    }
  }

  public WinState evaluateWinState(GridState playerState) {
    if (checkGridRows(playerState)
        || checkGridColumns(playerState)
        || checkGridDiagonals(playerState)) {
      return playerState == GridState.HUMAN ? WinState.HUMAN_WIN : WinState.COMPUTER_WIN;
    }
    if (isGameTie()) {
      return WinState.TIE;
    }
    return WinState.NO_WIN;
  }

  private boolean checkGridRows(GridState player) {
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

  private boolean checkGridColumns(GridState player) {
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

  private boolean checkGridDiagonals(GridState player) {
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

  public boolean isGameTie() {
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
