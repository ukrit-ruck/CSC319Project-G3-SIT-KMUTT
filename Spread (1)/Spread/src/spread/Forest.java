/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spread;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author tAnniiz-PC
 */
public class Forest extends JPanel {
    int width, height, probC, probT, probB;
    Tree[][] tree;
    GridLayout gl;
    
    public Forest(){
        this.width = 50;
        this.height = 50;
        this.probC = 100;
        this.probT = 100;
        this.probB = 0;
        this.tree = new Tree[this.height][this.width];
        this.gl = new GridLayout(this.height, this.width);
        this.gl.setVgap(5);
        this.gl.setHgap(5);
      
// Random Probability of Tree and Probablilty of Burn forest.  
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
        
        tree[this.height / 2][this.width / 2].setState(2);
        this.update();
    }
    
    public Forest(int width, int height, int probB, int probC, int probT){
        this.width = width;
        this.height = height;
        this.probC = probC;
        this.probT = probT;
        this.probB = probB;
        this.gl = new GridLayout(this.height, this.width);
        this.gl.setVgap(5);
        this.gl.setHgap(5);
        
        this.tree = new Tree[this.height][this.width];
        this.reset();
        
        /*
        for(int i = 0 ; i < this.height ; i++){
            for(int j = 0 ; j < this.width ; j++){
                if(i == 0 || i == this.height - 1 || j == 0 || j == this.weight - 1){
                    tree[i][j] = new Tree(0);
                    this.add(tree[i][j]);
                } else {
                    tree[i][j] = new Tree(1);
                    this.add(tree[i][j]);
                }
            }
        }
        
        tree[this.height / 2][this.width / 2].setState(2);
        this.update();*/
    }
    
    public boolean random(int prob){
        return (int)(Math.random()*100) < prob;
    }
    
 //Set times to burn forest
    public void spread(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
  //Burn forest
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
    //Check point burn forest
    public void burn(char dir, int posX, int posY) {
        if(this.random(probC)){
            switch(dir){
            case 'N': if(tree[posX][posY - 1].getState() == 1) tree[posX][posY - 1].setState(2); tree[posX][posY - 1].setFire(false); break;
            case 'E': if(tree[posX + 1][posY].getState() == 1) tree[posX + 1][posY].setState(2); tree[posX + 1][posY].setFire(false); break;
            case 'W': if(tree[posX - 1][posY].getState() == 1) tree[posX - 1][posY].setState(2); tree[posX - 1][posY].setFire(false); break;
            case 'S': if(tree[posX][posY + 1].getState() == 1) tree[posX][posY + 1].setState(2); tree[posX][posY + 1].setFire(false); break;
            }
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
        this.tree = new Tree[this.height][this.width];
        this.reset();
    }
    //Reset forest and create tree
    public void reset(){
        this.gl = new GridLayout(this.height, this.width);
        this.gl.setVgap(1);
        this.gl.setHgap(1);
        this.setLayout(this.gl);
        this.removeAll();
        for(int i = 0 ; i < this.height ; i++){
            for(int j = 0 ; j < this.width ; j++){
                if(i == 0 || i == this.height - 1 || j == 0 || j == this.width - 1){
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
        
        tree[this.height / 2][this.width / 2].setState(2);
        this.update();
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

    public Tree[][] getTree() {
        return tree;
    }


    class Tree extends JLabel {
        private final static int YELLOW = 0, GREED = 1, RED = 2;
        private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        private int state;
        private boolean fire;
        private boolean isShow;
        
        public Tree(int state){
            this.state = state;
            this.fire = true;
            this.isShow = false;
            
            //this.setBorder(this.border);
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
      /*  
        public void print(){
             for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < tree.length; j++) {
                System.out.print(tree[i][j] + " ");
            }
            System.out.println();
            
        }
        System.out.println("++++++++++++++++++++++++++++++++");
    
        }*/

        public void setState(int state) {
            this.state = state;
            switch(this.state){
                case 0:
                    this.setBackground(Color.decode("#FFCC00")); break;
                case 1:
                    this.setBackground(Color.decode("#66CC00")); break;
                case 2:
                    this.setBackground(Color.decode("#FF3300")); break;
            }
            
            if(this.isShow){
                this.showNum();
            }
            
            Forest.this.update();
        }
        
        public void hideNum(){
            this.isShow = false;
            this.setText("" + this.state);
            Forest.this.update();
        }
        
        public void showNum(){
            this.isShow = true;
            this.setText("" + this.state);
            Forest.this.update();
        }
        
    }
}

    

