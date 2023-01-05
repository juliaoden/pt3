/**
 * Laufzeit maybe : O(n) = n^3
 */


package org.example;

import java.util.*;

public class Dijkstra {

    static int n; // Number of nodes

    static int wayLength = 0;

    static ArrayList<Integer> visitedNodes = new ArrayList(); // Serie of nodes

    static ArrayList<Integer>[] graphArray; // Adjacency matrix
    static Boolean[] visited; // Visited Array

    public void setMaxInt(ArrayList<Integer>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i].get(j) == 0) {
                    graph[i].set(j, Integer.MAX_VALUE);
                }
            }
        }
    }

    public void dijkstra(int start) {
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
            if (!visitedNodes.contains(nextNode)) visitedNodes.add(nextNode);

        }

    }


    public int getNextVertex() {
        int curWeight, bestNode = -1, bestWeight = Integer.MAX_VALUE;
        for (int i = 0; i < visitedNodes.toArray().length; i++) {
            for (int curNode = 0; curNode < graphArray.length; curNode++) {
                curWeight = graphArray[visitedNodes.get(i)].get(curNode); // current weight
                // Check if current weight is less than best weight and if it isn´t already visited
                if ((curWeight < bestWeight) && !visited[curNode]) {
                    // best node is the current node
                    bestNode = curNode;
                    bestWeight = curWeight;

                }
            }
        }
        // the current node is the penultimate node
        /*if(bestNode == currentNode){
            // TODO: Was passiert, wenn letzter nicht besuchter Knoten keine Verbindung zum derzeitigen Knoten hat?
            int indexOfLastNode = java.util.Arrays.asList(visited).indexOf(Boolean.FALSE);
            // adding node value to length
            if(indexOfLastNode != -1) wayLength += graphArray[currentNode].get(indexOfLastNode);
            // find the node which isn´t already visited
            return indexOfLastNode;
        } else{*/
        // adding node value to length
        wayLength += bestWeight;
        return bestNode;
        //}
    }

    public void start(Dijkstra d, ArrayList<Integer>[] graph, int numNodes) {
        n = numNodes;
        graphArray = graph;
        visited = new Boolean[graphArray.length];

        // initializing vistied array
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        // TODO: Vom Nutzenden eingeben lassen
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


        // Replace all zeros with MAX_INT
        d.setMaxInt(graphArray);
        // Start Dijkstra Algorithm
        d.dijkstra(0);

        System.out.print("Reihenfolge der Häuser ist ");
        for (Integer visitedNode : visitedNodes) {
            System.out.print(visitedNode);
        }
        System.out.printf("%n Length of visited nodes %d%n", wayLength);

    }


}
