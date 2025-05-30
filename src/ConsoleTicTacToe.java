import java.util.Scanner;

public class ConsoleTicTacToe implements GameUI {
  private final Game myGame;
  private Scanner myScanner = new Scanner(System.in);
  private boolean gameOver = false;
  WinState currentGameState = null;

  public ConsoleTicTacToe(Game game) {
    this.myGame = game;
  }

  public void start() {
    boolean keepPlaying = true;
    while (keepPlaying) {
      while (!gameOver) {
        myGame.generateRandomAIMove();
        drawConsoleGameBorder();
        currentGameState = myGame.evaluateWinState(GridState.COMPUTER);
        if (announceWinner()) {
          break;
        }
        humanConsoleTurn();
        currentGameState = myGame.evaluateWinState(GridState.HUMAN);
        if (announceWinner()) {
          break;
        }
      }
      keepPlaying = consoleReset();
      myGame.initializeGameGrid();
    }
  }

  private void drawConsoleGameBorder() {
    int gameRow = myGame.getGameRow();
    int gameColumn = myGame.getGameColumn();
    drawBorder(gameColumn);

    for (int row = 0; row < gameRow; row++) {
      System.out.print("|");
      for (int col = 0; col < gameColumn; col++) {
        String symbol = myGame.getGameStateCellInfo(row, col).getSymbol();
        String cell = "   " + symbol + "   ";
        System.out.print(cell + " |");
      }
      System.out.println();
      System.out.print("|");
      for (int col = 0; col < gameColumn; col++) {
        System.out.print("        |");
      }
      System.out.println();
      drawBorder(gameColumn);
    }
  }

  private void humanConsoleTurn() {
    System.out.println("Where you want to place X, input ROW, COL");
    int row = myScanner.nextInt();
    int col = myScanner.nextInt();

    if (row < 0 || row >= myGame.getGameRow() || col < 0 || col >= myGame.getGameColumn()) {
      System.out.println("Invalid input. Try again.");
      humanConsoleTurn();
      return;
    }
    if (myGame.getGameStateCellInfo(row, col) != GridState.EMPTY) {
      System.out.println("Cell is already occupied. Try again.");
      humanConsoleTurn();
      return;
    }
    myGame.setCellState(row, col, GridState.HUMAN);
  }

  private boolean announceWinner() {
    if (currentGameState != WinState.NO_WIN) {
      drawConsoleGameBorder();
      System.out.println(currentGameState.getMessage());
      return true;
    }
    return false;
  }

  private boolean consoleReset() {
    System.out.println("Do you want to play again? Y/N");
    String input = myScanner.next().trim().toUpperCase();

    while (!input.equals("Y") && !input.equals("N")) {
      System.out.println("Invalid input. Please enter Y or N:");
      input = myScanner.next().trim().toUpperCase();
    }
    return input.equals("Y");
  }

  private void drawBorder(int columns) {
    for (int i = 0; i < columns; i++) {
      System.out.print("+--------");
    }
    System.out.println("+");
  }
}
