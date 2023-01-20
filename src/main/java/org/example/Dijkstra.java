package org.example;

import java.util.*;
import java.lang.*;

class Dijkstra {
    // max number of nodes in graph
    int numNodes ;
    //  array which holds weights
    int[] pathArray;
    // array for visited nodes, which have the shortest path
    Boolean[] visitedArray;
    // row = parent node, column = destination node, output[row][col] = total weight to get there
    int[][] output;

    public void initialize(){
        // initially all the distances are INFINITE and no nodes have the shortest path
        for (int i = 0; i < numNodes; i++) {
            pathArray[i] = Integer.MAX_VALUE;
            visitedArray[i] = false;
        }
    }

    int minDistance()   {
        // initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int i = 0; i < numNodes; i++)
            // if node isn´t visited and if it´s weight is better
            if (!visitedArray[i] && pathArray[i] <= min) {
                min = pathArray[i];
                min_index = i;
            }
        return min_index;
    }

    void dijkstra(ArrayList<Integer>[] graph, int src_node)  {
        // path between node and itself is always 0
        pathArray[src_node] = 0;
        // find the shortest path for each node
        for (int i = 0; i < numNodes - 1; i++) {
            // find the node with minimum distance
            int shortestNode = minDistance();
            // the current node is marked as visited
            visitedArray[shortestNode] = true;

            for (int j = 0; j < numNodes; j++)
                if (
                    // node isn´t visited
                    !visitedArray[j]
                    // edge to node is existing
                    && graph[shortestNode].get(j) != 0
                    // connection is existing
                    && pathArray[shortestNode] != Integer.MAX_VALUE
                    // new edge is less than current edge
                    && pathArray[shortestNode] + graph[shortestNode].get(j) < pathArray[j]) {
                    // update pathArray with new total weight for node
                    pathArray[j] = pathArray[shortestNode] + graph[shortestNode].get(j);
                    // add to output array
                    // shortestNode = parent node, j = destination, path_array[j] contains needed value
                    output[shortestNode][j] = pathArray[j];
                }

            }
    }


    public int[][] start(ArrayList<Integer>[] graph, int numModes, int startNode) {
        numNodes = numModes;
        pathArray = new int[numNodes];
        visitedArray = new Boolean[numNodes];
        output = new int[numNodes][numNodes];
        initialize();
        dijkstra(graph, startNode);

        return output;
    }
}