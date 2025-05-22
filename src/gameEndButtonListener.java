import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class gameEndButtonListener implements ActionListener {
    private final int row;
    private final int column;

    public gameEndButtonListener(int row, int column){
        this.row = row;
        this.column = column;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUITicTacToe guiGame = new GUITicTacToe(new Game(row,column));
        guiGame.start();
    }
}