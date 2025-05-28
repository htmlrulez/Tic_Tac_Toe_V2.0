import java.util.Scanner;

public class ConsoleTicTacToe {
    private final Game myGame;
    private Scanner myScanner = new Scanner(System.in);

    public ConsoleTicTacToe(Game game) {
        this.myGame = game;
    }

    public void start() {
        boolean keepPlaying = true;
        while (keepPlaying) {
            boolean gameOver = false;
            while (!gameOver) {
                drawConsoleGameBorder();
                humanConsoleTurn();
                if ( myGame.consoleVictory(Game.WinState.HUMAN_WIN, this)){
                    break;
                }
                if ( myGame.consoleVictory(Game.WinState.TIE, this)){
                    break;
                }
                myGame.invokeAI();
                if ( myGame.consoleVictory(Game.WinState.COMPUTER_WIN, this)){
                    break;
                }
                if ( myGame.consoleVictory(Game.WinState.TIE, this)){
                    break;
                }
            }
            keepPlaying = consoleReset();
            myGame.setGameArray();
        }
    }

    private String consoleCellSymbol(Game.GridState state){
        return switch (state){
            case EMPTY -> " ";
            case HUMAN -> "X";
            case COMPUTER -> "O";
        };
    }

    private void drawBorder(int columns) {
        for (int i = 0; i < columns; i++) {
            System.out.print("+--------");
        }
        System.out.println("+");
    }


    public void drawConsoleGameBorder() {
        int gameRow = myGame.getGameRow();
        int gameColumn = myGame.getGameColumn();

        drawBorder(gameColumn);

        for (int row = 0; row < gameRow; row++) {
            System.out.print("|");
            for (int col = 0; col < gameColumn; col++) {
                String symbol = consoleCellSymbol(myGame.getGameStateCellInfo(row, col));
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

    public void humanConsoleTurn() {
        System.out.println("Where you want to place X, input ROW, COL");
        int row = myScanner.nextInt();
        int col = myScanner.nextInt();

        if (row < 0 || row >= myGame.getGameRow() || col < 0 || col >= myGame.getGameColumn()) {
            System.out.println("Invalid input. Try again.");
            humanConsoleTurn();
            return;
        }
        if (myGame.getGameStateCellInfo(row, col) != Game.GridState.EMPTY) {
            System.out.println("Cell is already occupied. Try again.");
            humanConsoleTurn();
            return;
        }
        myGame.setCellState(row, col, Game.GridState.HUMAN);
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
}
