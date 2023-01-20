package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

// TODO: println() entweder in deutsch oder englisch

public class Main {
    // scanner for getting user input
    static Scanner input = new Scanner(System.in);
    // number of problem
    static int problem;
    // number of nodes
    static  int numNodes;
    // path of file
    static  String filePath;
    // rows of inut file
    static List<String> inputRows;
    // first line of nodes
    static ArrayList<String> nodeNamesHorizontal;
    // node names from first column
    static ArrayList<String> nodeNamesVertical= new ArrayList<>();
    // matrix for algorithm
    static ArrayList<Integer>[] matrix;
    // output graph
    static int[][] output;

    public static void main(String[] args) {
        // get the problem, which should be solved
        System.out.print("Which problem should be solved? \n"
                + "1: Straßen müssen her \n"
                + "2: Wasserversorgung \n"
                + "3: Stromversorgung \n"
                + "4: Historische Funde \n"
                + "5: Die Festhochzeit – das Verteilen der Einladungen \n"
                + "6: Wohin nur mit den Gästen? \n"
                + "7: Es gibt viel zu tun! Wer macht‘s? \n"
                + "Enter number of the problem! \n"
        );
        problem = input.nextInt();

        // get number of nodes
        System.out.println("Enter number of nodes:");
        numNodes = input.nextInt();

        // get path name
        System.out.println("Enter path of file (needs to be stored in the same folder as project):");
        filePath = input.next();

        // get rows from input
        try {
            inputRows = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // get node names
        String strNew = inputRows.get(0).replace("  ", "");
        nodeNamesHorizontal = new ArrayList<>(Arrays.asList(strNew.split(" ")));

        matrix = new ArrayList[numNodes];
        // initialize matrix
        for (int i = 0; i < numNodes; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (int i = 0; i < numNodes; i++) {
            ArrayList<String> line = new ArrayList<>(Arrays.asList(inputRows.get(i+1).split(" ")));
            // get node name from begin of each line and push to vertical node name ArrayList
            nodeNamesVertical.add(line.remove(0));
            // push ArrayList<String> to ArrayList<Integer>[]
            int[] arr = line.stream().mapToInt(j -> Integer.parseInt(j)).toArray();
            for (int k = 0; k < numNodes; k++) {
                matrix[i].add(arr[k]);
            }



        }

        switch (problem) {
            // Problem: Straßen müssen her
            case 1:
               startDijkstra();
                break;
            // Problem: Wasserversorgung
            case 2:
                startFordFulkerson();
                break;
            // Problem: Stromversorgung
            case 3:
                startPrim();
                break;
            // Problem: Historische Funde
            case 4:
                startDijkstra();
                break;
            // Problem: Die Festhochzeit – das Verteilen der Einladungen
            case 5:
                startEuler();
                break;
            // Problem: Wohin nur mit den Gästen?
            case 6:
                startFordFulkerson();
                break;
            // Problem: Es gibt viel zu tun! Wer macht's?
            case 7:
                startBipartiteMatching();
                break;
        }

        printOutput();

    }

    // methods for starting the algorithms
    public static void startFordFulkerson(){
        System.out.println("Enter source node (node indizes start at 0)");
        int source = input.nextInt();
        System.out.println("Enter destination node (node indizes start at 0)");
        int destination= input.nextInt();
        FordFulkerson ff1 = new FordFulkerson();
        ff1.start(matrix, numNodes, source, destination);
    }

    public static void startDijkstra(){
        System.out.println("Enter start node (node indizes start at 0)");
        int startNode = input.nextInt();
        Dijkstra d = new Dijkstra();
        output = d.start(matrix, numNodes, startNode);
    }

    public static void startBipartiteMatching(){
        String[] names = nodeNamesHorizontal.toArray(new String[0]);
        String[] jobs = nodeNamesVertical.toArray(new String[1]);
        MaximumBipartiteMatching m = new MaximumBipartiteMatching();
        output = m.start(numNodes, names, jobs, matrix);
    }

    public static void startPrim(){
        Prim p =  new Prim();
        output = p.start(matrix, numNodes, 0, nodeNamesHorizontal);
    }

    public static void startEuler(){
        Hierholzer h = new Hierholzer();
        output =  h.start(matrix, numNodes);
    }

    public static void printOutput(){
        System.out.print("Output Matrix:\n  ");
        // printing first line with node names
        for(int i = 0; i < nodeNamesHorizontal.size(); i++){
            System.out.print(nodeNamesHorizontal.get(i) + " ");
        }

        System.out.println();

        // printing the rest of the matrix
        for(int i = 0; i < output.length; i++){
            // printing the node name and one space
            System.out.print(nodeNamesVertical.get(i) + " ");
            for(int j=0; j< output.length; j++){
                // printing the values of the matrix with a space
                System.out.print(output[i][j] + " ");
            }
            System.out.println();
        }
    }
}