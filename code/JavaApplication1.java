/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Random;
/**
 *
 * @author Student Lab
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rnd=new Random();
        JFrame sc = new JFrame("Fire");
        Forest f=new Forest(90);
        int[][] forest=f.getLand();
        forest[1+rnd.nextInt(forest.length-3)][1+rnd.nextInt(forest.length-3)]=2;
        GUI g=new GUI(forest);
        sc.add(g);
        sc.setSize(200, 200);
        sc.setVisible(true);
    }
    
}
