/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spreadoffire;

import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Japan
 */
public class SpreadOfFire {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);
    static int x;
    static int y;
    static int[][] forest;
    int[][] probTree = new int[x][y];

    public static void main(String[] args) {
        x = sc.nextInt();
        y = sc.nextInt();
        forest = new int[x][y];
        JFrame frame = new JFrame("Spreading of fire");
        Pane pane = new Pane(x, y, forest);
        frame.add(pane);
        int width = frame.getWidth();
        int height = frame.getHeight();
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int j, k;
        int r;
        SpreadOfFire print = new SpreadOfFire();
        print.setForest();

        j = print.randomX();
        k = print.randomY();
        print.SetPoint(j, k);
        print.print();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        print.eachTree();
        // print.burn(j, k);
        r = print.probForest();
        print.compareProb(r, j, k);
        print.print();
        System.out.println(r);
        print.printprobTree();
        print.wall();
        print.print();
    }

    public void SetPoint(int x, int y) {
        forest[x][y] = 2;
    }

    public int randomX() {

        Random rand = new Random();
        int n = rand.nextInt(forest.length - 1) + 1;
        if (n == 0) {
            while (n != 0) {
                n = rand.nextInt(forest.length - 1) + 1;
            }
        }
        return n;
    }

    public int randomY() {
        Random rand = new Random();
        int m = rand.nextInt(forest.length - 1) + 1;
        if (m == 0 || m == 5) {
            while (m != 0 || m != 5) {
                m = rand.nextInt(forest.length - 1) + 1;
            }
        }
        return m;

    }

    public void print() {
        /*
         int[0][0] int[1][0] int[...][0]
         int[0][1] int[0][...] int[5][0]
         int[0][2]
         int[0][3]
         int[0][4]
         */
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                System.out.print(forest[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printprobTree() {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                System.out.print(probTree[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setForest() {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                forest[i][j] = 1;
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

    public void burn(int j, int k) {

        if (forest[j - 1][k] == 0) {
            forest[j - 1][k] = 0;
        } else {
            forest[j - 1][k] = 2;
        }

        if (forest[j + 1][k] == 0) {
            forest[j + 1][k] = 0;
        } else {
            forest[j + 1][k] = 2;
        }

        if (forest[j][k - 1] == 0) {
            forest[j][k - 1] = 0;
        } else {
            forest[j][k - 1] = 2;
        }

        if (forest[j][k + 1] == 0) {
            forest[j][k + 1] = 0;
        } else {
            forest[j][k + 1] = 2;
        }

        forest[j][k] = 0;
        /*
         forest[j+1][k] =2;
         forest[j][k-1]=2;
         forest[j][k+1]=2;
         forest[j][k]=0;
         */
    }

    public int probForest() {
        Random randF = new Random();
        int f = randF.nextInt(100) + 1;
        return f;
    }

    public void eachTree() {
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest.length; j++) {
                Random randT = new Random();
                int t = randT.nextInt(100) + 1;
                probTree[i][j] = t;
            }
        }
    }

    public void compareProb(int f, int j, int k) {
        // probTree[j][k] {//first point
        if (probTree[j - 1][k] <= f) {
            forest[j - 1][k] = 2;
        }
        if (probTree[j + 1][k] <= f) {
            forest[j + 1][k] = 2;
        }
        if (probTree[j][k - 1] <= f) {
            forest[j][k - 1] = 2;
        }
        if (probTree[j][k + 1] <= f) {
            forest[j][k + 1] = 2;
        }

    }

    public void colorForest() {
     //   for (int i = 0; i < forest.length; i++) {
        //     for (int y = 0; y < forest[0].length; y++) {

        //สร้างเฟรม   http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
        //หาตำแหน่ง จาก frame.หากว้าง frame. หายาว   หารด้วย  i ,y   Grid.java
        //g.fillrect ตามตำแหน่ง g.setColor http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsfillRectintxintyintwidthintheight.htm
        //paint component
        // }
        // }
    }

    static class Pane extends JPanel {

        int row;
        int column;
        int forest[][];
        int cellHt;
        int cellWid;

        //int row,int column, int forest[][]
        Pane(int row, int column, int forest[][]) {
            this.row = row;
            this.column = column;
            this.forest = forest;
            // set a preferred size for the custom panel.

        }

        void update(int forest[][]) {
            this.forest = forest;
            paintComponent(this.getGraphics());
        }

        public void paintComponent(Graphics g) {
            this.cellHt = this.getHeight() / row;
            this.cellWid = this.getWidth() / column;
            super.paintComponent(g);

            for (int i = 0; i < row; i++) {
                for (int y = 0; y < column; y++) {
                    if (forest[i][y] == 0) {
                        g.setColor(Color.yellow);
                        g.fillRect(i * cellWid, y * cellHt, cellWid, cellHt);
                    }
                    if (forest[i][y] == 1) {
                        g.setColor(Color.green);
                        g.fillRect(i * cellWid, y * cellHt, cellWid, cellHt);
                    }
                    if (forest[i][y] == 2) {
                        g.setColor(Color.red);
                        g.fillRect(i * cellWid, y * cellHt, cellWid, cellHt);
                    }
                }
            }

        }
    }
}
