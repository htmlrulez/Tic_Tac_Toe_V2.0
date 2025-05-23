import javax.swing.*;
import java.awt.*;

public class GUITicTacToe {
    private final int gameRow;
    private final int gameCol;
    private final int gameWidth = 800;
    private final int gameHeight = 600;
    private final Game myGame;
    private JPanel gamePanel;
    private JFrame gameFrame;
    private JButton[][] allPlayableButtons;

    public GUITicTacToe(Game myGame) {
        this.myGame = myGame;
        this.gameRow = myGame.getGameRow();
        this.gameCol = myGame.getGameColumn();
        this.gamePanel = setGamePanel();
        this.gameFrame = setGameFrame();
    }

   public void start(){
        generateAllButtons();
        setUI();
   }

    public JButton getButton(int row, int col) {
        return allPlayableButtons[row][col];
    }

    public void userClickHandler(int row, int col) {
        if(!validMove(row, col)){
            return;
        }
        setHumanUIMove(row, col);
        updateGUIButton(row, col, "X");
    }

    public void processTurn(int row, int col) {
        userClickHandler(row, col);
        if (!checkGameStatusAndUpdateUI(Game.GridState.HUMAN)) {
            computerMove();
            checkGameStatusAndUpdateUI(Game.GridState.COMPUTER);
        }
    }

    public void computerMove(){
        int[] aiMove = myGame.invokeAI();
        updateGUIButton(aiMove[0], aiMove[1], "O");

    }

    public boolean checkGameStatusAndUpdateUI(Game.GridState state) {
        Game.WinState result = myGame.checkForWinEnum(state);
        switch(result) {
            case HUMAN_WIN:
                setWinUI(myGame.humanVicotry);
                return true;
            case COMPUTER_WIN:
                setWinUI(myGame.computerVicotry);
                return true;
            case TIE:
                setWinUI(myGame.tie);
                return true;
            default:
                return false;
        }
    }

    public void setWinUI(String message) {
        JFrame winFrame = new JFrame();
        winFrame.setSize(gameWidth, gameHeight);
        winFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JButton button = new JButton(message);
        button.addActionListener(new gameEndButtonListener(this, winFrame));
        winFrame.add(button);
        winFrame.setLocationRelativeTo(gameFrame);
        winFrame.setVisible(true);
    }

    public void restartGame() {
        myGame.fillGameArray();
        gamePanel.removeAll();
        generateAllButtons();
        gamePanel.setLayout(new GridLayout(gameRow, gameCol));
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
        gamePanel.setLayout(new GridLayout(gameRow, gameCol));
        gameFrame.add(gamePanel);
        gameFrame.setVisible(true);
    }

    private void generateAllButtons() {
        allPlayableButtons = new JButton[gameRow][gameCol];

        for (int row = 0; row < gameRow; row++) {
            for (int column = 0; column < gameCol; column++) {
                ClickListener myListener = new ClickListener(row, column, this);
                JButton button = generateButton(" ");
                button.addActionListener(myListener);
                allPlayableButtons[row][column] = button;
                gamePanel.add(button);
            }
        }
    }

    private boolean validMove(int row, int col) {
        return myGame.getGridState()[row][col] == Game.GridState.EMPTY;
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