package org.example;

import java.util.*;

public class Dijkstra {

    public void initializeSingleSource(ArrayList<Integer>[] graph, int start) {
        int distance;
        for(int i = 0; i< graph.length; i++){
            int a = 1;
        }
    }

    public void start(ArrayList<Integer>[] graph, int weight, int start) {
        initializeSingleSource(graph, start);
        // S = leere Menge
        // Q = G.V
        // while ()
    }

    public void update(int precursor, int successor, int weight) {

    }

    public void getNextVertex(ArrayList<Integer>[] graph, Boolean[] visited) {
        int curBestWeight, nextWeight, x, y;
        for (int i = 0; i < graph.length; i++) {
            System.out.println(graph[i]);
            for (int j = 0; j < graph.length - 1; j++) {
                if(graph[i].get(j) == 0){
                    graph[i].set(j, Integer.MAX_VALUE);
                }

                // i = Zeile, j= Spalte
                curBestWeight = graph[i].get(j);
                nextWeight = graph[i].get(j + 1);
                System.out.printf("Current node: %d, Next Node: %d%n", curBestWeight, nextWeight);
                // Prüfen, ob nächstes Gewicht besser ist -> MUSS NOCH ÜBERARBEITET WERDEN WG FEHLENDEN EINTRÄGEN
                if ((nextWeight < curBestWeight) && (nextWeight != 0) && !visited[i]) {
                    x = i;
                    y = j + 1;
                    System.out.println(x + "/" + y);
                }

                /*
            }*/
            }
        }


    }


    public static void main(String[] args) {
        Boolean[] visited = new Boolean[]{false, false, false, false};

        int n = 4;

        ArrayList<Integer>[] graphArray = new ArrayList[n];

        // initializing
        for (int i = 0; i < n; i++) {
            graphArray[i] = new ArrayList<Integer>();
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

        d.getNextVertex(graphArray, visited);




        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < graphArray[i].size(); j++) {
                System.out.print(graphArray[i].get(j) + " ");
            }
            System.out.println();
        }*/
    }


}
