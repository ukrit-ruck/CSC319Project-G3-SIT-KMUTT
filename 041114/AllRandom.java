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
public class AllRandom {
    int forest;
      public AllRandom(int forest){
       this.forest = forest;
      }
    
   /* 
    }*/
    public int randomX() {
       
        int n =  (int) (Math.random() * forest - 1);
        if(n==0){n=n+1;}
        return n;
    }

    public int randomY() {
        
        int m = (int) (Math.random() * forest - 1);
        if(m==0){m=m+1;}
        return m;
    }
    
    public int probForest() {
        int f =  1+ (int) (Math.random()*100);
        return f;
    }
    
    
}
