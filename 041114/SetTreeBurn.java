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
public class SetTreeBurn extends Forest  {
   
   
    public SetTreeBurn(int[][] forest, int x, int y) {
        super(forest, x, y);
        this.forest = forest;
        this.probTree = forest;
    }
 
  
  public void printprobTree() {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                System.out.print(probTree[i][j] + " ");
            }
            System.out.println();
        }
    }

   

   
}
