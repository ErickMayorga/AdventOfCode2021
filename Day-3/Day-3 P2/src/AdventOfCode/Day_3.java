/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_3 {

    private static String path = "files/binary.txt";
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
    
    public static char selectBit(Stack<String> numbers, int bit, String type){
        char common;
        int cont1 = 0, cont0 = 0;
        for(String s: numbers){
            if(s.charAt(bit)=='1'){
                cont1++;
            }else{
                cont0++;
            }
        }
        if(type.equals("common")){
            if(cont1 >= cont0)
                common = '1';
            else
                common = '0';
        }else{
            if(cont1 >= cont0)
                common = '0';
            else
                common = '1';
        }
        return common;
    }
    
    public static String getRating(Stack<String> numbers, String type){
        String result = "";
        char common;
        int n = numbers.size();
        for(int i=0; i<n; i++){
            
            common = selectBit(numbers, i, type);
            
            for (Iterator<String> iterator = numbers.iterator(); iterator.hasNext();) {
                String number = iterator.next();
                if(number.charAt(i) != common) {
                    iterator.remove();
                }
            }
            if(numbers.size()==1){
                result = numbers.pop();
            }
        }
        return result;
    }
    
    public static int binToDec(long bin) {
 
    int dec = 0, j =0;
    int x;
    for (long i = bin; i > 0; i /= 10) {
        x = (int) (i % 10);
        if (x != 0 && x != 1) {
            return -1;
        }
        dec += x * Math.pow(2, j);
        j++;
    }
    return dec;
 
}
    
    public static void main(String[] args) {
        // TODO code application logic here
        Stack numbers1 = readFile(path);
        String OGR = getRating((Stack<String>)numbers1.clone(), "common");
        String CSR = getRating((Stack<String>)numbers1.clone(), "no common");
        
        int OGR_dec = binToDec(Long.parseLong(OGR));
        int CSR_dec = binToDec(Long.parseLong(CSR));
        System.out.println("Oxygen Generator Rating: " + OGR_dec);
        System.out.println("C02 Scrubber Rating: " + CSR_dec);
        System.out.println("Life Support Rating: " + (OGR_dec * CSR_dec));
        
    }
}
