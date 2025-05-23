import java.util.Scanner;

public class ConsoleTicTacToe {
    private int gameRow;
    private int gameColumn;
    private final Game myGame;
    private Scanner myScanner = new Scanner(System.in);

    public ConsoleTicTacToe(Game game) {
        this.myGame = game;
        this.gameRow = game.getGameRow();
        this.gameColumn = game.getGameColumn();
    }

    public void start() {
        boolean keepPlaying = true;
        while (keepPlaying) {
            myGame.setGameArray();

            boolean gameOver = false;
            while (!gameOver) {
                consoleState();
                humanConsoleTurn();
                Game.WinState result = myGame.checkForWinEnum(Game.GridState.HUMAN);
                switch (result) {
                    case HUMAN_WIN:
                        consoleState();
                        System.out.println("Human Wins");
                        gameOver = true;
                        break;
                    case TIE:
                        consoleState();
                        System.out.println("Game is Tie");
                        gameOver = true;
                        break;
                }
                if (gameOver) {
                    break;
                }

                myGame.invokeAI();
                result = myGame.checkForWinEnum(Game.GridState.COMPUTER);
                switch (result) {
                    case COMPUTER_WIN:
                        consoleState();
                        System.out.println("Computer Wins");
                        gameOver = true;
                        break;
                    case TIE:
                        consoleState();
                        System.out.println("Game is Tie");
                        gameOver = true;
                        break;
                }
            }
        keepPlaying = consoleReset();
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


    private void consoleState() {
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

        if (row < 0 || row >= gameRow || col < 0 || col >= gameColumn) {
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
