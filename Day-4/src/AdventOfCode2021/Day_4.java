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
public class Day_4 {

    private static String path = "files/boards.txt";
    private static File file = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static final int n = 5;
    
    private static Stack<int[][]> boards = new Stack<int[][]>();
    private static Stack<int[][]> marks = new Stack<int[][]>();
    private static int winnerValue = -1;
    private static int columnWinner = -1;
    private static int rowWinner = -1;

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
    
    public static void printMatrix(int A[][]){
        for(int i=0; i<n; i++ ){
            for(int j=0; j<n; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void setBoards(Stack<String> lines){
        List<String> numbersAux;
        int[][] matrix = new int[n][n];
        int i = 0, j = 0;
        
        for (Iterator<String> iterator = lines.iterator(); iterator.hasNext();) {
            String line = iterator.next();
            if(line.isBlank()) {
                iterator.remove();
            }
        }
        
        for(String s: lines){
            numbersAux = new ArrayList<>(Arrays.asList(s.split(" ")));
            for (Iterator<String> iterator = numbersAux.iterator(); iterator.hasNext();) {
                String number = iterator.next();
                if(number.isBlank()) {
                    iterator.remove();
                }
            }
            
            j = 0;
            for(String m: numbersAux){
                matrix[i][j] = Integer.parseInt(m);
                j++;
            }

            i++;
            if(i==n){
                boards.add(matrix);
                matrix = new int[n][n];
                i=0;
            }
        }
    }
    
    public static boolean hasLineWinner(int[][] A){
        boolean result = false;
        int contRow[] =  new int[n];
        int contColumn[] = new int[n];
  
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(A[i][j]==1){
                    contRow[i]++;
                    contColumn[j]++;
                    for(int k=0; k<n; k++){
                        if(contRow[k]==5 ){
                            rowWinner = k;
                            return true;
                        }
                        if(contColumn[k]==5){
                            columnWinner = k;
                            return true;
                        }
                        rowWinner = -1;
                        columnWinner = -1;
                    }
                }
            }
        }
        return result;
    }
    
    public static int findWinnerBoard(Stack<Integer> numbers){
        for(Integer k: numbers){
            for(int m=0; m<boards.size(); m++){
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        if(boards.get(m)[i][j] == k){
                            marks.get(m)[i][j] = 1;
                            if(hasLineWinner(marks.get(m))){
                                winnerValue = k;
                                return m;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public static int findLoserBoard(Stack<Integer> numbers){
        int winners = 0;
        Stack<Integer> exceptions = new Stack<Integer>();
        
        for(Integer k: numbers){
            for(int m=0; m<boards.size(); m++){
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        if(boards.get(m)[i][j] == k){
                            marks.get(m)[i][j] = 1;
                            if(!exceptions.contains(m) && hasLineWinner(marks.get(m))){
                                exceptions.add(m);
                                winners++;
                                winnerValue = k;
                                if(winners==boards.size()){
                                    return m;
                                }
                            }
                        }
                    }
                }
            }
        }     
        

        return -1;
    }
    
    public static int getFinalScore(int board){
        int sum = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(marks.get(board)[i][j] != 1){
                    sum += boards.get(board)[i][j];
                }
            }
        }
        return sum*winnerValue;
    }
    
    public static void main(String[] args) {
        Stack<String> lines = readFile(path);
        List<String> numbers = new ArrayList<>(Arrays.asList(lines.get(0).split(",")));
        Stack<Integer> numbersBingo = new Stack<Integer>();
        for(String s: numbers){
            numbersBingo.push(Integer.parseInt(s));
        }
        
        lines.remove(0);
        setBoards(lines);
        
        for(int[][] A: boards){
            marks.add(new int[5][5]);
        }
        
        
        System.out.println("PART I");
        
        int winner = findWinnerBoard(numbersBingo);
        if(winner != -1){
            System.out.println("Winner Value: " + winnerValue);
            if(rowWinner != -1){
                System.out.println("Row Winner: " + rowWinner);
            }else{
                System.out.println("Column Winner: " + columnWinner);
            }
            printMatrix(boards.get(winner));
        }else{
            System.out.println("Draw Winner!!");
        }
        
        System.out.println("Final Score: " + getFinalScore(winner));
        
        
        System.out.println("\nPART II");
        
        int loser = findLoserBoard(numbersBingo);
        if(loser != -1){
            System.out.println("Loser Value: " + winnerValue);
            if(rowWinner != -1){
                System.out.println("Row Loser: " + rowWinner);
            }else{
                System.out.println("Column Loser: " + columnWinner);
            }
            printMatrix(boards.get(loser));
        }else{
            System.out.println("Draw Loser!!");
        }
        
        //printMatrix(marks.get(loser));
        
        System.out.println("Final Score: " + getFinalScore(loser));
    }
    
}

