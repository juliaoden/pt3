package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static ArrayList<Integer>[] getArrayListMatrix(Scanner input, int numberNodes) {
        // Adjacency matrix
        System.out.println("Geben Sie Adjazenzmatrix ein");
        int[] array = new int[numberNodes];
        ArrayList<Integer>[] graphArray = new ArrayList[numberNodes];
        for (int i = 0; i < numberNodes; i++) {
            graphArray[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberNodes; i++) {
            for (int j = 0; j < numberNodes; j++) {
                array[j] = input.nextInt();
            }
            for (int j = 0; j < array.length; j++) {
                graphArray[i].add(array[j]);
            }
        }
        return graphArray;
    }

    public static int[][] getMatrix(Scanner input, int numberNodes) {
        // Adjacency matrix
        System.out.println("Geben Sie Adjazenzmatrix ein");
        int[][] graph = new int[numberNodes][numberNodes];
        for (int i = 0; i < numberNodes; i++) {
            for (int j = 0; j < numberNodes; j++) {
                graph[i][j] = input.nextInt();
            }

        }
        return graph;
    }

    public static String[] getStringArray(Scanner input, int numberNodes, boolean isNamesArray) {
        String[] names = new String[numberNodes];
        if(isNamesArray == true) names[0] = input.nextLine();
        for (int i = 0; i < names.length; i++) {
            names[i] = input.nextLine();
        }
        System.out.println(Arrays.toString(names));
        return names;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Welches Problem der Graphschaft Schilda wollen Sie angehen? \n"
                + "1: Straßen müssen her \n"
                + "2: Wasserversorgung \n"
                + "3: Stromversorgung \n"
                + "4: Historische Funde \n"
                + "5: Die Festhochzeit – das Verteilen der Einladungen \n"
                + "6: Wohin nur mit den Gästen? \n"
                + "7: Es gibt viel zu tun! Wer macht‘s? \n"
                + "Geben Sie die Nummer des gewünschten Problems ein! \n"
        );
        int problem = input.nextInt();

        System.out.println("Wie viele Knoten hat der Graph?");
        int numNodes = input.nextInt();


        switch (problem) {
            // Problem: Straßen müssen her
            case 1:
                break;
            // Problem: Wasserversorgung
            case 2:
                break;
            // Problem: Stromversorgung
            case 3:
                break;
            // Problem: Historische Funde
            case 4:
                ArrayList<Integer>[] graphArray = getArrayListMatrix(input, numNodes);
                Dijkstra d = new Dijkstra();
                d.start(d, graphArray, numNodes);
                break;
            // Problem: Die Festhochzeit – das Verteilen der Einladungen
            case 5:
                break;
            // Problem: Wohin nur mit den Gästen?
            case 6:
                break;
            // Problem: Es gibt viel zu tun! Wer macht‘s?
            case 7:
                System.out.println("Geben Sie die Namen der Personen ein");
                String[] names = getStringArray(input, numNodes,true);
                System.out.println("Geben Sie die Jobbezeichnungen ein");
                String[] jobs = getStringArray(input, numNodes, false);
                int[][] matrix = getMatrix(input, numNodes);
                MaximumBipartiteMatching m = new MaximumBipartiteMatching();
                m.start(numNodes, names, jobs, matrix);
                break;
        }

    }
}