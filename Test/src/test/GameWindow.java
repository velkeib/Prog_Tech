package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.*;

public class GameWindow extends Window {
    private int size;
    private JLabel label;
    private Model model;
    private MainWindow window;
    private JButton[][] buttons;
    
    
    public GameWindow(int size, MainWindow window) {
        this.size = size;
        this.window = window;
        model = new Model(size);
        label = new JLabel();
        buttons = new JButton[size][size];
        window.getWindowList().add(this);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu newGame = new JMenu("Új játék");
        menuBar.add(newGame);
        JMenuItem harom = new JMenuItem("3x3");
        JMenuItem ot = new JMenuItem("5x5");
        JMenuItem het = new JMenuItem("7x7");
        newGame.add(harom);
        newGame.add(ot);
        newGame.add(het);
        
        harom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showRestart(3);
            }
            
        });
        
        ot.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showRestart(5);
            }
            
        });
        
        het.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showRestart(7);
            }
            
        });
        
        
        JPanel top = new JPanel();

        top.add(label);
        label.setText("Aktuális játékos: " + model.getPlayer() + "       Piros: " + model.getP1() + " pont, Kék: " + model.getP2() + " pont.");
         
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(size,size));
        
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(contentPane, i, j); 
                
            }
        }
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPane, BorderLayout.CENTER);
        getContentPane().add(top, BorderLayout.NORTH);
        
    
    
    }
    
    private void addButton(JPanel panel, final int i, final int j) {
        String fe = Integer.toString(model.getTable(i,j));
        final JButton button = new JButton(fe);
        buttons[i][j] = button;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newValue = model.step(i, j);
                String szam = Integer.toString(newValue);
                button.setText(szam);
                if(model.getScorer(i, j)==Player.PIROS){
                    buttons[i][j].setBackground(Color.RED);
                }
                if(model.getScorer(i, j)==Player.KEK){
                    buttons[i][j].setBackground(Color.BLUE);
                }
                if(i<size-1){
                    buttons[i+1][j].setText(Integer.toString(model.getTable(i+1,j)));
                    if(model.getScorer(i+1, j)==Player.PIROS){
                        buttons[i+1][j].setBackground(Color.RED);
                    }
                    if(model.getScorer(i+1, j)==Player.KEK){
                        buttons[i+1][j].setBackground(Color.BLUE);
                    }
                }
                if(i>0){
                    buttons[i-1][j].setText(Integer.toString(model.getTable(i-1,j)));
                    if(model.getScorer(i-1, j)==Player.PIROS){
                        buttons[i-1][j].setBackground(Color.RED);
                    }
                    if(model.getScorer(i-1, j)==Player.KEK){
                        buttons[i-1][j].setBackground(Color.BLUE);
                    }
                }
                if(j<size-1){
                    buttons[i][j+1].setText(Integer.toString(model.getTable(i,j+1)));
                    if(model.getScorer(i, j+1)==Player.PIROS){
                        buttons[i][j+1].setBackground(Color.RED);
                    }
                    if(model.getScorer(i, j+1)==Player.KEK){
                        buttons[i][j+1].setBackground(Color.BLUE);
                    }
                }
                if(j>0){
                    buttons[i][j-1].setText(Integer.toString(model.getTable(i,j-1)));
                    if(model.getScorer(i, j-1)==Player.PIROS){
                        buttons[i][j-1].setBackground(Color.RED);
                    }
                    if(model.getScorer(i, j-1)==Player.KEK){
                        buttons[i][j-1].setBackground(Color.BLUE);
                    }
                }
                updateLabelText();
                
                if((model.getP1() + model.getP2()) == (size*size)){
                    showGameOverMessage();
                }
            }
        });
        panel.add(button);
    }
    
    private void showGameOverMessage() {
        
        if(model.getP1()>model.getP2()){
            JOptionPane.showMessageDialog(this,"Játék vége. A Piros játékos nyert","Játék vége",1);
            newGame(size);
        }if(model.getP1()<model.getP2()){
            JOptionPane.showMessageDialog(this, "Játék vége. A Kék játékos nyert", "Játék vége",1);
            newGame(size);
        }
    }
    
    private void updateLabelText() {
        label.setText("Aktuális játékos: " + model.getPlayer() + "       Piros: " + model.getP1() + " pont, Kék: " + model.getP2() + " pont.");
    }
    
     private void newGame(int i) {
        GameWindow newWindow = new GameWindow(i, window);
        newWindow.setVisible(true);
        
        this.dispose();
        window.getWindowList().remove(this);
     }
     
     private void showRestart(int i) {
        UIManager.put("OptionPane.yesButtonText", "Igen");
        UIManager.put("OptionPane.noButtonText", "Nem");
        int n = JOptionPane.showConfirmDialog(this, "Biztos új játékot akar kezdeni?",
                "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            newGame(i);
        }
    }
    
}
