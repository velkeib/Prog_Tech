package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends Window{
    
    private List<Window> gameWindows = new ArrayList<>();
    private JLabel label;
    private JLabel gombok;
    
    public MainWindow() {
        
        JButton small = new JButton();
        small.setText("3 x 3");
        small.addActionListener(getActionListener(3));
        
        JButton medium = new JButton();
        medium.setText("5 x 5");
        medium.addActionListener(getActionListener(5));

        JButton large = new JButton();
        large.setText("7 x 7");
        large.addActionListener(getActionListener(7));
        
        JPanel top = new JPanel();
        label = new JLabel();
        top.setVisible(true);
        top.add(label);
        label.setText("Válasszanak pályaméretet:");
        add(top, BorderLayout.NORTH);
        
        JPanel bottom = new JPanel();
        bottom.add(small);
        bottom.add(medium);
        bottom.add(large);
        bottom.setVisible(true);

        add(bottom, BorderLayout.CENTER);
    
        

    }
    
    private ActionListener getActionListener(final int size) {
        return new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent e) {
                Model model = new Model(size);
                GameWindow window = new GameWindow(size, MainWindow.this);
                
                window.setVisible(true);
                gameWindows.add(window);
            }
            
        };
    }
    
    public List<Window> getWindowList() {
        return gameWindows;
    }
}
