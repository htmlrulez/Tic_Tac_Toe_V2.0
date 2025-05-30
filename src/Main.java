import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Game game = new Game(3, 3);

    GameUI gameUI =
        (args.length > 0 && args[0].equals("--gui"))
            ? new GUITicTacToe(game)
            : new ConsoleTicTacToe(game);
    gameUI.start();
  }
}
