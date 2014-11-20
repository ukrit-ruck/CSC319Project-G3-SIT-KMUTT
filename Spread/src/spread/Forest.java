/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spread;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tAnniiz-PC
 */
public class Forest extends JPanel {
    private int width, height, probC, probT, probB;
    private Tree[][] tree;
    
    public Forest(){
        this.width = 50;
        this.height = 50;
        this.probC = 0;
        this.probT = 100;
        this.probB = 0;
        this.tree = new Tree[this.width][this.width];

        this.setLayout(new GridLayout(this.width, this.height));
        
        for(int i = 0 ; i < this.height ; i++){
            for(int j = 0 ; j < this.width ; j++){
                if(i == 0 || i == this.width - 1 || j == 0 || j == this.height - 1){
                    tree[i][j] = new Tree(0);
                    this.add(tree[i][j]);
                } else {
                    if(this.random(this.probT)){
                        if(this.random(this.probB)) {
                            tree[i][j] = new Tree(2);
                            this.add(tree[i][j]);
                        } else {
                            tree[i][j] = new Tree(1);
                            this.add(tree[i][j]);
                        }
                    } else {
                        tree[i][j] = new Tree(0);
                        this.add(tree[i][j]);
                    }
                }
            }
        }
        
        tree[this.width / 2][this.height / 2].setState(2);
        update();
    }
    
    public Forest(int width, int height, int probB, int probC, int probT){
        this.width = width;
        this.height = height;
        this.probC = probC;
        this.probT = probT;
        this.probB = probB;
        this.setLayout(new GridLayout(this.width, this.height));
        
        this.tree = new Tree[this.width][this.height];
        
        for(int i = 0 ; i < this.height ; i++){
            for(int j = 0 ; j < this.width ; j++){
                if(i == 0 || i == this.width - 1 || j == 0 || j == this.height - 1){
                    tree[i][j] = new Tree(0);
                    this.add(tree[i][j]);
                } else {
                    tree[i][j] = new Tree(1);
                    this.add(tree[i][j]);
                }
            }
        }
        
        tree[this.width / 2][this.height / 2].setState(2);
        update();
    }
    
    public boolean random(int prob){
        return (int)(Math.random()*100) < prob;
    }
    
    public void spread(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        for (int i = 0; i < this.height - 1; i++) {
            for (int j = 0; j < this.width - 1; j++) {
                if (tree[i][j].getState() == Tree.RED && tree[i][j].isFire()) {
                    tree[i][j].setState(Tree.YELLOW);
                    burn('N', i, j);
                    burn('S', i, j);
                    burn('W', i, j);
                    burn('E', i, j);
                    this.update(); 
                }
            }
        }
        
        for (int i = 0; i < this.height - 1; i++) {
            for (int j = 0; j < this.width - 1; j++) {
                if (tree[i][j].getState() == Tree.RED) {
                    tree[i][j].setFire(true);
                }
            }
        }
    }
    
    public void burn(char dir, int posX, int posY) {
        switch(dir){
            case 'N': if(tree[posX][posY - 1].getState() == 1) tree[posX][posY - 1].setState(2); tree[posX][posY - 1].setFire(false); break;
            case 'E': if(tree[posX + 1][posY].getState() == 1) tree[posX + 1][posY].setState(2); tree[posX + 1][posY].setFire(false); break;
            case 'W': if(tree[posX - 1][posY].getState() == 1) tree[posX - 1][posY].setState(2); tree[posX - 1][posY].setFire(false); break;
            case 'S': if(tree[posX][posY + 1].getState() == 1) tree[posX][posY + 1].setState(2); tree[posX][posY + 1].setFire(false); break;
        }
    }
    
    public boolean isEnd(){
        for (int i = 0; i < this.height - 1; i++) {
            for (int j = 0; j < this.width - 1; j++) {
                if (tree[i][j].getState() == Tree.RED) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        this.setLayout(new GridLayout(this.width, this.height));
        this.tree = new Tree[this.width][this.height];
        this.reset();
    }
    
    public void reset(){
        this.removeAll();
        for(int i = 0 ; i < this.height ; i++){
            for(int j = 0 ; j < this.width ; j++){
                if(i == 0 || i == this.width - 1 || j == 0 || j == this.height - 1){
                    tree[i][j] = new Tree(0);
                    this.add(tree[i][j]);
                } else {
                    if(this.random(this.probT)){
                        if(this.random(this.probB)) {
                            tree[i][j] = new Tree(2);
                            this.add(tree[i][j]);
                        } else {
                            tree[i][j] = new Tree(1);
                            this.add(tree[i][j]);
                        }
                    } else {
                        tree[i][j] = new Tree(0);
                        this.add(tree[i][j]);
                    }
                }
            }
        }
        
        tree[this.width / 2][this.height / 2].setState(2);
        update();
    }

    public void setProbC(int probC) {
        this.probC = probC;
    }

    public void setProbT(int probT) {
        this.probT = probT;
    }

    public void setProbB(int probB) {
        this.probB = probB;
    }

    public void update() {
        this.validate();
        this.repaint();
    }


    private class Tree extends JLabel {
        private final static int YELLOW = 0, GREED = 1, RED = 2;
        private int state;
        private boolean fire;
        
        public Tree(int state){
            this.state = state;
            this.fire = true;
            this.setState(this.state);
            this.setOpaque(true);
        }

        public boolean isFire() {
            return this.fire;
        }

        public void setFire(boolean fire) {
            this.fire = fire;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            switch(this.state){
                case 0:
                    this.setBackground(Color.YELLOW); this.setText(""  ); break;
                case 1:
                    this.setBackground(Color.GREEN); this.setText("" ); break;
                case 2:
                    this.setBackground(Color.RED); this.setText(""); break;
            }
            Forest.this.update();
        }
        
        
    }
}

    

