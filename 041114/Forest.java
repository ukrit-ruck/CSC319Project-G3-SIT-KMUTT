/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadoffire;

import java.util.Scanner;

/**
 *
 * @author japan
 */
public class Forest {
    int x,y;
    int[][] forest = new int[x][y];
    int[][] probTree = new int[x][y]; 
    int[][] probTreeBorn = new int[x][y];
    
    public Forest(int forest[][],int x,int y){
        this.x = x;
        this.y = y;
        this.forest = forest;
     
    }
  
   public int getLength(){
        return forest.length;
    }
    
   public void setPoint(int x, int y) {
        forest[x][y] = 2;
    }

   public void setForest() {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                forest[i][j] = 1;
            }
        }
    }
    
   public void eachTree(int probTree[][]) {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                int t = 1+ (int) (Math.random()*100);
                probTree[i][j] = t;
            }
        }
    }
  
   public void wall() {

        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                if (i == 0 || i == forest.length - 1) {
                    forest[i][j] = 0;
                } else if (j == 0 || j == forest.length - 1) {
                    forest[i][j] = 0;
                }
            }
        }
    }

   public void print() {
        /*
        x=0 -> x=4 && y=0  up
        x=4 && y=0 -> y=4  right
        x=0 && y=0 -> y=4  left
        x=0 -> x=4 && y=4  down
         int[0][0] int[1][0] int[2][0] int[3][0] int[4][0]
         int[0][1] int[0][...]                   int[4][1]
         int[0][2]                               int[4][2]
         int[0][3]                               int[4][3]       
         int[0][4] int[1][4] int[2][4] int[3][4] int[4][4]
         */
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
            
        }
        System.out.println("++++++++++++++++++++++++++++++++");
    }

   public void probTreeBorn(int probTreeBorn[][]){
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                int k = 1+ (int) (Math.random()*100);
                probTreeBorn[i][j] = k;
            }
        }
    }

   
  
    
}
