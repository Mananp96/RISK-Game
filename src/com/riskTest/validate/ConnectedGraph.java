package com.riskTest.validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class ConnectedGraph {

    private int V;   // No. of vertices 
    private LinkedList<String> adjacencyList;//Adjacency List
    Map<String,ArrayList<String>> adjcentTerritories;
  
    //Constructor 
    public ConnectedGraph(Map<String,ArrayList<String>> adjcentTerritories) 
    { 
       this.adjcentTerritories = new HashMap<>();
       
       adjacencyList = new LinkedList<>(); 
       for(Entry<String, ArrayList<String>> entry : adjcentTerritories.entrySet()) {
    	   adjacencyList[entry.getKey()] = new LinkedList();
       }
            
    } 
  
    //Function to add an edge into the graph 
    void addEdge(String territory,String adjTerritory){
    	adjacencyList[territory].add(adjTerritory);
    } 
  
    // A recursive function to print DFS starting from v 
    void DFSUtil(int v,Boolean visited[]) 
    { 
        // Mark the current node as visited and print it 
        visited[v] = true; 
  
        String n; 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<String> i = adjacencyList[v].iterator(); 
        while (i.hasNext()) 
        { 
            n = i.next(); 
            if (!visited[n]) 
                DFSUtil(n,visited); 
        } 
    } 
  
    // Function that returns transpose of this graph 
    ConnectedGraph getTranspose() 
    { 
        ConnectedGraph g = new ConnectedGraph(V); 
        for (int v = 0; v < V; v++) 
        { 
            // Recur for all the vertices adjacent to this vertex 
            Iterator<String> i = adjacencyList[v].listIterator(); 
            while (i.hasNext()) 
                g.adjacencyList[i.next()].add(v); 
        } 
        return g; 
    } 
  
    // The main function that returns true if graph is strongly 
    // connected 
    Boolean isSC() 
    { 
        // Step 1: Mark all the vertices as not visited 
        // (For first DFS) 
        Boolean visited[] = new Boolean[V]; 
        for (int i = 0; i < V; i++) 
            visited[i] = false; 
  
        // Step 2: Do DFS traversal starting from first vertex. 
        DFSUtil(0, visited); 
  
        // If DFS traversal doesn't visit all vertices, then 
        // return false. 
        for (int i = 0; i < V; i++) 
            if (visited[i] == false) 
                return false; 
  
        // Step 3: Create a reversed graph 
        ConnectedGraph gr = getTranspose(); 
  
        // Step 4: Mark all the vertices as not visited (For 
        // second DFS) 
        for (int i = 0; i < V; i++) 
            visited[i] = false; 
  
        // Step 5: Do DFS for reversed graph starting from 
        // first vertex. Staring Vertex must be same starting 
        // point of first DFS 
        gr.DFSUtil(0, visited); 
  
        // If all vertices are not visited in second DFS, then 
        // return false 
        for (int i = 0; i < V; i++) 
            if (visited[i] == false) 
                return false; 
  
        return true; 
    } 
}
