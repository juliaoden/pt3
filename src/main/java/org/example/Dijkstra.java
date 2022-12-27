package org.example;

import java.util.*;

public class Dijkstra {

    static int n = 4;

    static ArrayList<Integer> visitedNodes = new ArrayList();

    static ArrayList<Integer>[] graphArray = new ArrayList[n];
    static Boolean[] visited = new Boolean[graphArray.length];

    public void setMaxInt(ArrayList<Integer>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i]);
            for (int j = 0; j < graph.length - 1; j++) {
                if (graph[i].get(j) == 0) {
                    graph[i].set(j, Integer.MAX_VALUE);
                }
            }
        }
    }

    public void update(ArrayList<Integer>[] graph, int currentNode, Boolean[] visited) {
        int nextNode;
        visited[currentNode] = true;
        for (int i = 0; i < graph.length; i++) {
            System.out.println("We are looking at row " + currentNode);
            //visitedNodes.add(currentNode);
            nextNode = getNextVertex(graph, visited, currentNode); // a: nextNode = d// d: nextNode = c
            System.out.println("Next Node is: " + nextNode);
            if(nextNode != -1) visited[nextNode] = true; // visited = [true, flase, false, true] // visited = [true, true, false, true]
            //int nextNode2 = getNextVertex(graph, visited, nextNode); // nächster Knoten für d = c // nächster Knoten für c = b oder so
            visitedNodes.add(currentNode);
            currentNode = nextNode;
        }

        //return visitedNodes;

    }


    public int getNextVertex(ArrayList<Integer>[] graph, Boolean[] visited, int currentNode) {
        int curBestWeight, nextWeight, x, bestNode = currentNode, bestWeight;
        for (int j = 0; j < graph.length - 1; j++) {
            // i = Zeile, j= Spalte
            curBestWeight = graph[currentNode].get(j);
            nextWeight = graph[currentNode].get(j + 1);
            System.out.printf("Current node(curBestWeight): %d, Next Node(nextWeight): %d, Value of visited: %b%n", curBestWeight, nextWeight, visited[j]);
            //System.out.println((nextWeight < curBestWeight) && (nextWeight != 0) && !visited[currentNode]);
            // Prüfen, ob nächstes Gewicht besser ist -> MUSS NOCH ÜBERARBEITET WERDEN WG FEHLENDEN EINTRÄGEN
            if ((nextWeight < curBestWeight) && (nextWeight != 0) && !visited[j]) {
                //curBestWeight = nextWeight;
                bestNode = j + 1;
                //System.out.println(x + "/" + y);
            } else {
                System.out.println("Vergleich fehlgeschlagen");
            }
        }

        if(bestNode == currentNode){
            return java.util.Arrays.asList(visited).indexOf(Boolean.FALSE);  // da wo halt visited noch false ist
        } else{
            return bestNode;
        }

    }


    public static void main(String[] args) {

        // initializing
        for (int i = 0; i < n; i++) {
            graphArray[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        //al[0].add(1);
        graphArray[0].add(0);
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
        graphArray[3].add(0);


        Dijkstra d = new Dijkstra();
        d.setMaxInt(graphArray);
        d.update(graphArray, 0, visited);

        for (int i = 0; i < visitedNodes.size(); i++) {
            System.out.print(visitedNodes.get(i));
        }

    }


}
