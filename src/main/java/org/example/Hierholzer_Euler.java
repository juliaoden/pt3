package org.example;

import java.util.*;

class Hierholzer_Euler {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                /* {0,1,1,0,0,0,0,1,0,0},
                 {1,0,5,2,4,0,0,0,0,0},
                 {3,5,0,0,4,0,3,3,0,0},
                 {0,2,0,0,3,3,0,0,0,0},
                 {0,4,4,3,0,2,4,0,0,0},
                 {0,0,0,3,2,0,3,0,0,4},
                 {0,0,3,0,4,3,0,2,0,3},
                 {5,0,3,0,0,0,2,0,3,4},
                 {0,0,0,0,0,0,0,3,0,2},
                 {0,0,0,0,0,4,3,4,2,0}*/
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0}
        };

        System.out.println(findTour(matrix));

    }

    static List<Integer> findTour(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        List<Integer> tour = new ArrayList<>();
        Set<Integer> oddDegreeVertices = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int degree = 0;
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    degree++;
                }
            }
            if (degree % 2 == 1) {
                oddDegreeVertices.add(i);
            }
        }
        if (oddDegreeVertices.size() > 0) {
            // Graph ist ungültig
            System.out.println("Error: Der Graph hat mehr als einen Knoten mit ungeradem Grad."); // TODO
            return tour;
        }
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
                for (int i = 0; i < n; i++) {
                    if (adjacencyMatrix[current][i] == 1) {
                        adjacencyMatrix[current][i] = 0;
                        adjacencyMatrix[i][current] = 0;
                        current = i;
                        break;
                    }
                }
                subTour.add(current);
            } while (current != start);

            tour.addAll(start+1, subTour);
            start = findNextVertex(adjacencyMatrix, n);

            // TODO optimieren mit nextVertex
            boolean tourIsEuler = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        tourIsEuler = false;
                        break;
                    }
                }
            }
            if (tourIsEuler) {
                break;
            }
        }
        return tour;
    }

    public static int findNextVertex(int[][] adjacencyMatrix, int n) {
        int nextVertex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    return i;
                }

            }

        }

        return nextVertex;
    }
}

