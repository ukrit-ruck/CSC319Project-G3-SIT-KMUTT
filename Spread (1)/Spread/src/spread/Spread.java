/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author tAnniiz-PC
 */
public class Spread {

    /**
     * @param args the command line arguments
     */
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int INIT =0;
    static JSlider probb,probc,probt;
    static Thread auto;
    static Forest forest;
    static JLabel xb,xc,xt;
    static Forest.Tree tree[][];
    static int counter = 0, width = 50, height = 50, probB = 0, probC = 50, probT = 100;
    
    public static void main(String[] args) {
        
      
        forest = new Forest(width, height, probB, probC, probT);
        final JFrame frame = new JFrame("Spread Of Fire");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
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
        JButton help = new JButton("Help");
        panel2.add(help);
         
        panel2.setBackground(Color.green);
        frame.add(panel2, BorderLayout.NORTH);
        probb = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        probb.setMajorTickSpacing(20);
        probb.setMinorTickSpacing(1);
        probb.setPaintTicks(true);
        probb.setPaintLabels(true);
        
        probc = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        probc.setMajorTickSpacing(20);
        probc.setMinorTickSpacing(1);
        probc.setPaintTicks(true);
        probc.setPaintLabels(true);
        
        probt = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        probt.setMajorTickSpacing(20);
        probt.setMinorTickSpacing(1);
        probt.setPaintTicks(true);
        probt.setPaintLabels(true);
        
        JPanel sideMenu = new JPanel(new GridLayout(11, 1));
        JPanel area = new JPanel(new FlowLayout());
        JPanel area2 = new JPanel(new FlowLayout());
        JPanel area3 = new JPanel(new FlowLayout());


        xb = new JLabel("Burn Probability : 0");
        xb.setFont(new Font("Serif", Font.BOLD, 14));
        xc = new JLabel("Catch Probability : 0");
        xc.setFont(new Font("Serif", Font.BOLD, 14));
        xt = new JLabel("Tree Probability : 0");
        xt.setFont(new Font("Serif", Font.BOLD, 14));
        JButton reset = new JButton("Reset");
      // JButton start = new JButton("start");

        sideMenu.add(xb);
        sideMenu.add(probb);
        sideMenu.add(xc);
        sideMenu.add(probc);
        sideMenu.add(xt);
        sideMenu.add(probt);
        sideMenu.add(area);
       // sideMenu.add(start);
        sideMenu.add(reset);
        sideMenu.add(area2);
        sideMenu.add(area3);
    
        
        frame.add(forest, BorderLayout.CENTER);
        frame.add(sideMenu, BorderLayout.EAST);
        frame.add(panel2, BorderLayout.NORTH);   
        frame.setVisible(true);

//Auto steps burn forest  
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
  
        
//Show Messges about how to use program
          help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null,"Tutorial\n" +
"1. Reset button: Click this button to reset the simulation to initial state with full of tree in the forest\n" +
"2. Manual button: Click this button to simulate the spreading of fire step by step\n" +
"3. Autospread button: Click this button to show automatic spread by randomise.\n" +
"4. Pause button: Click this button when you use autospread button to stop that step. \n" +
"5. Set Size button: Click this button to change area of forest by width and height\n" +
"6. Slider Burn probability: The chance that trees is burning when creating the forest.(0%-100%)\n" + 
"7. Catch probability: The chance that the fire will spread to the neighbor trees.(0%-100%)\n" +
"8. Tree probability: The density of the forest in the field.(0%-100%)\n" , "Helps", JOptionPane.PLAIN_MESSAGE);
            }
        }); 


  // Reset forest 
         reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                
                forest.setProbC(probC=50);
                forest.setProbB(probB=0);
                forest.setProbT(probT=100);
                forest.reset();
                counter = 0;
                step.setText("Step: " + counter);
                frame.validate();
                frame.repaint();
            }
        }); 
  // Set size of forest
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
                while(width >500 || height >500 || width <4 || height <4){
                JOptionPane.showMessageDialog(frame,"Input Width and Height between 4-500");
                JOptionPane.showMessageDialog(null, inputs, "Size Setting", JOptionPane.PLAIN_MESSAGE);
                width = Integer.parseInt(widthTF.getText());
                height = Integer.parseInt(heightTF.getText());            
                }               
                forest.setSize(width, height);
            }
        }); 
       
 //Scrollbar set value of Burn Probability     
        probb.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                   xb.setText("Burn Probability : "+probb.getValue());
                    probB = (int) source.getValue();
                }
                forest.setProbB(probB);

            }

        });
        
  //Scrollbar set value of Catch Probability  
        probc.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                   xc.setText("Catch Probability : "+probc.getValue());
                    probC = (int) source.getValue();
                }
                forest.setProbC(probC);

            }

        });
        
   //Scrollbar set value of Tree Probability  
            probt.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    xt.setText("Tree Probability : "+probt.getValue());
                    probT = (int) source.getValue();
                }
                forest.setProbT(probT);

            }

        });
        
  // Pause  
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                auto.stop();
            }
        }); 
        
  //Show step forest burn      
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
