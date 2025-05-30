import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEndButtonListener implements ActionListener {
  private final GUITicTacToe gui;

  public GameEndButtonListener(GUITicTacToe gui) {
    this.gui = gui;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    gui.restartGame();
  }
}
