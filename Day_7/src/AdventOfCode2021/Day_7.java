/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_7 {

    private static String path = "files/CrabPositions.txt";
    private static File file = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    
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
    
    public static int maxPosition(Stack<Integer> crabs){
        int max = crabs.get(0);
       
        for (Integer crab: crabs) {
            if(crab > max) {
                max = crab;
            }
        }
        return max;
    }
    
    public static int fuelCost(Stack<Integer> crabs, int position){
        int sum = 0, steps = 0;
        for(Integer crab: crabs){
            steps = Math.abs(crab - position);
            for(int i=1; i<=steps; i++){
                sum += i;
            }
        }
        return sum;
    }
    
    public static int minFuel(Stack<Integer> fuels){
        int min = fuels.get(0);
       
        for (Integer fuel: fuels) {
            if(fuel < min) {
                min = fuel;
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        String[] numbers = readFile(path).get(0).split(",");
        Stack<Integer> crabs = new Stack<Integer>();
        for(String s: numbers){
            crabs.push(Integer.parseInt(s));
        }
        int n = maxPosition(crabs);
        Stack<Integer> fuels = new Stack<Integer>();
        for(int i=1; i<n; i++){
            fuels.add(fuelCost(crabs, i));
        }
        System.out.println("Min Fuel: " + minFuel(fuels));
        
    }
    
}
