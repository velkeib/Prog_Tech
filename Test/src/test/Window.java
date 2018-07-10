package test;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class Window extends JFrame {
    
    
    
    public Window(){
        setTitle("4-es játék");
        setSize(400, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }

        });
        
    }
    
    
    private void showExitConfirmation() {
        UIManager.put("OptionPane.yesButtonText", "Igen");
        UIManager.put("OptionPane.noButtonText", "Nem");
        int n = JOptionPane.showConfirmDialog(this, "Valóban ki akar lépni?",
                "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
    
    protected void doUponExit() {
        this.dispose();
    }

}
