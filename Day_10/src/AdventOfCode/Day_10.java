/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_10 {

    private static String path = "files/NavigationSubsystem.txt";
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
    
    public static int firstIllegal(String line){
        Stack<Character> chars = new Stack<>();
        Character last;
        for(char c: line.toCharArray()){
            //System.out.println(c);
            if(c=='(' || c == '[' || c == '{' || c == '<'){
                chars.push(c);
            }else{
                last = chars.lastElement();
                if(c == ')' && last != '('){
                  return 3;  
                }else if(c == ']' && last != '['){
                    return 57;
                }else if(c == '}' && last != '{'){
                    return 1197;
                }else if(c == '>' && last != '<'){
                    return 25137;
                }else {
                    chars.pop();
                }
            }
                
        }
        return 0;
    }
    
    public static Stack<Character> closingCharacters(String line){
        Stack<Character> chars = new Stack<>();
        Character last;
        for(char c: line.toCharArray()){
            //System.out.println(c);
            if(c=='(' || c == '[' || c == '{' || c == '<'){
                chars.push(c);
            }else{
                last = chars.lastElement();
                if(c == ')' && last != '('){
                  return null;  
                }else if(c == ']' && last != '['){
                    return null;
                }else if(c == '}' && last != '{'){
                    return null;
                }else if(c == '>' && last != '<'){
                    return null;
                }else {
                    chars.pop();
                }
            }
                
        }
        return chars;
    }
    
    public static long scoreClosing(Stack<Character> chars){
        long score = 0;
        Character last;
        int n = chars.size();
        for(int i=0; i<n; i++){
            last = chars.pop();
            if(last == '('){
                score *= 5;
                score += 1;
            }else if(last == '['){
                score *= 5;
                score += 2;
            }else if(last == '{'){
                score *= 5;
                score += 3;
            }else{
                score *= 5;
                score += 4;
            }
            //System.out.print(score + " ");
        }
        //System.out.println();
        return score;
    }
    
    public static long middleScore(Stack<Long> scores){
        long middle = 0;
        Collections.sort(scores);
        //System.out.println(scores.toString());
        int n = scores.size();
        if(n%2!=0){
            middle = scores.get(n/2);
        }else{
            System.out.println("Stack is even");
        }
        return middle;
    }
    
    public static void main(String[] args) {
        Stack<String> lines = readFile(path);
        int sum = 0;
        System.out.println("PART I");
        for(String line: lines){
            sum += firstIllegal(line);
        }
        System.out.println("total syntax error score: " + sum);
        System.out.println("PART II");
        Stack<Character> closing;
        Stack<Long> scores = new Stack<>();
        for(String line: lines){
            closing = closingCharacters(line);
            if(closing!=null){
                //System.out.println(closing.toString());
                scores.add(scoreClosing(closing));
            }
        }
        System.out.println("Middle Score: " + middleScore(scores));
    }
    
}
