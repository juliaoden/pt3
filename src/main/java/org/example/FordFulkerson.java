package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    // number of nodes
    static int numNodes;
    // graph
    static ArrayList<Integer>[] graph;
    // output graph / residual graph
    static int[][] output;
    // array to store the path from source to destination
    static int[] parent;
    // maximal flow
    static int max_flow;

    public void findMaxFlow(int source, int sink) {
        // while there is a possible path the algorithm can take
        while (isPathExist_BFS(output, source, sink, parent)) {
            int flow_capacity = Integer.MAX_VALUE;

            // find the capacity which can be passed through the path
            int destNode = sink;
            while (destNode != source) {
                int curNode = parent[destNode];
                flow_capacity = Math.min(flow_capacity, output[curNode][destNode]);
                destNode = curNode;
            }

            // update the residual graph
            // reduce the capacity on forward edge by flow_capacity and add the capacity on back edge by flow_capacity
            destNode = sink;
            while (destNode != source) {
                int curNode = parent[destNode];
                output[curNode][destNode] -= flow_capacity;
                output[destNode][curNode] += flow_capacity;
                destNode = curNode;
            }

            // add flow_capacity to max value
            max_flow += flow_capacity;
        }
    }

    // BFS = Breath First Search (Breitensuche)
    public boolean isPathExist_BFS(int[][] residualGraph, int src, int destination, int[] parent) {
        boolean pathFound = false;

        // visited array to keep track of visited nodes
        boolean[] visited = new boolean[numNodes];

        // create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // insert the source node and mark it visited
        queue.add(src);
        parent[src] = -1;
        visited[src] = true;

        while (queue.isEmpty() == false) {
            // get current node
            int curNode = queue.poll();

            // visit all the adjacent nodes
            for (int i = 0; i < numNodes; i++) {
                // if node is not already visited and if weight is > 0
                if (visited[i] == false && residualGraph[curNode][i] > 0) {
                    queue.add(i);
                    parent[i] = curNode;
                    visited[i] = true;
                }
            }
        }

        // check if destination is reached during BFS
        pathFound = visited[destination];
        return pathFound;
    }

    public static void initialize(){
        output = new int[numNodes][numNodes];
        // initialize residual graph same as original graph
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                output[i][j] = graph[i].get(j);
            }
        }

         parent= new int[numNodes];
        // initialize the max flow
         max_flow= 0;
    }


    public int[][] start(ArrayList<Integer>[] graphArray, int numNodes, int source, int destination) {
        this.numNodes = numNodes;
        graph = graphArray;
        initialize();
        findMaxFlow(source, destination);
        System.out.println("Max Flow is: " + max_flow);
        return output;
    }
}