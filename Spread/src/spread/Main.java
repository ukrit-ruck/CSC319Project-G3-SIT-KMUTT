/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
    static int counter = 0, width = 50, height = 50, probB = 0, probC = 50, probT = 100;
    public static void main(String[] args) {
        
        //create frame to input forest into frame.
        forest = new Forest(width, height, probB, probC, probT);
        JFrame frame = new JFrame("SpreadFire");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create panel into the frame
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel step = new JLabel("Step: " + counter);
        panel2.add(step);
        JButton man = new JButton("Manual");
        panel2.add(man);
        JButton autospread = new JButton("Auto spraed");
        panel2.add(autospread);
        JButton pause = new JButton("Pause");
        panel2.add(pause);
        JButton size = new JButton("Set Size");
        panel2.add(size);
        JButton reset = new JButton("Reset");
        panel2.add(reset);
        //create button of Probtree , probburn ,and probcacth
        JButton probt = new JButton("Probtree");
        JButton probb = new JButton("Probburn");
        JButton probc = new JButton("Probcacth");
        
        panel2.setBackground(Color.green);
        frame.add(panel2, BorderLayout.NORTH);
        
        JPanel sideMenu = new JPanel(new GridLayout(3, 1));
        sideMenu.add(probb);
        sideMenu.add(probc);
        sideMenu.add(probt);
        
        frame.add(forest, BorderLayout.CENTER);
        frame.add(sideMenu, BorderLayout.EAST);
        frame.add(panel2, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        //create autospread action
        autospread.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                auto = new Thread(){
                    public void run(){
                        while(!forest.isEnd()){
                            forest.spread();
                            counter++;
                            step.setText("Step: " + counter);
                            frame.validate();
                            frame.repaint();
                        }
                    }
                };
                auto.start();
            }
        }); 
        //create reset action
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                forest.reset();
                counter = 0;
                step.setText("Step: " + counter);
                frame.validate();
                frame.repaint();
            }
        }); 
        // create set size action
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
        //create prob burn action
        probb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probB = Integer.parseInt(JOptionPane.showInputDialog("Input ProbBurn: "));
                forest.setProbB(probB);
            }
        }); 
        //create prob catch action
        probc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probC = Integer.parseInt(JOptionPane.showInputDialog("Input Probcatch: "));
                forest.setProbC(probC);
            }
        }); 
        //create prob tree action
        probt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probT = Integer.parseInt(JOptionPane.showInputDialog("Input Probtree: "));
                forest.setProbT(probT);
            }
        }); 
        //create pause action
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                auto.stop();
            }
        }); 
        //create manual action
        man.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(!forest.isEnd()){
                    forest.spread();
                    counter++;
                }
                step.setText("Step: " + counter);
                frame.validate();
                frame.repaint();
            }
        }); 
    }  
}
