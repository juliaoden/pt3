package org.example;

import java.util.*;

public class Dijkstra {

    static int n = 10; // Number of nodes

    static int wayLength = 0;

    static ArrayList<Integer> visitedNodes = new ArrayList(); // Serie of nodes

    static ArrayList<Integer>[] graphArray = new ArrayList[n]; // Adjacency matrix
    static Boolean[] visited = new Boolean[graphArray.length]; // Visited Array

    public void initialize(){
        for (int i = 0; i < n; i++) {
            graphArray[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
    }

    public void setMaxInt(ArrayList<Integer>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i].get(j) == 0) {
                    graph[i].set(j, Integer.MAX_VALUE);
                }
            }
        }
    }

    public void dijkstra(ArrayList<Integer>[] graph, int currentNode, Boolean[] visited) {
        int nextNode;
        // Current node is marked as visited
        visited[currentNode] = true;
        for (int i = 0; i < graph.length; i++) {
            // Searching for next node
            nextNode = getNextVertex(graph, visited, currentNode);
            // Marking next node as visited
            if(nextNode != -1) visited[nextNode] = true;
            // Add next node to path of nodes
            visitedNodes.add(currentNode);
            // Repeat process with next node
            currentNode = nextNode;
        }

    }


    public int getNextVertex(ArrayList<Integer>[] graph, Boolean[] visited, int currentNode) {
        int curWeight = 0, nextWeight, bestNode = currentNode, bestWeight = Integer.MAX_VALUE;
        for (int curNode = 0; curNode < graph.length - 1; curNode++) {
            curWeight = graph[currentNode].get(curNode); // current weight
            nextWeight = graph[currentNode].get(curNode + 1); // next weight
            // Check if next weight is less than current weight and if it isn´t already visited
            if ((nextWeight < curWeight) && (nextWeight < bestWeight)  && !visited[curNode+1]) {
                // best node is the next node
                bestNode = curNode + 1;
                bestWeight = nextWeight;

            }
        }

        // the current node is the penultimate node
        if(bestNode == currentNode){
            // TODO: Was passiert, wenn letzter nicht besuchter Knoten keine Verbindung zum derzeitigen Knoten hat?
            int indexOfLastNode = java.util.Arrays.asList(visited).indexOf(Boolean.FALSE);
            // adding node value to length
            if(indexOfLastNode != -1) wayLength += graph[currentNode].get(indexOfLastNode);
            // find the node which isn´t already visited
            return indexOfLastNode;
        } else{
            // adding node value to length
            wayLength += bestWeight;
            return bestNode;
        }
    }

    public void start(Dijkstra d) {

        // initializing
        d.initialize();

        // TODO: Vom Nutzenden eingeben lassen
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
        graphArray[7].add(0);
        graphArray[7].add(0);
        graphArray[7].add(3);
        graphArray[7].add(0);
        graphArray[7].add(4);
        graphArray[7].add(3);
        graphArray[7].add(0);
        graphArray[7].add(2);
        graphArray[7].add(0);
        graphArray[7].add(3);
        //
        graphArray[6].add(5);
        graphArray[6].add(0);
        graphArray[6].add(3);
        graphArray[6].add(0);
        graphArray[6].add(0);
        graphArray[6].add(0);
        graphArray[6].add(2);
        graphArray[6].add(0);
        graphArray[6].add(3);
        graphArray[6].add(4);
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



        // Replace all zeros with MAX_INT
        d.setMaxInt(graphArray);
        // Start Dijkstra Algorithm
        d.dijkstra(graphArray, 0, visited);

        System.out.print("Reihenfolge der Häuser ist ");
        for (int i = 0; i < visitedNodes.size(); i++) {
            System.out.print(visitedNodes.get(i));
        }
        System.out.printf("%n Length of visited nodes %d%n",  wayLength);

    }


}
