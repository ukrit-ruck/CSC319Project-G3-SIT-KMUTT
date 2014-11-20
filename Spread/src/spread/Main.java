/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author anfeww
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static Thread auto;
    static Forest forest;
    static int counter = 0, width = 10, height = 10, probB = 0, probC = 100, probT = 100;
    public static void main(String[] args) {
        

        forest = new Forest(width, height, probB, probC, probT);
        JFrame frame = new JFrame("SpreadFire");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton man = new JButton("Manual");
        panel2.add(man);
        JButton pause = new JButton("Pause");
        panel2.add(pause);
        JButton autospread = new JButton("Auto spraed");
        panel2.add(autospread);
        JButton size = new JButton("Set Size");
        panel2.add(size);
        JButton reset = new JButton("Reset");
        panel2.add(reset);
        JButton probt = new JButton("Probtree");
        panel2.add(probt);
        JButton probb = new JButton("Probburn");
        panel2.add(probb);
        JButton probc = new JButton("Probcacth");
        panel2.add(probc);
        panel2.setBackground(Color.green);
        frame.add(panel2);
        frame.add(panel2, BorderLayout.NORTH);
        
        frame.add(forest, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.NORTH);
        
        frame.setVisible(true);

        autospread.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                auto = new Thread(){
                    public void run(){
                        while(!forest.isEnd()){
                            forest.spread();
                            counter++;
                            System.out.println(counter);
                        }
                    }
                };
                auto.start();
            }
        }); 
        
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                forest.reset();
            }
        }); 
        
        size.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JTextField widthTF = new JTextField();
                JTextField heightTF = new JTextField();
                final JComponent[] inputs = new JComponent[] {
                        new JLabel("Width:"),
                        widthTF,
                        new JLabel("Height:"),
                        heightTF,
                };
                JOptionPane.showMessageDialog(null, inputs, "Size Setting", JOptionPane.PLAIN_MESSAGE);
                
                width = Integer.parseInt(widthTF.getText());
                height = Integer.parseInt(heightTF.getText());
                forest.setSize(width, height);
            }
        }); 
        
        probb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probB = Integer.parseInt(JOptionPane.showInputDialog("Input ProbBurn: "));
                forest.setProbB(probB);
            }
        }); 
        
        probc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probC = Integer.parseInt(JOptionPane.showInputDialog("Input Probcatch: "));
                forest.setProbB(probC);
            }
        }); 
        
        probt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probT = Integer.parseInt(JOptionPane.showInputDialog("Input Probtree: "));
                forest.setProbB(probT);
            }
        }); 
        
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                auto.stop();
            }
        }); 
        
        man.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(!forest.isEnd()){
                    forest.spread();
                }
            }
        }); 
    }  
}
