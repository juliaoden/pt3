package org.example;

import java.util.*;

public class Dijkstra {

    static int n = 4; // Number of nodes

    static ArrayList<Integer> visitedNodes = new ArrayList<>(); // Serie of nodes

    static  ArrayList<Integer> valueNodes = new ArrayList<>();

    static ArrayList<Integer>[] graphArray = new ArrayList[n]; // Adjacency matrix
    //static Boolean[] visited = new Boolean[graphArray.length]; // Visited Array

    public void initialize(){
        for (int i = 0; i < n; i++) {
            graphArray[i] = new ArrayList<Integer>();
        }
        /*for (int i = 0; i < n; i++) {
            visited[i] = false;
        }*/
        for (int i = 0; i < n; i++) {
            valueNodes.set(i, Integer.MAX_VALUE);
        }
    }

    public void setMaxInt(ArrayList<Integer>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length - 1; j++) {
                if (graph[i].get(j) == 0) {
                    graph[i].set(j, Integer.MAX_VALUE);
                }
            }
        }
    }

    public void dijkstra(int startNode) {
        int startgewicht = 10; // TODO!!
        int nextNode;
        valueNodes.set(startNode, startgewicht);
        while(true){
            nextNode = getNextVertex(startNode);


        }
    }


    public int getNextVertex(int curNode) {
        /*
        int curWeight, nextWeight, x, bestNode = currentNode;
        for (int j = 0; j < graph.length - 1; j++) {
            curWeight = graph[currentNode].get(j); // current weight
            nextWeight = graph[currentNode].get(j + 1); // next weight
            // Check if next weight is less than current weight and if it isn´t already visited
            if ((nextWeight < curWeight) && (nextWeight != 0) && !visited[j]) {
                // best node is the next node
                bestNode = j + 1;
            }
        }

        // the current node is the penultimate node
        if(bestNode == currentNode){
            // find the node which isn´t already visited
            // TODO: Was passiert, wenn letzter nicht besuchter Knoten keine Verbindung zum derzeitigen Knoten hat?
            return java.util.Arrays.asList(visited).indexOf(Boolean.FALSE);
        } else{
            return bestNode;
        }*/
        int nextNode, edgeWeight; //nextNode = e, edgeWeight = 10
        for(int i= 0; i< graphArray.length; i++){
            nextNode = i;
            for( int k = 0; k < graphArray.length; k++){
                edgeWeight = graphArray[nextNode].get(k);
                if(valueNodes.get(curNode)+edgeWeight <valueNodes.get(nextNode) ){
                    valueNodes.set(nextNode, valueNodes.get(curNode)+edgeWeight);
                }

            }


        }
    return 0;
    }


    public void start(ArrayList<Integer>[] matrix, int numNodes) {

        // initializing
        initialize();

        // TODO: Vom Nutzenden eingeben lassen
        /*graphArray[0].add(0);
        graphArray[0].add(3);
        graphArray[0].add(2);
        graphArray[0].add(1);
        graphArray[1].add(3);
        graphArray[1].add(0);
        graphArray[1].add(4);
        graphArray[1].add(0);
        graphArray[2].add(2);
        graphArray[2].add(4);
        graphArray[2].add(0);
        graphArray[2].add(7);
        graphArray[3].add(1);
        graphArray[3].add(0);
        graphArray[3].add(7);
        graphArray[3].add(0);*/


        // Replace all zeros with MAX_INT
        setMaxInt(graphArray);
        // Start Dijkstra Algorithm
        dijkstra(0);

        System.out.print("Reihenfolge der Häuser ist ");
        for (int i = 0; i < visitedNodes.size(); i++) {
            System.out.print(visitedNodes.get(i));
        }

    }


}