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
public class Day_5 {

    private static String path = "files/lines.txt";
    private static File file = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    private static final int n = 1000;
    private static String[][] diagram = new String[n][n]; 
    private static final int limitDanger = 2;
    
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
    
    public static void printDiagram(){
        for(int i=0; i<n; i++ ){
            for(int j=0; j<n; j++){
                System.out.print(diagram[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static Stack<Integer[]> setCoordinates(Stack<String> lines){
        Stack<Integer[]> points = new Stack<Integer[]>();
        int x1=0, x2=0, y1=0, y2=0;
        for(String s: lines){
            x1 = Integer.parseInt(s.split(" -> ")[0].split(",")[0]);
            y1 = Integer.parseInt(s.split(" -> ")[0].split(",")[1]);
            x2 = Integer.parseInt(s.split(" -> ")[1].split(",")[0]);
            y2 = Integer.parseInt(s.split(" -> ")[1].split(",")[1]);
            points.add(new Integer[] {x1,y1,x2,y2});
        }
        return points;
    }
    
    public static boolean has45Degree(Integer[] line){
        boolean result = false;
        int m = (line[3]-line[1])/(line[2]-line[0]);
        if(m==1 || m==-1){
            result = true;
        }
        return result;
    }
    
    public static void drawLine(Integer[] line){
        int i = 0, j =0;
        if(line[0].equals(line[2])){ // Vertical
            i=line[1];
            while(i!=line[3]){
                if(diagram[i][line[0]]=="."){
                    diagram[i][line[0]] = "1";
                }else{
                    diagram[i][line[0]] = String.valueOf(Integer.parseInt(diagram[i][line[0]])+1);
                }
                if(line[1]<line[3]){
                    i++;
                }else{
                    i--;
                }      
            }
            if(diagram[line[3]][line[0]]=="."){
                diagram[line[3]][line[0]] = "1";
            }else{
                diagram[line[3]][line[0]] = String.valueOf(Integer.parseInt(diagram[line[3]][line[0]])+1);
            }
        }else if(line[1].equals(line[3])){ // Horizontal
            i=line[0];
            while(i!=line[2]){
                if(diagram[line[1]][i]=="."){
                    diagram[line[1]][i] = "1";
                }else{
                    diagram[line[1]][i] = String.valueOf(Integer.parseInt(diagram[line[1]][i])+1);
                }
                if(line[0]<line[2]){
                    i++;                
                }else{
                    i--;
                }      
            }
            if(diagram[line[1]][line[2]]=="."){
                diagram[line[1]][line[2]] = "1";
            }else{
                diagram[line[1]][line[2]] = String.valueOf(Integer.parseInt(diagram[line[1]][line[2]])+1);
            }
        }else{
            //System.out.println("Oblicual Line");
            //System.out.println(line[0] + "," + line[1] + " -> " + line[2] + "," + line[3]);
        }
    }
    
    public static void drawLine2(Integer[] line){
        int i = 0, j =0;
        if(line[0].equals(line[2])){ // Vertical
            i=line[1];
            while(i!=line[3]){
                if(diagram[i][line[0]]=="."){
                    diagram[i][line[0]] = "1";
                }else{
                    diagram[i][line[0]] = String.valueOf(Integer.parseInt(diagram[i][line[0]])+1);
                }
                if(line[1]<line[3]){
                    i++;
                }else{
                    i--;
                }      
            }
            if(diagram[line[3]][line[0]]=="."){
                diagram[line[3]][line[0]] = "1";
            }else{
                diagram[line[3]][line[0]] = String.valueOf(Integer.parseInt(diagram[line[3]][line[0]])+1);
            }
        }else if(line[1].equals(line[3])){ // Horizontal
            i=line[0];
            while(i!=line[2]){
                if(diagram[line[1]][i]=="."){
                    diagram[line[1]][i] = "1";
                }else{
                    diagram[line[1]][i] = String.valueOf(Integer.parseInt(diagram[line[1]][i])+1);
                }
                if(line[0]<line[2]){
                    i++;                
                }else{
                    i--;
                }      
            }
            if(diagram[line[1]][line[2]]=="."){
                diagram[line[1]][line[2]] = "1";
            }else{
                diagram[line[1]][line[2]] = String.valueOf(Integer.parseInt(diagram[line[1]][line[2]])+1);
            }
        }else if(has45Degree(line)){
            i=line[0];
            j=line[1];
            while(i!=line[2] && j!=line[3]){
                if(diagram[j][i]=="."){
                    diagram[j][i] = "1";
                }else{
                    diagram[j][i] = String.valueOf(Integer.parseInt(diagram[j][i])+1);
                }
                if(line[0]<line[2]){
                    i++;
                }else{
                    i--;
                }
                if(line[1]<line[3]){
                    j++;
                }else{
                    j--;
                } 
            }
            if(diagram[line[3]][line[2]]=="."){
                diagram[line[3]][line[2]] = "1";
            }else{
                diagram[line[3]][line[2]] = String.valueOf(Integer.parseInt(diagram[line[3]][line[2]])+1);
            }
        }else{
            System.out.println("Oblicual Line without 45 degrees");
            System.out.println(line[0] + "," + line[1] + " -> " + line[2] + "," + line[3]);
        }
    }
    
    public static int contDangerPoints(){
        int cont = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!diagram[i][j].equals(".") && Integer.parseInt(diagram[i][j])>=limitDanger){
                    cont++;
                }
            }
        }
        return cont;
    }
    
    public static void main(String[] args) {
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                diagram[i][j] = ".";
            }
        }
        Stack<String> lines = readFile(path);
        Stack<Integer[]> points = setCoordinates(lines);
        System.out.println("PART I");
        
        for(Integer[] line: points){
            drawLine(line);
        }
        //printDiagram();
        System.out.println("Danger Points: " + contDangerPoints());
        
        System.out.println("\nPART II");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                diagram[i][j] = ".";
            }
        }
        
        for(Integer[] line: points){
            drawLine2(line);
        }
        //printDiagram();
        System.out.println("Danger Points: " + contDangerPoints());
        
    }
    
}
