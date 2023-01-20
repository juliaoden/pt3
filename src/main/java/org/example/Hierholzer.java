package org.example;

import java.util.*;

class Hierholzer {
    // output matrix
    static int[][] output;
    // graph matrix
    static ArrayList<Integer>[] adjacencyMatrix;
    // number of nodes
    static int numNodes;
    // tour list
    static List<Integer> tour = new ArrayList<>();
    // set to check if graph is valid
    static Set<Integer> oddDegreeVertices = new HashSet<>();

    static void findTour() {
        int start;
        if (oddDegreeVertices.size() == 0) {
            // choose random node as start
            start = 0;
        } else {
            // choose node with even degree as start
            start = oddDegreeVertices.iterator().next();
        }
        tour.add(start);
        int current = start;
        while (true) {
            List<Integer> subTour = new ArrayList<>();
            current = start;
            do {
                for (int i = 0; i < numNodes; i++) {
                    if (adjacencyMatrix[current].get(i) == 1) {
                        adjacencyMatrix[current].set(i, 0);
                        adjacencyMatrix[i].set(current, 0);
                        current = i;
                        break;
                    }
                }
                subTour.add(current);
            } while (current != start);

            tour.addAll(start+1, subTour);
            start = findNextVertex();

            // TODO optimieren mit nextVertex
            boolean tourIsEuler = true;
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    if (adjacencyMatrix[i].get(j) == 1) {
                        tourIsEuler = false;
                        break;
                    }
                }
            }
            if (tourIsEuler) {
                break;
            }
        }

        for(int i = 0; i < tour.size()-1; i++){
            output[tour.get(i)][tour.get(i+1)] = 1;
        }

    }

    public static boolean checkEvenGraph(){
        for (int i = 0; i < numNodes; i++) {
            int degree = 0;
            for (int j = 0; j < numNodes; j++) {
                if (adjacencyMatrix[i].get(j) == 1) {
                    degree++;
                }
            }
            if (degree % 2 == 1) {
                oddDegreeVertices.add(i);
            }
        }
        if (oddDegreeVertices.size() > 0) {
            // Graph ist ungültig
            System.out.println("Error: Graph has more than one node with odd degree, algorithm can´t proceed.");
            return false;
        }
        return true;
    }

    public static int findNextVertex() {
        int nextVertex = 0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (adjacencyMatrix[i].get(j) == 1) {
                    return i;
                }
            }
        }
        return nextVertex;
    }

    public int[][] start(ArrayList<Integer>[] matrix, int numNodes){
        this.numNodes = numNodes;
        adjacencyMatrix = matrix;
        output = new int[numNodes][numNodes];
        if(checkEvenGraph())  findTour();
        return output;
    }
}