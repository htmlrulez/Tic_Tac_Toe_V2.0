import javax.swing.*;
import java.awt.*;

public class GUITicTacToe {
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final Game myGame;
    private JPanel gamePanel;
    private JFrame gameFrame;
    private JButton[][] allPlayableButtons;

    public GUITicTacToe(Game myGame) {
        this.myGame = myGame;
        this.gamePanel = setGamePanel();
        this.gameFrame = setGameFrame();
    }

   public void start(){
       generatePlayableButtons();
       setUI();

   }

    public JButton getButton(int row, int col) {
        return allPlayableButtons[row][col];
    }


    public void userClickHandler(int row, int col) {
        if(!checkValidMove(row, col)){
            return;
        }
        setHumanUIMove(row, col);
        updateGUIButton(row, col, "X");
    }

    public void processTurn(int row, int col) {
        userClickHandler(row, col);
        if (!myGame.GUIVictory(Game.WinState.HUMAN_WIN, this)) {
            computerMove();
            myGame.GUIVictory(Game.WinState.COMPUTER_WIN, this);
        }
    }

    public void computerMove(){
        int[] aiMove = myGame.invokeAI();
        updateGUIButton(aiMove[0], aiMove[1], "O");

    }

    public void setGameEndUIFrame(String message) {
        JFrame winFrame = new JFrame();
        winFrame.setSize(gameWidth, gameHeight);
        winFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JButton button = new JButton(message);
        button.addActionListener(new gameEndButtonListener(this, winFrame));
        winFrame.add(button);
        winFrame.setLocationRelativeTo(gameFrame);
        winFrame.setVisible(true);
    }

    public void restartGame(JFrame winFrame) {
        winFrame.dispose();
        myGame.fillGameArray();
        gamePanel.removeAll();
        generatePlayableButtons();
        gamePanel.setLayout(new GridLayout(myGame.getGameRow(), myGame.getGameColumn()));
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private JPanel setGamePanel(){
        return new JPanel();
    }

    private JFrame setGameFrame(){
        return new JFrame();
    }

    private void setUI(){
        gameFrame.setSize(gameWidth, gameHeight);
        gameFrame.setTitle("Tic Tac Toe");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel.setLayout(new GridLayout(myGame.getGameRow(), myGame.getGameColumn()));
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
    }

    private void generatePlayableButtons() {
        allPlayableButtons = new JButton[myGame.getGameRow()][myGame.getGameColumn()];

        for (int row = 0; row < myGame.getGameRow(); row++) {
            for (int column = 0; column < myGame.getGameColumn(); column++) {
                ClickListener myListener = new ClickListener(row, column, this);
                JButton button = generateButton(" ");
                button.addActionListener(myListener);
                allPlayableButtons[row][column] = button;
                gamePanel.add(button);
            }
        }
    }

    private boolean checkValidMove(int row, int col) {
       return myGame.getGameStateCellInfo(row, col) == Game.GridState.EMPTY;
    }

    private void setHumanUIMove(int row, int col) {
        myGame.setCellState(row, col, Game.GridState.HUMAN);
    }

    private void updateGUIButton(int row, int col, String symbol) {
        JButton button = getButton(row, col);
        button.setText(symbol);
        button.setEnabled(false);
    }

    private JButton generateButton(String sign) {
        return new JButton(sign);
    }
}