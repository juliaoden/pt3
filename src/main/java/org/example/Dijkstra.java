package org.example;

import java.util.*;
import java.lang.*;

class Dijkstra {
    //max number of vertices in graph
    int num_Vertices ;
    // The output array dist[i] will hold
    int[] path_array;
    // spt (shortest path set) contains vertices that have shortest path
    Boolean[] sptSet;
    // row = parent node, column = destination node, output[row][col] = total weight to get there
    int[][] output;

    void printMinpath(int[] path_array)   {
        System.out.println("Vertex \t Minimum Distance from Source");
        for (int i = 0; i < num_Vertices; i++)
            System.out.println(i + " \t\t\t " + path_array[i]);
    }

    public void initialize(){
        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < num_Vertices; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
    }

    int minDistance(int[] path_array, Boolean[] sptSet)   {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < num_Vertices; v++)
            if (!sptSet[v] && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }
        return min_index;
    }

    void algo_dijkstra(ArrayList<Integer>[] graph, int src_node)  {
        // Path between vertex and itself is always 0 
        path_array[src_node] = 0;
        // now find the shortest path for each vertices
        for (int count = 0; count < num_Vertices - 1; count++) {
            // call minDistance method to find the vertex with min distance
            int shortestVertex = minDistance(path_array, sptSet);
            // the current vertex shortestVertex is marked as visited
            sptSet[shortestVertex] = true;

            for (int i = 0; i < num_Vertices; i++)
                if (
                    //vertex isn´t visited
                    !sptSet[i]
                    // edge to vertex is existing
                    && graph[shortestVertex].get(i) != 0
                    // TODO: Was wird hier abgefragt
                    && path_array[shortestVertex] != Integer.MAX_VALUE
                    // new edge is less than current edge
                    && path_array[shortestVertex] + graph[shortestVertex].get(i) < path_array[i]) {
                    // update path_array with new total weight for node
                    path_array[i] = path_array[shortestVertex] + graph[shortestVertex].get(i);
                    // add to output array
                    // shortestVertex = parent node, i = destination, path_array[i] contains needed value
                    output[shortestVertex][i] = path_array[i];
                }

            }
        // print the path array
        printMinpath(path_array);
    }


    public int[][] start(ArrayList<Integer>[] graph, int numModes, int startNode) {
        num_Vertices = numModes;
        path_array = new int[num_Vertices];
        sptSet = new Boolean[num_Vertices];
        output = new int[num_Vertices][num_Vertices];
        initialize();
        algo_dijkstra(graph, startNode);

        return output;
    }
}