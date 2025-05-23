public class Main {
    public static void main(String[] args) {

        //ConsoleTicTacToe consoleGame = new ConsoleTicTacToe(new Game(3, 3));
        //consoleGame.start();

        GUITicTacToe guiGame = new GUITicTacToe(new Game(3, 3));
        guiGame.start();


    }
}