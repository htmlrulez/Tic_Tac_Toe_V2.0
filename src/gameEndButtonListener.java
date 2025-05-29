import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameEndButtonListener implements ActionListener {
  private final GUITicTacToe gui;

  public gameEndButtonListener(GUITicTacToe gui) {
    this.gui = gui;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    gui.restartGame();
  }
}
