// Origin: https://zetcode.com/javagames/movingsprites/

package Game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class TheGame extends JFrame {

    public TheGame() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());

        setTitle("Moving sprite");
        setSize(800, 800);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        	TheGame ex = new TheGame();
            ex.setVisible(true);
        });
    }

}
