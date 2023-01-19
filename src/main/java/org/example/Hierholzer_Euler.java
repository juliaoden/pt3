package org.example;

import java.util.*;

class Hierholzer_Euler {

    static int[][] output;
    static ArrayList<Integer>[] adjacencyMatrix;
    static int numNodes;
    static List<Integer> tour = new ArrayList<>();
    static Set<Integer> oddDegreeVertices = new HashSet<>();

    static void findTour() {
        int start;
        if (oddDegreeVertices.size() == 0) {
            // Wähle beliebigen Knoten als Start
            start = 0;
        } else {
            // Wähle Knoten mit ungeradem Grad als Start
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
            System.out.println("Error: Der Graph hat mehr als einen Knoten mit ungeradem Grad.");
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