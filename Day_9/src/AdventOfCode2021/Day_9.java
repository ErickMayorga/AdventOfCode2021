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
public class Day_9 {
    
    private static String path = "files/hightmap.txt";
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
    
    public static Stack<int[]> getLowPoints(int[][] map){
        int n = map.length;
        int m = map[0].length;
        Stack<int[]> lowPoints = new Stack<int[]>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i -1 < 0){
                    if(j - 1 < 0){
                        if(map[i][j]< map[i+1][j] && map[i][j]< map[i][j+1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }else if(j + 1 == m){
                        if(map[i][j]< map[i+1][j] && map[i][j]< map[i][j-1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }else{
                        if(map[i][j]< map[i+1][j] && map[i][j]< map[i][j-1] && map[i][j]< map[i][j+1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }
                }else if(i+1 == n){
                    if(j - 1 < 0){
                        if(map[i][j]< map[i-1][j] && map[i][j]< map[i][j+1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }else if(j + 1 == m){
                        if(map[i][j]< map[i-1][j] && map[i][j]< map[i][j-1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }else{
                        if(map[i][j]< map[i-1][j] && map[i][j]< map[i][j-1] && map[i][j]< map[i][j+1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }
                }else{
                    if(j - 1 < 0){
                        if(map[i][j]< map[i-1][j] && map[i][j]< map[i+1][j] && map[i][j]< map[i][j+1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }else if(j + 1 == m){
                        if(map[i][j]< map[i-1][j] && map[i][j]< map[i+1][j] && map[i][j]< map[i][j-1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }else{
                        if(map[i][j]< map[i-1][j] && map[i][j]< map[i+1][j] && map[i][j]< map[i][j+1] && map[i][j]< map[i][j-1]){
                            lowPoints.push(new int[] {i,j});
                        }
                    }
                }
            }
        }
        
        return lowPoints;
        
    }
    
    public static int getBasin(int[] point, int[][] map){
        int basin = 0, cont =0;
        int n = map.length;
        int m = map[0].length;
        Stack<int[]> basinPoints =  new Stack<>();
        basinPoints.add(point);
        int limit = 1;
        int P[] = point;
        do{
            P = basinPoints.get(cont);
            if(P[0]-1<0){
                if(P[1]-1<0){
                    if(map[P[0]+1][P[1]]!=9){   // Down
                        basin += map[P[0]+1][P[1]];
                        basinPoints.add(new int[] {P[0]+1, P[1]});
                    }
                    if(map[P[0]][P[1]+1]!=9){   // Right
                        basin += map[P[0]][P[1]+1];
                        basinPoints.add(new int[] {P[0], P[1]+1});
                    }
                }else if(P[1]+1 == m){
                    if(map[P[0]+1][P[1]]!=9){   // Down
                        basin += map[P[0]+1][P[1]];
                        basinPoints.add(new int[] {P[0]+1, P[1]});
                    }
                    if(map[P[0]][P[1]-1]!=9){   // Left
                        basin += map[P[0]][P[1]-1];
                        basinPoints.add(new int[] {P[0], P[1]-1});
                    }
                }else{
                    if(map[P[0]+1][P[1]]!=9){   //Down
                        basin += map[P[0]+1][P[1]];
                        basinPoints.add(new int[] {P[0]+1, P[1]});
                    }
                    if(map[P[0]][P[1]+1]!=9){   // Right
                        basin += map[P[0]][P[1]+1];
                        basinPoints.add(new int[] {P[0], P[1]+1});
                    }
                    if(map[P[0]][P[1]-1]!=9){   // Left
                        basin += map[P[0]][P[1]-1];
                        basinPoints.add(new int[] {P[0], P[1]-1});
                    }
                }
            }else if(P[0]+1 == n){
                if(P[1]-1<0){
                    if(map[P[0]-1][P[1]]!=9){   // Up
                        basin += map[P[0]-1][P[1]];
                        basinPoints.add(new int[] {P[0]-1, P[1]});
                    }
                    if(map[P[0]][P[1]+1]!=9){   // Right
                        basin += map[P[0]][P[1]+1];
                        basinPoints.add(new int[] {P[0], P[1]+1});
                    }
                }else if(P[1]+1 == m){
                    if(map[P[0]-1][P[1]]!=9){   // Up
                        basin += map[P[0]-1][P[1]];
                        basinPoints.add(new int[] {P[0]-1, P[1]});
                    }
                    if(map[P[0]][P[1]-1]!=9){   // Left
                        basin += map[P[0]][P[1]-1];
                        basinPoints.add(new int[] {P[0], P[1]-1});
                    }
                }else{
                    if(map[P[0]-1][P[1]]!=9){   //Up
                        basin += map[P[0]-1][P[1]];
                        basinPoints.add(new int[] {P[0]-1, P[1]});
                    }
                    if(map[P[0]][P[1]+1]!=9){   // Right
                        basin += map[P[0]][P[1]+1];
                        basinPoints.add(new int[] {P[0], P[1]+1});
                    }
                    if(map[P[0]][P[1]-1]!=9){   // Left
                        basin += map[P[0]][P[1]-1];
                        basinPoints.add(new int[] {P[0], P[1]-1});
                    }
                }
            }else{
                if(P[1]-1<0){
                    if(map[P[0]-1][P[1]]!=9){   //Up
                        basin += map[P[0]-1][P[1]];
                        basinPoints.add(new int[] {P[0]-1, P[1]});
                    }
                    if(map[P[0]+1][P[1]]!=9){   //Down
                        basin += map[P[0]+1][P[1]];
                        basinPoints.add(new int[] {P[0]+1, P[1]});
                    }
                    if(map[P[0]][P[1]+1]!=9){   // Right
                        basin += map[P[0]][P[1]+1];
                        basinPoints.add(new int[] {P[0], P[1]+1});
                    }
                }else if(P[1]+1 == m){
                    if(map[P[0]-1][P[1]]!=9){   //Up
                        basin += map[P[0]-1][P[1]];
                        basinPoints.add(new int[] {P[0]-1, P[1]});
                    }
                    if(map[P[0]+1][P[1]]!=9){   //Down
                        basin += map[P[0]+1][P[1]];
                        basinPoints.add(new int[] {P[0]+1, P[1]});
                    }
                    if(map[P[0]][P[1]-1]!=9){   // Left
                        basin += map[P[0]][P[1]-1];
                        basinPoints.add(new int[] {P[0], P[1]-1});
                    }
                }else{
                    if(map[P[0]-1][P[1]]!=9){   //Up
                        basin += map[P[0]-1][P[1]];
                        basinPoints.add(new int[] {P[0]-1, P[1]});
                    }
                    if(map[P[0]+1][P[1]]!=9){   //Down
                        basin += map[P[0]+1][P[1]];
                        basinPoints.add(new int[] {P[0]+1, P[1]});
                    }
                    if(map[P[0]][P[1]+1]!=9){   // Right
                        basin += map[P[0]][P[1]+1];
                        basinPoints.add(new int[] {P[0], P[1]+1});
                    }
                    if(map[P[0]][P[1]-1]!=9){   // Left
                        basin += map[P[0]][P[1]-1];
                        basinPoints.add(new int[] {P[0], P[1]-1});
                    }
                }
            }
            limit = basinPoints.size();
            cont++;
        }while(cont != limit);
        return basin;
    }
    
    public static int getMutiplyBasin(int[][] map){
        int result = 1, cont = 0;
        Stack<int[]> lowPoints = getLowPoints(map);
        int n = map.length;
        int m = map[0].length;
        for(int[] P: lowPoints){
            result *= getBasin(P, map);
            cont++;
            if(cont==3){
                break;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        Stack<String> hights = readFile(path);
        int n = hights.size();
        int m = hights.get(0).length();
        int[][] map = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(String.valueOf(hights.get(i).charAt(j)));
            }
        }
        
        System.out.println("PART I");
        int suma = 0;
        Stack<int[]> lowPoints = getLowPoints(map);
        for(int[] P: lowPoints){
            suma += map[P[0]][P[1]] +1;
        }
        System.out.println("Sum of risk levels: " + suma);
        
        System.out.println("PART II");
        System.out.println("Multiplication Basins: " + getMutiplyBasin(map));
    }
    
}
