/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_6 {

    private static String path = "files/lanternfish.txt";
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
    
    public static void simulateGlowing(Stack<Integer> fishes){
        int value = 0;
        int n = fishes.size();
        for(int i=0; i<n; i++){
            value = fishes.get(i);
            if(fishes.get(i) != 0){
                fishes.set(i, value-1);
            }else{
                fishes.set(i, 6);
                fishes.add(8);
            }
                
        }
        //System.out.println(fishes);
    }
    
    public static void main(String[] args) {
        String[] numbers = readFile(path).get(0).split(",");
        Stack<Integer> fishes = new Stack<Integer>();
        for(String s: numbers){
            fishes.push(Integer.parseInt(s));
        }
        int n = 80;
        for(int i=0; i<n; i++){
            simulateGlowing(fishes);
        }
        System.out.println("Lantern Fish Population: " + fishes.size());
    }
    
}
