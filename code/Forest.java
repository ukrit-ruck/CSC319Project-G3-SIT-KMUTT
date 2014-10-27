/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Student Lab 
 */
public class Forest {
    

    int[][] f=new int[5][2];
    int[][] land;
    public Forest(int size){
        land=new int[size][size];
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[i].length;j++){
                if(j==0 || i==0 || j==land.length-1 ||  i==land.length-1){
                    land[i][j]=0;
                }else{
                    land[i][j]=1;
                }
            }
        }
    }
    
    public int[][] getLand(){
        return land;
    
    }
    
    public void burn(){
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land.length;j++){
                
            }
        }
    }
}
