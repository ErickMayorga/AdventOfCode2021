/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2021;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Erick
 */
public class Graph {
    private int n;
    private ArrayList<Integer>[] edges;
    private Stack<String> nodes = new Stack<>();
    private int cont = 0;
 
    // Constructor
    public Graph(int n){
        this.n = n;
        edges = new ArrayList[this.n];
 
        for (int i = 0; i < this.n; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public int getCont() {
        return cont;
    }
    
    public void addNode(String node) {
        nodes.add(node);
    }

    public void addEdge(int start, int end){
        edges[start].add(end);
        edges[end].add(start);
    }
 
    public boolean isUpperCase(String node){
        int n = node.length();
        int cont =0;
        boolean result = false;
        for(char c: node.toCharArray()){
            if(Character.isUpperCase(c))
                cont++;
        }
        if(cont==n){
            result = true;
        }
        return result;
    }
    
    public void showPaths(int start, int end){
        boolean[] isVisited = new boolean[n];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(start);
        cont = 0;
        recursivePaths(start, end, isVisited, pathList);
    }
    
    public void printPath(List<Integer> path){
        for(Integer i: path){
            System.out.print("->" + nodes.get(i));
        }
        System.out.println();
    }
    
    public int contNodes(List<Integer> path, int node){
        int cont = 0;
        for(int i=0; i<path.size(); i++){
            if(path.contains(node)){
                cont++;
            }
        }
        return cont;
    }
 
    private void recursivePaths(Integer u, Integer d,boolean[] isVisited,List<Integer> path){
        if (u.equals(d)) {
            //printPath(path);
            cont++;
            return;
        }
        
        //if(!isUpperCase(nodes.get(u)) && contNodes(path, u)>=2){
        if(!isUpperCase(nodes.get(u))){
            isVisited[u] = true;
        }else{
            isVisited[u] = false;
        }
        
 
        for (Integer i : edges[u]) {
            if (!isVisited[i]) {
                if((contNodes(path,i)<2 || isUpperCase(nodes.get(i)))){
                    path.add(i);
                }
                recursivePaths(i, d, isVisited, path);
                
                path.remove(i);
            }
        }
 
        // Mark the current node
        isVisited[u] = false;
        
    }
}
