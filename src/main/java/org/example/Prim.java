/**
 * Laufzeit maybe : O(n) = n^3
 */


package org.example;

import java.util.*;

public class Prim {
    // length of all the cables
    static int wayLength = 0;
    // starting point of one cable
    int parentNode = 0;
    // stack of visited nodes
    static ArrayList<Integer> visitedNodes = new ArrayList();
    // matrix which get´s returned
    static int[][] output;
    // graph matrix
    static ArrayList<Integer>[] graphArray;
    // visited array
    static Boolean[] visited;


    // method to initialize all necessary arrays
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
        // current node is marked as visited
        visited[start] = true;
        // adding start node to visited node stack
        visitedNodes.add(start);
        for (int i = 0; i < graphArray.length - 1; i++) {
            // searching for next node
            nextNode = getNextVertex();
            // marking next node as visited
            if (nextNode != -1) visited[nextNode] = true;
            // adding next node to path of nodes
            visitedNodes.add(nextNode);
        }
    }

    // method for finding next cheapest node
    public int getNextVertex() {
        int curWeight, bestNode = -1, bestWeight = Integer.MAX_VALUE;
        for (int i = 0; i < visitedNodes.size(); i++) {
            for (int curNode = 0; curNode < graphArray.length; curNode++) {
                // current weight
                curWeight = graphArray[visitedNodes.get(i)].get(curNode);
                // check if current weight is less than best weight, if it isn´t already visited and if there are less than 6 connections
                if ((curWeight < bestWeight) && !visited[curNode] && !isFull(curNode)) {
                    // setting parent node as current node from stack
                    parentNode = visitedNodes.get(i);
                    // best node is the current node
                    bestNode = curNode;
                    // best weight is the current weight
                    bestWeight = curWeight;
                }
            }
        }
        // adding the best weight to the length
        wayLength += bestWeight;
        // adding the way to the output array
        output[parentNode][bestNode] = bestWeight;
        output[bestNode][parentNode] = bestWeight;

        return bestNode;
    }

    // method for checking if the node is connected to more than 5 other nodes
    public boolean isFull(int curNode){
        int count = 0;
        // initially the node isn´t connected to more than 5 nodes
        boolean isFull = false;
        for (int i = 0; i < output.length; i++){
            if(output[curNode][i] == 1){
                // if there is a connection to another node increase counter
                count++;
            }
        }
        // node is connected to more than 5 nodes
        if (count >= 5) isFull = true;
        return isFull;
    }

    public int[][] start(ArrayList<Integer>[] graph, int numNodes, int startNode, ArrayList<String> nodeNames) {
        graphArray = graph;
        visited = new Boolean[graphArray.length];
        output = new int[numNodes][numNodes];
        initialize();
        prim(startNode);

        System.out.println("Total length is: " + wayLength);

        return output;
    }
}
