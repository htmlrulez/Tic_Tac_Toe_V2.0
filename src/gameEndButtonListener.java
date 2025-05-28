import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class gameEndButtonListener implements ActionListener {
    private final GUITicTacToe gui;
    private final JFrame winFrame;

    public gameEndButtonListener(GUITicTacToe gui, JFrame winFrame){
        this.gui = gui;
        this.winFrame = winFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.restartGame(winFrame);
    }
}