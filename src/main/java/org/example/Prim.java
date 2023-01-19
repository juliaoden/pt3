/**
 * Laufzeit maybe : O(n) = n^3
 */


package org.example;

import java.util.*;

public class Prim {
    static int wayLength = 0; // Length of all the cables
    int parentNode = 0; // Starting point of one cable
    static ArrayList<Integer> visitedNodes = new ArrayList(); // Stack of visited nodes
    static int[][] output;// matrix which get´s returned
    static ArrayList<Integer>[] graphArray; // Graph matrix
    static Boolean[] visited; // Visited array


    // Method to initialize all necessary arrays
    public void initialize() {
        // initializing graph array
        for (int i = 0; i < graphArray.length; i++) {
            for (int j = 0; j < graphArray.length; j++) {
                if (graphArray[i].get(j) == 0) {
                    graphArray[i].set(j, Integer.MAX_VALUE);
                }
            }
        }

        // initializing visited array
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        // initializing output array
        for (int i = 0; i < output.length; i++) {
            for (int j = 0;  j < output.length; j++){
                output[i][j] = 0;
            }
        }
    }

    public void prim(int start) {
        int nextNode;
        // Current node is marked as visited
        visited[start] = true;
        // Adding start node to visited node stack
        visitedNodes.add(start);
        for (int i = 0; i < graphArray.length - 1; i++) {
            // Searching for next node
            nextNode = getNextVertex();
            // Marking next node as visited
            if (nextNode != -1) visited[nextNode] = true;
            // Adding next node to path of nodes
            visitedNodes.add(nextNode);
        }
    }

    // Method for finding next cheapest node
    public int getNextVertex() {
        int curWeight, bestNode = -1, bestWeight = Integer.MAX_VALUE;
        for (int i = 0; i < visitedNodes.size(); i++) {
            for (int curNode = 0; curNode < graphArray.length; curNode++) {
                // current weight
                curWeight = graphArray[visitedNodes.get(i)].get(curNode);
                // Check if current weight is less than best weight, if it isn´t already visited and if there are less than 6 connections
                if ((curWeight < bestWeight) && !visited[curNode] && !isFull(curNode)) {
                    // Setting parent node as current node from stack
                    parentNode = visitedNodes.get(i);
                    // Best node is the current node
                    bestNode = curNode;
                    // Best weight is the current weight
                    bestWeight = curWeight;
                }
            }
        }
        // adding the best weight to the length
        wayLength += bestWeight;
        // Adding the way to the output array
        output[parentNode][bestNode] = bestWeight;
        output[bestNode][parentNode] = bestWeight;

        return bestNode;
        //}
    }

    // Method for checking if the node is connected to more than 5 other nodes
    public boolean isFull(int curNode){
        int count = 0;
        // Initially the node isn´t connected to more than 5 nodes
        boolean isFull = false;
        for (int i = 0; i < output.length; i++){
            if(output[curNode][i] == 1){
                // if there is a connection to another node increase counter
                count++;
            }
        }
        // Node is connected to more than 5 nodes
        if (count >= 5) isFull = true;
        return isFull;
    }

    public int[][] start(ArrayList<Integer>[] graph, int numNodes, int startNode, ArrayList<String> nodeNames) {
        graphArray = graph;
        visited = new Boolean[graphArray.length];
        output = new int[numNodes][numNodes];

        // Initializing array / matrizes
        initialize();
        // Start Dijkstra Algorithm
        prim(startNode);

        System.out.println("Gesamte Länge ist: " + wayLength);

        return output;
    }
}
