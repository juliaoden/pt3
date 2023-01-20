package org.example;

import java.util.ArrayList;

public class BipartiteMatching {
    // output matrix
    static int[][] output;
    // number of jobs
    static int jobs;
    // number of applicants
    static int applicants;
    // graph matrix
    static ArrayList<Integer>[] adjMatrix;
    // array for job names
    static String[] job;
    // array for name names
    static String[] namen;


    public static void maxMatching() {
        // array to track which job is assigned to which applicant
        int assign[] = new int[jobs];

        // initially set all jobs are available
        for (int i = 0; i < jobs; i++)
            assign[i] = -1;

        for (int applicant = 0; applicant < applicants; applicant++) {
            // for each applicant, all jobs will be not visited initially
            boolean visited[] = new boolean[jobs];

            // check if applicant can get a job n
            bipartiteMatch(applicant, visited, assign);
        }

        // update output matrix
        for(int i = 0; i < namen.length; i++){
            for(int j= 0; j< job.length; j++){
                if(i == assign[j])
                    output[i][j] = 1;
            }
        }

    }

    static boolean bipartiteMatch(int applicant, boolean visited[], int assign[]) {
        // check each job for the applicant
        for (int job = 0; job < jobs; job++) {
            // check if applicant can do this job and applicant has not considered for this job before
            if (adjMatrix[applicant].get(job) == 1 && !visited[job]) {
                // mark as job is visited, means applicant is considered for this job
                visited[job] = true;
                int assignedApplicant = assign[job];
                // check if job was not assigned earlier or if job was assigned earlier to some other applicant
                // make recursive call for other applicant to check if some other job can be assigned
                // so that this job can be assigned to current candidate
                if (assignedApplicant < 0 || bipartiteMatch(assignedApplicant, visited, assign)) {
                    // assign job to applicant
                    assign[job] = applicant;
                    return true;
                }
            }
        }
        return false;
    }


    public static int[][] start(int numNodes, String[] namesArray, String[] jobsArray, ArrayList<Integer>[] matrix) {
        applicants = numNodes;
        jobs = numNodes;
        adjMatrix = matrix;
        namen = namesArray;
        job = jobsArray;
        output = new int[namesArray.length][jobsArray.length];

        maxMatching();
        return  output;
    }
}

