import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickListener implements ActionListener {

    private final int row;
    private final int column;
    private final GUITicTacToe gui;

    public ClickListener(int row, int column, GUITicTacToe gui) {
        this.row = row;
        this.column = column;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.processTurn(row, column);
    }
}

