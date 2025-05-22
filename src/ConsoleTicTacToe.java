import java.util.Scanner;

public class ConsoleTicTacToe {
    public ConsoleTicTacToe(Game game) {
        this.myGame = game;
    }

    private final Game myGame;


    public void start(){
        myGame.setGameArray();
        myGame.fillGameArray();

        Boolean gameOver = false;
        while (gameOver != true){
            printGameState();
            myGame.humanConsoleTurn();

            if (myGame.checkForWin(Game.GridState.HUMAN)){
                System.out.println("Human Wins");
                if (consoleReset().equals("Y")){
                    start();
                }
                gameOver = true;
            } else if (myGame.gameIsTie()) {
                System.out.println("Game is Tie");
                if (consoleReset().equals("Y")){
                    start();
                }
                gameOver = true;
            }

            myGame.invokeDumbAI();

            if (myGame.checkForWin(Game.GridState.COMPUTER)){
                System.out.println("Computer Wins");
                if (consoleReset().equals("Y")){
                    start();
                }
                gameOver = true;
            } else if (myGame.gameIsTie()) {
                System.out.println("Game is Tie");
                if (consoleReset().equals("Y")){
                    start();
                }
                gameOver = true;
            }
        }
    }

    private void printGameState(){
        int gameRow = myGame.getGameRow();
        int gameColumn = myGame.getGameColumn();

        for (int row = 0; row < gameRow; row++) {
            for (int col = 0; col < gameColumn; col++) {
                System.out.print(myGame.getGameStateCellInfo(row, col) + " ");
            }
            System.out.println(" ");
        }
    }

    public String consoleReset() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Do you want to play again?  Y//N");
        String input = myScanner.nextLine().toUpperCase();

        if (input.equals("Y")) {
            myGame.fillGameArray();
            return "Y";
        }
        return "N";
    }

}
