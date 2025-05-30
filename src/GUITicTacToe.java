import javax.swing.*;
import java.awt.*;

public class GUITicTacToe {
  private final int gameWidth = 800;
  private final int gameHeight = 600;
  private final Game myGame;
  private JPanel gamePanel;
  private JFrame gameFrame;
  private JButton[][] allPlayableButtons;
  private JFrame gameEndFrame = null;
  private WinState result = null;

  public GUITicTacToe(Game myGame) {
    this.myGame = myGame;
    this.gamePanel = new JPanel();
    this.gameFrame = new JFrame();
  }

  public void start() {
    setupClickableButtons();
    createGameUI();
  }

  public void processTurn(int row, int col) {
    if (!checkValidMove(row, col)) {
      return;
    }
    setHumanUIMove(row, col);
    updateGUIButton(row, col, GridState.HUMAN);

    result = myGame.evaluateWinState(GridState.HUMAN);
    if (!renderWinFrame()) {
      computerMove();
      result = myGame.evaluateWinState(GridState.COMPUTER);
      renderWinFrame();
    }
  }

  public void restartGame() {
    gameEndFrame.dispose();
    myGame.clearGameGrid();
    gamePanel.removeAll();
    setupClickableButtons();
    gamePanel.setLayout(new GridLayout(myGame.getGameRow(), myGame.getGameColumn()));
    gamePanel.revalidate();
    gamePanel.repaint();
  }

  private void setupClickableButtons() {
    allPlayableButtons = new JButton[myGame.getGameRow()][myGame.getGameColumn()];
    for (int row = 0; row < myGame.getGameRow(); row++) {
      for (int column = 0; column < myGame.getGameColumn(); column++) {
        ClickListener myListener = new ClickListener(row, column, this);
        JButton button = new JButton(" ");
        button.addActionListener(myListener);
        allPlayableButtons[row][column] = button;
        gamePanel.add(button);
      }
    }
  }

  private void createGameUI() {
    gameFrame.setSize(gameWidth, gameHeight);
    gameFrame.setTitle("Tic Tac Toe");
    gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    gamePanel.setLayout(new GridLayout(myGame.getGameRow(), myGame.getGameColumn()));
    gameFrame.add(gamePanel);
    gameFrame.setVisible(true);
  }

  private boolean renderWinFrame() {
    if (result != WinState.NO_WIN) {
      gameEndFrame = new JFrame();
      gameEndFrame.setSize(gameWidth, gameHeight);
      gameEndFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      JButton button = new JButton(result.getMessage());
      button.addActionListener(new GameEndButtonListener(this));
      gameEndFrame.add(button);
      gameEndFrame.setLocationRelativeTo(gameFrame);
      gameEndFrame.setVisible(true);
      return true;
    }
    return false;
  }

  private boolean checkValidMove(int row, int col) {
    return myGame.getGameStateCellInfo(row, col) == GridState.EMPTY;
  }

  private void setHumanUIMove(int row, int col) {
    myGame.setCellState(row, col, GridState.HUMAN);
  }

  private JButton getButton(int row, int col) {
    return allPlayableButtons[row][col];
  }

  private void updateGUIButton(int row, int col, GridState symbol) {
    JButton button = getButton(row, col);
    button.setText(symbol.getSymbol());
    button.setEnabled(false);
  }

  private void computerMove() {
    AIMove aiMove = myGame.generateRandomAIMove();
    updateGUIButton(aiMove.x, aiMove.y, GridState.COMPUTER);
  }
}
