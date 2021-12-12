/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_11 {

    private static String path = "files/EnergyLevel.txt";
    private static File file = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    
    private static final int n = 10;
    private static int[][] map = new int[n][n];
    private static int[][] flashes = new int[n][n];
    
    public static Stack<String> readFile(String path){
        Stack<String> lines = new Stack<String>();
        try {
         file = new File (path);
         fr = new FileReader (file);
         br = new BufferedReader(fr);

         String line;
         while((line=br.readLine())!=null){
             //System.out.println(line);
             lines.push(line);
         }
            
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
        return lines;
    }
    
    public static int contFlashes(){
        int cont = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(flashes[i][j]==1){
                    cont++;
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                flashes[i][j] = 0;
            }
        }
        return cont;
    }
    
    public static void octopusFlashes2(int i, int j){
        if(map[i][j] == 9){
            map[i][j] = 0;
            flashes[i][j] = 1;
            if(i -1 < 0){
                if(j - 1 < 0){  //Top-Left
                    octopusFlashes2(i+1,j);
                    octopusFlashes2(i,j+1);
                    octopusFlashes2(i+1,j+1);
                }else if(j + 1 == n){   // Top-Right
                    octopusFlashes2(i+1,j);
                    octopusFlashes2(i,j-1);
                    octopusFlashes2(i+1,j-1);
                }else{  //  Top-Center
                    octopusFlashes2(i+1,j);
                    octopusFlashes2(i,j-1);
                    octopusFlashes2(i,j+1);
                    octopusFlashes2(i+1,j+1);
                    octopusFlashes2(i+1,j-1);
                }
            }else if(i+1 == n){
                if(j - 1 < 0){  // Bottom-Left
                    octopusFlashes2(i-1,j);
                    octopusFlashes2(i,j+1);
                    octopusFlashes2(i-1,j+1);
                }else if(j + 1 == n){   // Bottom-Right
                    octopusFlashes2(i-1,j);
                    octopusFlashes2(i,j-1);
                    octopusFlashes2(i-1,j-1);
                }else{  // Bottom-Center
                    octopusFlashes2(i-1,j);
                    octopusFlashes2(i,j-1);
                    octopusFlashes2(i,j+1);
                    octopusFlashes2(i-1,j+1);
                    octopusFlashes2(i-1,j-1);
                }
            }else{
                if(j - 1 < 0){  // Center-Left
                    octopusFlashes2(i+1,j);
                    octopusFlashes2(i-1,j);
                    octopusFlashes2(i,j+1);
                    octopusFlashes2(i-1,j+1);
                    octopusFlashes2(i+1,j+1);
                }else if(j + 1 == n){   // Center-Right
                    octopusFlashes2(i+1,j);
                    octopusFlashes2(i-1,j);
                    octopusFlashes2(i,j-1);
                    octopusFlashes2(i-1,j-1);
                    octopusFlashes2(i+1,j-1);
                }else{  //Center-Center
                    octopusFlashes2(i+1,j);
                    octopusFlashes2(i-1,j);
                    octopusFlashes2(i,j-1);
                    octopusFlashes2(i-1,j-1);
                    octopusFlashes2(i+1,j-1);
                    octopusFlashes2(i,j+1);
                    octopusFlashes2(i-1,j+1);
                    octopusFlashes2(i+1,j+1);
                }
            }        
        }else{
            if(flashes[i][j]!=1) 
                map[i][j] +=1;
        }
    }
    
    public static void setMap(Stack<String> lines){
        int j = 0;
        char[] row;
        for(String s: lines){
            row = s.toCharArray();
            for(int i=0; i<n; i++){
                map[j][i] = Integer.parseInt(String.valueOf(row[i]));
            }
            j++;
        }
    }
    
    public static void main(String[] args) {
        Stack<String> lines = readFile(path);
        
        System.out.println("PART I");
        setMap(lines);
        int sum = 0, cont = 0;
        int num = 100;
        for(int k=0; k<num; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    octopusFlashes2(i,j);
                }
            }
            sum += contFlashes();
            /*
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            */
        }
        System.out.println("Number of flashes: " + sum);
        
        System.out.println("PART II");
        int k=0;
        while(true){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    octopusFlashes2(i,j);
                }
            }
            k++;
            if(contFlashes()==n*n){
                 for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        System.out.print(map[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            } 
            
        }
        System.out.println("All octopuses flash in step: " + k);
    }
    
}
