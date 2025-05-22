
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ClickListener implements ActionListener {

    private final JButton button;
    private final int row;
    private final int column;
    private final Game game;
    private final GUITicTacToe gui;


    public ClickListener(JButton button, int row, int column, Game game, GUITicTacToe gui) {
        this.button = button;
        this.row = row;
        this.column = column;
        this.game = game;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!button.getText().equals(" ")) return;

        button.setText("X");
        button.setEnabled(false);
        game.humanGUITurn(row, column, Game.GridState.HUMAN);

            if (game.checkForWin(Game.GridState.HUMAN)){
                gui.setWinUI(game.humanVicotry);
                return;
            } else if(game.gameIsTie()) {
                gui.setWinUI(game.tie);
                return;
            }

            gui.updateUiWithComputerMove(game);
            if (game.checkForWin(Game.GridState.COMPUTER)){
                gui.setWinUI(game.computerVicotry);
            } else if (game.gameIsTie()) {
                gui.setWinUI(game.tie);
        }
    }
}

