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
public class Day_13 {

    private static String path = "files/Instructions.txt";
    private static File file = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;

    private static String[][] sheet;
    
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
    
    public static int[] maxXY(Stack<int[]> points){
        int max[] = new int[2];
        for(int[] P: points){
            if(P[0]>max[0]){
                max[0] = P[0];
            }
            if(P[1]>max[1]){
                max[1] = P[1];
            }
        }
        return max;
    }
    
    public static void setPoints(Stack<int[]> points){
        for(int[] P: points){
            sheet[P[1]][P[0]] = "#";
        }
    }
    
    public static String[][] fold(String eje, int value){
        String[][] newSheet;
        int rows = sheet.length;
        int columns = sheet[0].length;
        
        if(eje.equals("x")){
            newSheet = new String[rows][value];
        }else{
            newSheet = new String[value][columns];
        }
        emptySheet(newSheet);
        int newRows = newSheet.length;
        int newColumns = newSheet[0].length;
        /*
        System.out.println("Y: " + newRows);
        System.out.println("X: " + newColumns);
        */

        
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                if(y<newRows && x<newColumns){
                    if(sheet[y][x].equals("#")) 
                        newSheet[y][x] = sheet[y][x];
                }else if(y>newRows){
                    if(sheet[y][x].equals("#")) 
                        newSheet[2*newRows-y][x]= sheet[y][x];
                }else if(x>newColumns){
                    if(sheet[y][x].equals("#")) 
                        newSheet[y][2*newColumns-x]= sheet[y][x];
                }
            }
        }
        return newSheet;
    }
    
    public static void printSheet(){
        int rows = sheet.length;
        int columns = sheet[0].length;
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                System.out.print(sheet[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void emptySheet(String[][] sheet){
        int rows = sheet.length;
        int columns = sheet[0].length;
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                sheet[y][x] = ".";
            }
        }
    }
    
    public static int contPoints(){
        int rows = sheet.length;
        int columns = sheet[0].length;
        int cont = 0;
        for(int y=0; y<rows; y++){
            for(int x=0; x<columns; x++){
                if(sheet[y][x] == "#")
                    cont++;
            }
        }
        return cont;
    }
    
    public static void main(String[] args) {
        Stack<String> lines = readFile(path);
        String[] parts;
        int[] point = new int[2];
        boolean isFold = false;
        Stack<String[]> folds = new Stack<>();
        Stack<int[]> points = new Stack<>();
        
        System.out.println("PART I");
        for(String line: lines){
            if(isFold){
                parts = line.split(" ");
                parts = parts[2].split("=");
                folds.add(new String[] {parts[0], parts[1]});
                //System.out.println(parts[0] + "=" + parts[1]);
            }
            if(!line.isBlank() && !isFold){
                parts = line.split(",");
                point[0] = Integer.parseInt(parts[0]);
                point[1] = Integer.parseInt(parts[1]);
                points.add(new int[] {point[0], point[1]});
                //System.out.println(point[0] + "," + point[1]);
            }else{
                isFold = true;
            } 
        }
        
        int[] max = maxXY(points);
        sheet = new String[max[1]+1][max[0]+1];
        emptySheet(sheet);
        setPoints(points);
        String[][] currentSheet = fold(folds.get(0)[0],Integer.parseInt(folds.get(0)[1]));
        sheet = currentSheet;
        //printSheet();
        System.out.println("Visible Points at First Fold: " + contPoints());
        
        System.out.println("PART II");
        max = maxXY(points);
        sheet = new String[max[1]+1][max[0]+1];
        
        emptySheet(sheet);
        setPoints(points);
        
        
        for(String[] f: folds){
            currentSheet = fold(f[0],Integer.parseInt(f[1]));
            sheet = currentSheet;
        }
        printSheet();
        
    }
    
}
