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
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Day_12 {

    private static String path = "files/RemainingCaves2.txt";
    private static File file = null;
    private static FileReader fr = null;
    private static BufferedReader br = null;
    
    private static Stack<String> nodes= new Stack<>();
    
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

    public static Graph getGraph2(Stack<String> lines){
        String[] parts;
        int cont=0;
        for(String connection: lines){
            parts = connection.split("-");
            if(!nodes.contains(parts[0])){
                nodes.push(parts[0]);
                cont++;
            }
            if(!nodes.contains(parts[1])){
                nodes.push(parts[1]);
                cont++;
            }
        }
        Graph graph = new Graph(cont);
        //System.out.println(nodes.toString());
        for(String node: nodes){
            graph.addNode(node);
        }
        
        int start = 0, end =0;
        for(String connection: lines){
            parts = connection.split("-");
            start = nodes.indexOf(parts[0]);
            end = nodes.indexOf(parts[1]);
            graph.addEdge(start, end);
        }
        return graph;
    }

    public static void main(String[] args) {
        Stack<String> lines = readFile(path);
        
        System.out.println("PART I");
        
        Graph graph = getGraph2(lines);
        int start = nodes.indexOf("start");
        int end = nodes.indexOf("end");

        graph.showPaths(start, end);
        System.out.println("Number of paths: " + graph.getCont());
    }
    
}
