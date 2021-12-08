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
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_8 {

    private static String path = "files/SignalPatterns.txt";
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
    
    public static int contUniqueSegments(String sign){
        int cont = 0, n =0;
        String[] outputs = sign.split(" ");
        n = outputs.length;
        for(int i=n-4; i<n; i++){
            if(outputs[i].length()== 2 ||
                    outputs[i].length()== 3 ||
                    outputs[i].length()== 4 ||
                    outputs[i].length()== 7){
                cont++;
            }
        }
        return cont;
    }
    
    public static Stack<Character> mapping(String sign){

        Stack<Character> positions = new Stack<>();
        for(int i=0; i<7; i++){
            positions.add('*');
        }
        String[] outputs = sign.split(" ");
        
        // Analize 1
        for(int i=0; i<10; i++){
            if(outputs[i].length()==2){
                positions.set(2, outputs[i].charAt(0));
                positions.set(5, outputs[i].charAt(1));
                break;
            }
        }
        // Analize 7
        for(int i=0; i<10; i++){
            if(outputs[i].length()==3){
                for(char c: outputs[i].toCharArray()){
                    if(!positions.contains(c)){
                        if(positions.get(0)=='*'){
                            positions.set(0, c);
                            break;
                        }else if(positions.get(2)=='*'){
                            positions.set(2, c);
                            break;
                        }else if(positions.get(5)=='*'){
                            positions.set(5, c);
                            break;
                        }
                    }
                }
                break;
            }
        }
        
        // Analize 4
        Stack<Character> temp = new Stack<>();
        for(int i=0; i<10; i++){
            if(outputs[i].length()==4){
                for(char c: outputs[i].toCharArray()){
                    if(!positions.contains(c)){
                        temp.add(c);
                    }
                }
                break;
            }
        }
        
        // Analize 3
        int cont1 = 0, cont2 =0;
        for(int i=0; i<10; i++){
            if(outputs[i].length()==5){
                cont1 = 0; 
                cont2 =0;
                //Search letters
                for(char c: outputs[i].toCharArray()){
                    if(positions.contains(c)){
                        cont1++;
                    }
                    if(temp.contains(c)){
                        cont2++;
                    }
                }
                //Validations
                if(cont1==3 && cont2==1){ //3
                    for(char c: outputs[i].toCharArray()){
                        if(!positions.contains(c) && !temp.contains(c)){
                            positions.set(6, c);
                        }else if(!positions.contains(c)){
                            positions.set(3,c);
                            if(temp.get(0)!=c){
                                positions.set(1,temp.get(0));
                            }else{
                                positions.set(1,temp.get(1));
                            }
                        }
                    }
                    break;
                }
            }
        }
        
        for(int i=97; i<=103; i++){
            if(!positions.contains((char)i)){
                positions.set(4,(char)i);
                break;
            }
        }
        
        return positions;
    }
    
    public static int getOutput(String sign, Stack<Character> map){
        String output = "";
        String[] outputs = sign.split(" ");
        List<Character> temp =  new ArrayList<>();
        
        int n = outputs.length;
        for(int i=n-4; i<n; i++){
            temp.clear();
            switch (outputs[i].length()) {
                case 2:
                    output += "1";
                    break;
                case 3:
                    output += "7";
                    break;
                case 4:
                    output += "4";
                    break;
                case 7:
                    output += "8";
                    break;
                case 5: 
                    for (char ch : outputs[i].toCharArray()) {
                        temp.add(ch);
                    }
                    // 2
                    if((temp.contains(map.get(2)) || temp.contains(map.get(5))) && temp.contains(map.get(4))){
                        output += "2";
                    } 
                    //3
                    else if(temp.contains(map.get(2)) && temp.contains(map.get(5))){
                        output += "3";
                    }
                    //3
                    else if(
                            temp.contains(map.get(1)) && (temp.contains(map.get(5))) || temp.contains(map.get(2))){
                        output += "5";
                    }else{
                        output += "*";
                    }
                    break;
                case 6:
                    for (char ch : outputs[i].toCharArray()) {
                        temp.add(ch);
                    }
                    // 0
                    if(!temp.contains(map.get(3))){
                        output += "0";
                    } 
                    //6
                    else if(!temp.contains(map.get(2)) || !temp.contains(map.get(5))){
                        output += "6";
                    }
                    //9
                    else if(!temp.contains(map.get(4))){
                        output += "9";
                    }else{
                        output += "*";
                    }
                    break;
                default:
                    break;
            }
        }
        return Integer.parseInt(output);
    }
    
    public static void main(String[] args) {
        Stack<String> signs = readFile(path);
        System.out.println("PART I");
        int sum = 0;
        
        for(String s: signs){
            sum += contUniqueSegments(s);
        }
        System.out.println("Number of unique segments: " + sum);
        
        System.out.println("PART II");
        int sum1 = 0;
        for(String s: signs){
            sum1 += getOutput(s,mapping(s));
        }
        System.out.println("Sum of output values: " + sum1);
    }
    
}
