package com.risk.validate;

import java.util.*; 
import java.util.LinkedList; 


/**
 * Java Program to check if Graph is connected or not.
 */
public class ConnectedGraph { 
	private int noOfterritory;   // No. of vertices 
	private LinkedList<Integer> adjacencyList[];

	/**
	 * Constructor initialize territory size of vertices
	 * @param territorySize number of territory
	 */
	ConnectedGraph(int territorySize) { 
		noOfterritory = territorySize; 
		adjacencyList = new LinkedList[noOfterritory]; 
		for (int i=0; i<noOfterritory; ++i) {
			adjacencyList[i] = new LinkedList();
		}       
	} 

	/**
	 * Function to add an connection between Territory and it's Adjacent Territory.
	 * @param territory Number of Territory
	 * @param adjacentTerritory Number of Adjacent Territory
	 */
	void addConnectionLine(int territory,int adjacentTerritory){
		adjacencyList[territory].add(adjacentTerritory); 
	} 

	/**
	 * This is a recursive function which prints DFS starting from first territory. 
	 * @param territory Number of Territory
	 * @param visited mark node as visited
	 */
	void DFSTraverse(int territory,Boolean visited[]) { 
		visited[territory] = true; // Mark the current node as visited
		int n; 
		Iterator<Integer> i = adjacencyList[territory].iterator(); // Recur for all the vertices adjacent to this vertex
		while (i.hasNext()) 
		{ 
			n = i.next(); 
			if (!visited[n]) {
				DFSTraverse(n, visited); 
			}   
		} 
	} 

	/**
	 * This method returns Transpose of Graph.
	 * @return transpose of graph
	 */
	ConnectedGraph getTransposeOfGraph() { 
		ConnectedGraph g = new ConnectedGraph(noOfterritory); 
		for (int v = 0; v < noOfterritory; v++) { 
			// Recur for all the vertices adjacent to this vertex 
			Iterator<Integer> i = adjacencyList[v].listIterator(); 
			while (i.hasNext()) 
				g.adjacencyList[i.next()].add(v); 
		} 
		return g; 
	} 

	/**
	 * The function that returns true if map is connected. 
	 * @return true if graph is connected.
	 */
	Boolean isGraphStronglyConnected() {  
		Boolean visited[] = new Boolean[noOfterritory]; 
		for (int i = 0; i < noOfterritory; i++) {
			visited[i] = false;
		}	 
		DFSTraverse(0, visited); 
		for (int i = 0; i < noOfterritory; i++) {
			if (visited[i] == false) 
				return false; 
		}
		ConnectedGraph gr = getTransposeOfGraph(); 
		for (int i = 0; i < noOfterritory; i++) {
			visited[i] = false; 
		} 
		gr.DFSTraverse(0, visited); 
		for (int i = 0; i < noOfterritory; i++) {
			if (visited[i] == false) 
				return false; 
		}
		return true; 
	}
	
	
}