/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadoffire;

/**
 *
 * @author japan
 */
public class Burn {
    int x,y,x1,y1,ff;
    int[][] forest = new int[x][y];
    int[][] probTree = new int[x][y]; 
    boolean[][] check = new boolean[x][y];
    int[][] treeBorn = new int[x][y];

    
    
    public Burn(int forest[][],int probTree[][],int treeBorn[][],int ff){
    this.forest = forest;
    this.probTree = probTree;
    this.treeBorn = treeBorn;
    this.ff = ff;
   
    check = new boolean[forest.length][forest.length];
    }
    
   
    
     public void compareProb(int j, int k) {
        // probTree[j][k] {//first point
        if (forest[j][k-1]==1 &&probTree[j][k - 1] <= ff && check[j][k-1]==false) {
            forest[j][k - 1] = 2;check[j][k-1]=true;
            
        }if (forest[j - 1][k]==1 && probTree[j - 1][k] <= ff && check[j-1][k]==false) {
            forest[j - 1][k] = 2;check[j-1][k]=true;
        }
        if (forest[j + 1][k]==1 && probTree[j + 1][k] <= ff && check[j+1][k]==false) {
            forest[j + 1][k] = 2;check[j+1][k]=true;
        }
      
        if (forest[j][k+1]==1 &&probTree[j][k + 1] <= ff && check[j][k+1]==false) {
            forest[j][k + 1] = 2;check[j][k+1]=true;
        }
    }
     
    
     
     public void search(){
        for(int x=0;x<forest.length;x++){
            for(int y=0; y<forest.length;y++){
                if(forest[x][y]==2 && check[x][y]==false){                    
                    forest[x][y] = 0;
                    compareProb(x,y); 
                    break;
                }
            }
        }
     
     }
     
     public void reset(){
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {                  
             check[i][j] = false;
            }
        }
    }
     
     public boolean finish(){
       for(int x=0;x<forest.length;x++){
            for(int y=0; y<forest.length;y++){
                if(forest[x][y]==2){
                   return false; 
                }
            }
        }return true;
     
     }
    
    
    public void print(){
    for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void probTreeBorn(){
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                int k = 1+ (int) (Math.random()*100);
                treeBorn[i][j] = k;
            }
        }
    }
    
     public void probForest(int pBorn){
         for(int i = 0; i< forest.length; i++){
             for(int j = 0; j< forest.length; j++){
                 if(treeBorn[i][j]<pBorn){
                     forest[i][j] = 2;
                 }else{
                     forest[i][j] = 0;
                 }
             }
         }
      /* if a random number is less than probTree // tree at site
if another random number is less than probBuring // tree is burning
assign BURNING to the cell
else // tree is not burning
assign TREE to the cell
else // no tree at site
assign EMPTY to the cell*/
       
   }
    
    
}
