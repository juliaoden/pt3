package org.example;

import java.util.*;
import java.lang.*;

class Dijkstra {

    int num_Vertices ;  //max number of vertices in graph
    // find a vertex with minimum distance
    int minDistance(int path_array[], Boolean sptSet[])   {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < num_Vertices; v++)
            if (sptSet[v] == false && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }

        return min_index;
    }

    // print the array of distances (path_array)
    void printMinpath(int path_array[])   {
        System.out.println("Vertex \t Minimum Distance from Source");
        for (int i = 0; i < num_Vertices; i++)
            System.out.println(i + " \t\t\t " + path_array[i]);
    }

    // Implementation of Dijkstra's algorithm for graph (adjacency matrix)
    void algo_dijkstra(ArrayList<Integer>[] graph, int src_node)  {
        int path_array[] = new int[num_Vertices]; // The output array. dist[i] will hold 
        // the shortest distance from src to i 

        // spt (shortest path set) contains vertices that have shortest path 
        Boolean sptSet[] = new Boolean[num_Vertices];

        // Initially all the distances are INFINITE and stpSet[] is set to false 
        for (int i = 0; i < num_Vertices; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Path between vertex and itself is always 0 
        path_array[src_node] = 0;
        // now find shortest path for all vertices
        for (int count = 0; count < num_Vertices - 1; count++) {
            // call minDistance method to find the vertex with min distance
            int u = minDistance(path_array, sptSet);
            // the current vertex u is processed
            sptSet[u] = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < num_Vertices; v++)

                // if vertex v not in sptset then update it  
                if (!sptSet[v] && graph[u].get(v) != 0 && path_array[u] !=
                        Integer.MAX_VALUE && path_array[u]
                        + graph[u].get(v) < path_array[v])
                    path_array[v] = path_array[u] + graph[u].get(v);
        }

        // print the path array 
        printMinpath(path_array);
    }


    public void start(ArrayList<Integer>[] graph, int numModes, int startNode)
    {
        //example graph is given below
        /*ArrayList<Integer>[] graph = new ArrayList[num_Vertices];
        for (int i = 0; i < num_Vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        ArrayList<Integer> a = new ArrayList<Integer>() {{
            add(0);
            add(0);
            add(0);
            add(0);
            add(5);
            add(1);
            add(5);
            add(0);
        }};
        ArrayList<Integer> b = new ArrayList<Integer>() {{
            add(0);
            add(0);
            add(1);
            add(5);
            add(0);
            add(0);
            add(4);
            add(0);
        }};
        ArrayList<Integer> c = new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(0);
            add(4);
            add(0);
            add(0);
            add(0);
            add(3);
        }};
        ArrayList<Integer> d = new ArrayList<Integer>() {{
            add(0);
            add(5);
            add(4);
            add(0);
            add(0);
            add(0);
            add(0);
            add(0);
        }};
        ArrayList<Integer> e = new ArrayList<Integer>() {{
            add(5);
            add(0);
            add(0);
            add(0);
            add(0);
            add(2);
            add(0);
            add(0);
        }};
        ArrayList<Integer> f = new ArrayList<Integer>() {{
            add(1);
            add(0);
            add(0);
            add(0);
            add(2);
            add(0);
            add(3);
            add(0);
        }};
        ArrayList<Integer> g = new ArrayList<Integer>() {{
            add(5);
            add(4);
            add(0);
            add(0);
            add(0);
            add(3);
            add(0);
            add(2);
        }};
        ArrayList<Integer> h = new ArrayList<Integer>() {{
            add(0);
            add(0);
            add(3);
            add(0);
            add(0);
            add(2);
            add(2);
            add(0);
        }};

        graph[0] = a;
        graph[1] = b;
        graph[2] = c;
        graph[3] = d;
        graph[4] = e;
        graph[5] = f;
        graph[6] = g;
        graph[7] = h;

        Dijkstra2 dijkstra2 = new Dijkstra2();*/
        num_Vertices = numModes;
        algo_dijkstra(graph, startNode);
    }
}