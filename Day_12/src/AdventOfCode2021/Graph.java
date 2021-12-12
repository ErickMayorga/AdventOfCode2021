/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2021;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Erick
 */
public class Graph {
    private int n;
    private int numberOfEdges;
    private double[] distance;
    private String[] path;
    private ArrayList<String> nodes;
    private static int[][] edges;
    private boolean[] isVisited;
    
    public  Graph(int n){
        this.n = n;
        numberOfEdges=0;
        nodes = new ArrayList<>(n);
        edges  = new int[n][n];
        isVisited = new boolean[n+1];
        distance = new double[n];
        for (int i = 0; i <n ; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }

        path = new String[n];
        for (int i = 0; i <n ; i++) {
            path[i] = "";
        }
    }

    public static void showEdges(){
        for (int[] edge: edges) {
            System.out.println(Arrays.toString(edge));
        }

    }
    
    public  void addNode(String s){
        nodes.add(s);
    }
    
    public  void addEdges(int start,int end){
        edges[start][end] = 1;
        edges[end][start] = 1;
        numberOfEdges++;
    }
    
    public int firstNode(int index){
        for(int i=0; i<nodes.size(); i++) {
            if(edges[index][i]!=0) 
                return i;
        }
        return n;
    }

    public int nextNode(int index,int fisrtNode){
        for(int i=fisrtNode+1; i<nodes.size(); i++) {
            if(edges[index][i]!=0) 
                return i;
        }
        return n;
    }

    // Devuelve el siguiente vértice requerido a través de la matriz de distancia y la matriz de acceso dadas
    public int indexGet(double[] distance, boolean[] isVisited){
        int node=0;
        double mindis=Double.POSITIVE_INFINITY;
        for(int i=0; i<distance.length; i++) {
            if(!isVisited[i]){
                if(distance[i]<mindis){
                    mindis = distance[i];
                    node = i;
                }
                //node = i;
            }
        }
        return node;
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
    
    private ArrayList<String> pathEnd = new ArrayList<>();
    
    public void showPaths1(int start, int previus, int end){
        for(int i=0; i<nodes.size(); i++) {
            if(edges[start][i]!=0) {
                if(!pathEnd.contains(nodes.get(i))){
                    if(i == end){
                    pathEnd.add(nodes.get(i));
                    pathEnd.clear();
                    return;
                    }
                    else if(i!=previus){
                        pathEnd.add(nodes.get(i));
                        System.out.println(i);
                        showPaths1(i, start, end);
                    }
                }
            }   
        }
    }
    
    public void showPaths(){
        // CO son las coordenadas necesarias para la iteración, headIndex es el vértice inicial de cada DIJKSTRA
        int CO;
        int start = nodes.indexOf("start");
        int end = nodes.indexOf("end");
        int headIndex = start;

        // Establece la distancia desde el punto inicial al punto inicial, naturalmente 0
        distance[start]=0;
        ArrayList<String> pathsEnd = new ArrayList<>();
        
        while (!isVisited[headIndex]){
            // CO es la primera CO que no ha sido visitada
            CO = firstNode(headIndex);
            while(isVisited[CO]){
                CO = nextNode(headIndex,CO);
            }
            
            // Si el vértice headIndex no tiene vértices adyacentes que no hayan sido visitados, la coordenada del vértice se obtiene como n, lo que indica que es el último nodo desconocido, y solo necesita establecerse como conocido
            if (CO==n) {
                isVisited[headIndex]=true;
            }else{  // Ejecuta el paso 2 para todos los vértices adyacentes a través de un bucle
                while (!isVisited[CO] && CO<n) {
                    isVisited[headIndex]=true;
                    double currentDis = distance[headIndex] + edges[headIndex][CO];
                    if (currentDis<distance[CO]) {
                        distance[CO] = currentDis;
                        path[CO] = path[headIndex] + "->" + nodes.get(headIndex);
                    }
                    //path[CO] = path[headIndex]+"->"+nodes.get(headIndex);
                    if(CO+1==end){
                        pathsEnd.add(path[CO]);
                    }
                    CO = nextNode(headIndex, CO);
                }
            }
            headIndex = indexGet(distance,isVisited);
        }
        
        for (int i=0; i<n; i++) {
            path[i] += "->" + nodes.get(i);
            System.out.println(path[i]);
        }
        int cont = 0;
        for(String pathEnd: pathsEnd){
            //System.out.println(pathEnd);
            cont++;
        }
        System.out.println("End paths: " + cont);
    }
}
