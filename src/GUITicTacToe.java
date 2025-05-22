import javax.swing.*;
import java.awt.*;

public class GUITicTacToe {
    public GUITicTacToe(Game myGame) {
        this.myGame = myGame;
        this.gameRow = myGame.getGameRow();
        this.gameCol = myGame.getGameColumn();
        this.allPlayableButtons = generateAllButtons();
    }

    private final Game myGame;
    private final int gameRow;
    private final int gameCol;
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final JButton[][] allPlayableButtons;



   public void start(){

       myGame.setGameArray();
       myGame.fillGameArray();
       setUI();
   }


   public void updateUiWithComputerMove(Game game){
       int[] aiMove = game.invokeUIAI();
       int aiRow = aiMove[0];
       int aiCol = aiMove[1];
       JButton aiButton = getButton(aiRow, aiCol);
       aiButton.setText("O");
       aiButton.setEnabled(false);
   }

    public JButton getButton(int row, int col) {
        return allPlayableButtons[row][col];
    }

   public void setWinUI(String string){
        JFrame frame = new JFrame();
        frame.setSize(gameWidth, gameHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button = new JButton(string);
        frame.add(button);
        frame.setVisible(true);
        gameEndButtonListener listener = new gameEndButtonListener(gameRow, gameCol);
        button.addActionListener(listener);
   }

   private void setUI(){
       JFrame frame = new JFrame();
       frame.setSize(gameWidth, gameHeight);
       frame.setTitle("Tic Tac Toe");
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       JPanel panel = new JPanel();
       panel.setLayout(new GridLayout(gameRow, gameCol));
       generatePlayableButtonsToGrid(panel, allPlayableButtons);
       frame.add(panel);
       frame.setVisible(true);
   }

    private JButton generateButton(String sign) {
        return new JButton(sign);
    }

    private JButton[][] generateAllButtons() {
        JButton[][] allPlayableButtons = new JButton[gameRow][gameCol];

        for (int row = 0; row < gameRow; row++) {
            for (int column = 0; column < gameCol; column++) {
                JButton button = generateButton(" ");
                allPlayableButtons[row][column] = button;
            }
        }
        return allPlayableButtons;
    }

    private void generatePlayableButtonsToGrid(JPanel panel, JButton[][] buttons){
        for (int row = 0; row < gameRow; row++) {
            for (int column = 0; column < gameCol; column++) {
                JButton button = buttons[row][column];
                ClickListener myListener = new ClickListener(button, row, column, myGame, this);
                button.addActionListener(myListener);
                panel.add(button);
            }
        }
    }

}
