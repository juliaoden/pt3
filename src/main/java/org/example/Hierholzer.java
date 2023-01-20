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
    static Set<Integer> oddDegreeNodes = new HashSet<>();

    static void findTour() {
        int start;
        if (oddDegreeNodes.size() == 0) {
            // choose random node as start
            start = 0;
        } else {
            // choose node with even degree as start
            start = oddDegreeNodes.iterator().next();
        }
        tour.add(start);
        int current = start;
        while (true) {
            // new subTour
            List<Integer> subTour = new ArrayList<>();
            current = start;
            do {
                for (int i = 0; i < numNodes; i++) {
                    // find a connected node
                    if (adjacencyMatrix[current].get(i) == 1) {
                        adjacencyMatrix[current].set(i, 0);
                        adjacencyMatrix[i].set(current, 0);
                        current = i;
                        break;
                    }
                }
                // "go to node"
                subTour.add(current);
            // as long as we are not back at the start
            } while (current != start);

            // add subTour to total tour
            tour.addAll(start+1, subTour);
            // find next connected node to start
            start = findNextNode();

            // no more nodes can be visited
            if (start == 0) {
                break;
            }
        }

        // update output array
        for(int i = 0; i < tour.size()-1; i++){
            output[tour.get(i)][tour.get(i+1)] = 1;
        }

    }

    public static boolean checkEvenGraph(){
        for (int i = 0; i < numNodes; i++) {
            int degree = 0;
            for (int j = 0; j < numNodes; j++) {
                // count number of connected nodes for each node
                if (adjacencyMatrix[i].get(j) == 1) {
                    degree++;
                }
            }
            // if number of connected nodes is odd add the node to oddDegreeNodes
            if (degree % 2 == 1) {
                oddDegreeNodes.add(i);
            }
        }
        // if there are odd vertices the graph isn´t valid
        if (oddDegreeNodes.size() > 0) {
            System.out.println("Error: Graph has more than one node with odd degree, algorithm can´t proceed.");
            return false;
        }
        return true;
    }

    public static int findNextNode() {
        int nextNode = 0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (adjacencyMatrix[i].get(j) == 1) {
                    return i;
                }
            }
        }
        return nextNode;
    }

    public int[][] start(ArrayList<Integer>[] matrix, int numNodes){
        this.numNodes = numNodes;
        adjacencyMatrix = matrix;
        output = new int[numNodes][numNodes];
        if(checkEvenGraph())  findTour();
        System.out.println(tour);
        return output;
    }
}