/**
 * Laufzeit maybe : O(n) = n^3
 */


package org.example;

import java.util.*;

public class Prim {

    static int n; // Number of nodes

    static int wayLength = 0;
    int parentNode = 0;

    static ArrayList<Integer> visitedNodes = new ArrayList(); // Serie of nodes

    static int[][] output;

    static ArrayList<Integer>[] graphArray; // Adjacency matrix
    static Boolean[] visited; // Visited Array

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
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        // initializing output array
        for (int i = 0; i < n; i++) {
            for (int j = 0;  j < n; j++){
                output[i][j] = 0;
            }
        }
    }

    public int[][] prim(int start) {
        int nextNode;
        // Current node is marked as visited
        visited[start] = true;
        visitedNodes.add(start);
        for (int i = 0; i < graphArray.length - 1; i++) {
            // Searching for next node
            nextNode = getNextVertex();
            // Marking next node as visited
            if (nextNode != -1) visited[nextNode] = true;
            // Add next node to path of nodes
            //visitedNodes.add(parentNode);
            //System.out.println("Parent node: " +parentNode + " Next node: " + nextNode);
            output[parentNode][nextNode] = 1;
            output[nextNode][parentNode] = 1;
            visitedNodes.add(nextNode);
        }
        return output;
    }


    public int getNextVertex() {
        int curWeight, bestNode = -1, bestWeight = Integer.MAX_VALUE;
        for (int i = 0; i < visitedNodes.size(); i++) {
            for (int curNode = 0; curNode < graphArray.length; curNode++) {
                curWeight = graphArray[visitedNodes.get(i)].get(curNode); // current weight
                // Check if current weight is less than best weight and if it isn´t already visited
                if ((curWeight < bestWeight) && !visited[curNode] && !isFull(curNode)) {
                    // best node is the current node
                    parentNode = visitedNodes.get(i);
                    bestNode = curNode;
                    bestWeight = curWeight;

                }
            }
        }
        // adding node value to length
        wayLength += bestWeight;
        return bestNode;
        //}
    }

    public boolean isFull(int curNode){
        int count = 0;
        boolean isFull = false;
        for (int i = 0; i < output.length; i++){
            if(output[curNode][i] > 0){
                count++;
            }
        }
        if (count >= 5) isFull = true;
        return isFull;
    }

    public void start(ArrayList<Integer>[] graph, int numNodes, int startNode) {
        n = numNodes;
        graphArray = graph;
        visited = new Boolean[graphArray.length];
        output = new int[numNodes][numNodes];



        //
        /*
        //
        graphArray[0].add(0);
        graphArray[0].add(4);
        graphArray[0].add(3);
        graphArray[0].add(0);
        graphArray[0].add(0);
        graphArray[0].add(0);
        graphArray[0].add(0);
        graphArray[0].add(5);
        graphArray[0].add(0);
        graphArray[0].add(0);
        //
        graphArray[1].add(4);
        graphArray[1].add(0);
        graphArray[1].add(5);
        graphArray[1].add(2);
        graphArray[1].add(4);
        graphArray[1].add(0);
        graphArray[1].add(0);
        graphArray[1].add(0);
        graphArray[1].add(0);
        graphArray[1].add(0);
        //
        graphArray[2].add(3);
        graphArray[2].add(5);
        graphArray[2].add(0);
        graphArray[2].add(0);
        graphArray[2].add(4);
        graphArray[2].add(0);
        graphArray[2].add(3);
        graphArray[2].add(3);
        graphArray[2].add(0);
        graphArray[2].add(0);
        //
        graphArray[3].add(0);
        graphArray[3].add(2);
        graphArray[3].add(0);
        graphArray[3].add(0);
        graphArray[3].add(3);
        graphArray[3].add(3);
        graphArray[3].add(0);
        graphArray[3].add(0);
        graphArray[3].add(0);
        graphArray[3].add(0);
        //
        graphArray[4].add(0);
        graphArray[4].add(4);
        graphArray[4].add(4);
        graphArray[4].add(3);
        graphArray[4].add(0);
        graphArray[4].add(2);
        graphArray[4].add(4);
        graphArray[4].add(0);
        graphArray[4].add(0);
        graphArray[4].add(0);
        //
        graphArray[5].add(0);
        graphArray[5].add(0);
        graphArray[5].add(0);
        graphArray[5].add(3);
        graphArray[5].add(2);
        graphArray[5].add(0);
        graphArray[5].add(3);
        graphArray[5].add(0);
        graphArray[5].add(0);
        graphArray[5].add(4);
        //
        graphArray[6].add(0);
        graphArray[6].add(0);
        graphArray[6].add(3);
        graphArray[6].add(0);
        graphArray[6].add(4);
        graphArray[6].add(3);
        graphArray[6].add(0);
        graphArray[6].add(2);
        graphArray[6].add(0);
        graphArray[6].add(3);
        //
        graphArray[7].add(5);
        graphArray[7].add(0);
        graphArray[7].add(3);
        graphArray[7].add(0);
        graphArray[7].add(0);
        graphArray[7].add(0);
        graphArray[7].add(2);
        graphArray[7].add(0);
        graphArray[7].add(3);
        graphArray[7].add(4);
        //
        graphArray[8].add(0);
        graphArray[8].add(0);
        graphArray[8].add(0);
        graphArray[8].add(0);
        graphArray[8].add(0);
        graphArray[8].add(0);
        graphArray[8].add(0);
        graphArray[8].add(3);
        graphArray[8].add(0);
        graphArray[8].add(2);
        //
        graphArray[9].add(0);
        graphArray[9].add(0);
        graphArray[9].add(0);
        graphArray[9].add(0);
        graphArray[9].add(0);
        graphArray[9].add(4);
        graphArray[9].add(3);
        graphArray[9].add(4);
        graphArray[9].add(2);
        graphArray[9].add(0);
        */


        // Initializing array / matrizes
        initialize();
        // Start Dijkstra Algorithm
        int[][] output = prim(startNode);

        System.out.print("Reihenfolge der Häuser ist \n");
        for (Integer visitedNode : visitedNodes) {
            System.out.print(visitedNode);
        }
        System.out.println();

        for(int i = 0; 0 < output.length; i++){

            for(int j=0; j< output.length; j++){
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }

        //System.out.printf("%n Length of visited nodes %d%n", wayLength);

    }


}
