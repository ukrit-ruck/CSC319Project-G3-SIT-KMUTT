/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadoffire;


import java.util.Scanner;

/**
 *
 * @author Japan
 */
public class SpreadOfFire {

    /**
     * @param args the command line arguments
     */
   

    public static void main(String[] args) {
       int randX1,randY1,s;
       
       Scanner sc = new Scanner(System.in);
       
       int width = sc.nextInt(); //width of forest
       int height = sc.nextInt(); // height of forest
       
       int pforestburn = sc.nextInt(); //probability of forest that forest will be burn
       int pforesttree = sc.nextInt();  //probability of forest that tree will be born
       int[][] forest = new int[width][height];
       int[][] probTree = new int[width][height];
       int[][] probTreeBorn = new int[width][height];
       /* Scanner sc1 = new Scanner(System.in);
        int prob = sc1.nextInt();*/
      
       // print.setForest();
        Forest forest1 = new Forest(forest,width,height);
        AllRandom randX = new AllRandom(forest1.getLength());
        AllRandom randY = new AllRandom(forest1.getLength());
        randX1=randX.randomX(); // random of point that burn(X)
        randY1=randY.randomY(); // random of point that burn(Y)
      
        System.out.println("x ="+randX1+ "\ny ="+randY1);
        System.out.println();
        forest1.setForest();
        forest1.wall();
        forest1.setPoint(randX1, randY1);
        forest1.print();
        SetTreeBurn Tree = new SetTreeBurn(probTree,width,height);
        forest1.eachTree(probTree);
        forest1.probTreeBorn(probTreeBorn);
        Tree.printprobTree();
       
        
       // s = k.probForest();
        //System.out.println("Prob="+s);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        Burn burn = new Burn(forest,probTree,probTreeBorn,pforestburn);
        burn.compareProb(randX1, randY1);
        forest1.wall();
        forest1.print();
       
        burn.probForest(pforesttree);
        forest1.print();
        /*
        while(!burn.finish()){
         burn.search();
         burn.reset();
         forest1.print();
        }
  */
        
      /*  
        // print.burn(j, k);
        //r = print.probForest();
        
        print.print();
        System.out.println(prob);
        print.printprobTree();
        print.wall();
        print.print();*/
    }
}


