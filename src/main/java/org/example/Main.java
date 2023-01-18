package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

   /* public static ArrayList<Integer>[] getArrayListMatrix(Scanner input, int numberNodes) {
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

    */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Welches Problem der Grafschaft Schilda wollen Sie angehen? \n"
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

        System.out.println("Geben Sie die Anzahl der Knoten ein:");
        int numNodes = input.nextInt();


        System.out.println("Geben Sie den Namen der Datei ein:");

        String filePath = input.next();
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get node names
        String strNew = lines.get(0).replace("  ", "");
        ArrayList<String> nodeNames = new ArrayList<>(Arrays.asList(strNew.split(" ")));

        ArrayList<Integer>[] matrix = new ArrayList[numNodes];

        // Initialize matrix
        for (int i = 0; i < numNodes; i++) {
            matrix[i] = new ArrayList<>();
        }

        for (int i = 0; i < numNodes; i++) {
            String target = nodeNames.get(i) + " ";
            // Remove node name from string
            String numberString = lines.get(i + 1).replace(target, "");
            // Convert single lines from input to ArrayList
            ArrayList<String> line = new ArrayList<>(Arrays.asList(numberString.split(" ")));
            // Convert ArrayList to Integer array
            int[] arr = line.stream().mapToInt(j -> Integer.parseInt(j)).toArray();
            //
            for (int k = 0; k < numNodes; k++) {
                matrix[i].add(arr[k]);
            }

        }

        // TODO: Von Nutzer abfragen
        int source = 0;
        int destination = 0;
        int startNode = 0;

        switch (problem) {
            // Problem: Straßen müssen her
            case 1:
                Dijkstra2 d2 = new Dijkstra2();
                d2.start(matrix, numNodes, 0);
                break;
            // Problem: Wasserversorgung
            case 2:
                source = 0;
                destination= 8;
                MaxFlow_Ford_Fulkerson ff1 = new MaxFlow_Ford_Fulkerson();
                ff1.start(matrix, numNodes, source, destination);
                break;
            // Problem: Stromversorgung
            case 3:
                Prim p =  new Prim();
                p.start(matrix, numNodes, 0);
                break;
            // Problem: Historische Funde
            case 4:
                Dijkstra2 d = new Dijkstra2();
                d.start(matrix, numNodes, 0);
                break;
            // Problem: Die Festhochzeit – das Verteilen der Einladungen
            case 5:
                Hierholzer_Euler h = new Hierholzer_Euler();
               List<Integer> result =  h.findTour(matrix, numNodes);
                System.out.println(result);
                break;
            // Problem: Wohin nur mit den Gästen?
            case 6:
                source=0;
                destination = 5;
                MaxFlow_Ford_Fulkerson ff2 = new MaxFlow_Ford_Fulkerson();
                ff2.start(matrix, numNodes, source, destination);
                break;
            // Problem: Es gibt viel zu tun! Wer macht's?
            case 7:
                System.out.println("Geben Sie die Namen der Personen ein");
                String[] names = nodeNames.toArray(new String[0]);
                System.out.println("Geben Sie die Jobbezeichnungen ein");
                String[] jobs = nodeNames.toArray(new String[1]);
                MaximumBipartiteMatching m = new MaximumBipartiteMatching();
                m.start(numNodes, names, jobs, matrix);
                break;
        }

    }
}