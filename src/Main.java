import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Game game = new Game(3, 3);

    if (args[0].equals("console")) {
      ConsoleTicTacToe consoleGame = new ConsoleTicTacToe(game);
      consoleGame.start();
    } else if (args[0].equals("gui")) {
      GUITicTacToe guiGame = new GUITicTacToe(game);
      guiGame.start();
    }
  }
}