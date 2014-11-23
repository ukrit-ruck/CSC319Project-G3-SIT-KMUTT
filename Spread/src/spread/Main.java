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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author tAnniiz-PC
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int INIT1 =0;
    private static final int INIT2 = 50;
    private static final int INIT3 = 0;
    private static int w, h, p, t, x, y, b, count;
    static JSlider probb,probc,probt;
    static Thread auto;
    static Forest forest;
    static JLabel xx;
    static int counter = 0, width = 50, height = 50, probB = 0, probC = 50, probT = 100;
    public static void main(String[] args) {
        
      
        forest = new Forest(width, height, probB, probC, probT);
        final JFrame frame = new JFrame("SpreadFire");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        final JLabel step = new JLabel("Step: " + counter);
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
        JButton help = new JButton("Help");
        panel2.add(help);
        
        
       /* JButton probt = new JButton("Probtree");
        JButton probb = new JButton("Probburn");
        JButton probc = new JButton("Probcacth");*/
        
        panel2.setBackground(Color.green);
        frame.add(panel2, BorderLayout.NORTH);
        probb = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT1);
        probb.setMajorTickSpacing(20);
        probb.setMinorTickSpacing(1);
        probb.setPaintTicks(true);
        probb.setPaintLabels(true);
        
        probc = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT1);
        probc.setMajorTickSpacing(20);
        probc.setMinorTickSpacing(1);
        probc.setPaintTicks(true);
        probc.setPaintLabels(true);
        
        probt = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT1);
        probt.setMajorTickSpacing(20);
        probt.setMinorTickSpacing(1);
        probt.setPaintTicks(true);
        probt.setPaintLabels(true);
        
        JPanel sideMenu = new JPanel(new GridLayout(3, 1));
        //sideMenu.add(probb);
       /* xx = new JLabel();
        xx.setVerticalAlignment ( SwingConstants.TOP );
       sideMenu.add(xx);*/
        sideMenu.add(probb);
        sideMenu.add(probc);
        sideMenu.add(probt);

        
        frame.add(forest, BorderLayout.CENTER);
        frame.add(sideMenu, BorderLayout.EAST);
        frame.add(panel2, BorderLayout.NORTH);
        
        frame.setVisible(true);

        autospread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                auto = new Thread(){
                    @Override
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
          help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null,"Hi", "Helps", JOptionPane.PLAIN_MESSAGE);
            }
        }); 
        
        
        
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                forest.reset();
                counter = 0;
                step.setText("Step: " + counter);
                frame.validate();
                frame.repaint();
            }
        }); 
        
        size.addActionListener(new ActionListener() {
            @Override
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
       
        
        probb.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                   //xx.setText(""+probb.getValue());
                    probB = (int) source.getValue();
                }
                forest.setProbB(probB);

            }

        });
       /* probb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probB = Integer.parseInt(JOptionPane.showInputDialog("Input ProbBurn: "));
             
              while (probB>100 || probB<0){
                  probB = Integer.parseInt(JOptionPane.showInputDialog("Input ProbBurn between 0-100: "));}

                forest.setProbB(probB);
            }
        }); */
        probc.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                  //  xx.setText(""+probc.getValue());
                    probC = (int) source.getValue();
                }
                forest.setProbB(probC);

            }

        });
        
       /* probc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                probC = Integer.parseInt(JOptionPane.showInputDialog("Input Probcatch: "));
                
                while(probC>100 || probC<0){
                  probC = Integer.parseInt(JOptionPane.showInputDialog("Input Probcatch between 0-100: "));
                }
             
                
                forest.setProbC(probC);
            }
        }); */
            probt.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                  //  xx.setText(""+probc.getValue());
                    probT = (int) source.getValue();
                }
                forest.setProbB(probT);

            }

        });
        
       /* probt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                probT = Integer.parseInt(JOptionPane.showInputDialog("Input Probtree: "));
               
                while(probT>100 || probT<0){
                probT = Integer.parseInt(JOptionPane.showInputDialog("Input Probtree between 0-100: "));
                }
                
                forest.setProbT(probT);
            }
        }); */
        
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                auto.stop();
            }
        }); 
        
        man.addActionListener(new ActionListener() {
            @Override
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
