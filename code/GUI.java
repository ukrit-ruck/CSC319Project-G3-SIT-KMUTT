/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Student Lab
 */
public class GUI extends JPanel {
    private int j=5;
    private int i=5;
    private int width=5;
    private int screen=500;
    private int forest[][];
    public GUI(int[][] f){
        forest=f;
        screen=f.length*width;
    }
    
    public void paintComponent(Graphics g){
        int vali=0;
        int valj=0;
        for(int k=0;k<screen;k+=width){
            valj=0;
            for(int l=0;l<screen;l+=width){
                if(forest[valj][vali]==1){
                     g.setColor(Color.green);
                }else if(forest[valj][vali]==2){
                     g.setColor(Color.red);
                }else if(forest[valj][vali]==5){
                    g.setColor(Color.darkGray);
                }else  if(forest[valj][vali]==0){
                    g.setColor(Color.yellow);
                }
                g.fillRect(k,l,width,width);
              valj++;
            }
            
           vali++;
        }
    }
}
