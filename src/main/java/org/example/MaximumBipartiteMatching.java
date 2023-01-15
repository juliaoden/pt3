package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MaximumBipartiteMatching {

    static class Graph {
        HashMap<Integer, Integer> zuordnung = new HashMap<Integer, Integer>();
        int jobs;
        int applicants;
        ArrayList<Integer>[] adjMatrix;

        public Graph(int applicants, int jobs) {
            this.jobs = jobs;
            this.applicants = applicants;
            adjMatrix = new ArrayList[jobs];
        }

       /* public void canDoJob(int applicant, int job) {
            //add edge - means applicant can do this job
            adjMatrix[applicant][job] = 1;
        }*/
    }

    public int[] maxMatching(Graph graph) {
        int applicants = graph.applicants;
        int jobs = graph.jobs;

        int assign[] = new int[jobs];    //an array to track which job is assigned to which applicant
        for (int i = 0; i < jobs; i++)
            assign[i] = -1;    //initially set all jobs are available

        for (int applicant = 0; applicant < applicants; applicant++) {    //for all applicants
            //for each applicant, all jobs will be not visited initially
            boolean visited[] = new boolean[jobs];

            //check if applicant can get a job  n
            bipartiteMatch(graph, applicant, visited, assign, graph.zuordnung);
        }
        return assign;
    }

    boolean bipartiteMatch(Graph graph, int applicant, boolean visited[], int assign[], HashMap x) {
        //check each job for the applicant
        for (int job = 0; job < graph.jobs; job++) {
            //check if applicant can do this job means adjMatrix[applicant][job] == 1
            // and applicant has not considered for this job before, means visited[job]==false
            if (graph.adjMatrix[applicant].get(job) == 1 && !visited[job]) {
                //mark as job is visited, means applicant is considered for this job
                visited[job] = true;
                //now check if job was not assigned earlier - assign it to this applicant
                // OR job was assigned earlier to some other applicant 'X' earlier than
                //make recursive call for applicant 'X' to check if some other job can be assigned
                // so that this job can be assigned to current candidate.
                int assignedApplicant = assign[job];
                if (assignedApplicant < 0 || bipartiteMatch(graph, assignedApplicant, visited, assign, x)) {
                    assign[job] = applicant;    //assign job job to applicant applicant
                    x.put(applicant, job);
                    return true;
                }
            }
        }
        return false;
    }

    public static HashMap map(String[] keys, String[] values, HashMap zugeordnet){
        // zugeordnet = {Person : Job}
        HashMap<String, String> zuordnung = new HashMap<String, String>();
        for(int i = 0; i < keys.length; i++){
            for (int j= 0; j < values.length; j++){
                // System.out.println(j + " | " + zugeordnet.get(i));
                if(j == (Integer) zugeordnet.get(i)){
                    zuordnung.put(keys[i], values[j]);
                }
            }
        }
        return zuordnung;
    }
    public static void start(int numNodes, String[] namesArray, String[] jobsArray, ArrayList<Integer>[] matrix) {


        //Construct Graph with applicants and jobs
        int applicants = numNodes;
        int jobs = numNodes;
        Graph graph = new Graph(applicants, jobs);
        String[] namen = namesArray; //{"Herr Maier", "Frau Müller", "Frau August", "Frau Schmidt", "Herr Kunze", "Herr Hof", "Frau Lustig"};
        String[] job = jobsArray; //{"Straßenbau", "Verkehrsplanung", "Archäologie", "Gesamtkoordination", "Festplanung", "Wasserversorgung", "Wettkampfausrichtung"};
        graph.adjMatrix = matrix;
        /*graph.canDoJob(0, 0);
        graph.canDoJob(0, 1);
        graph.canDoJob(0, 2);
        graph.canDoJob(1, 3);
        graph.canDoJob(1, 4);
        graph.canDoJob(2, 0);
        graph.canDoJob(2, 5);
        graph.canDoJob(3, 0);
        graph.canDoJob(3, 1);
        graph.canDoJob(3, 6);
        graph.canDoJob(4, 2);
        graph.canDoJob(4, 4);
        graph.canDoJob(5, 0);
        graph.canDoJob(5, 3);
        graph.canDoJob(6, 1);
        graph.canDoJob(6, 6);*/

        MaximumBipartiteMatching m = new MaximumBipartiteMatching();
        m.maxMatching(graph);
        HashMap verteilung= map(namen, job, graph.zuordnung);
        System.out.println("Jobverteilung lautet wie folgt: " + verteilung);
    }
}

