package org.example;

import java.util.*;

// TODO: Hier kommt der Euler rein am Ende, weil File schon auf Github lol
// TODO: Mit Sprick abklären, weil hier keine Eulertour möglich aber Eulertour gefordert
public class Hierholzer_Euler
{
    public static void main(String args[])
    {
        List< List<Integer> > adj = new ArrayList<>();

        // Build the Graph
        adj.add(new ArrayList<Integer>());
        adj.get(0).add(1);
        adj.get(0).add(2);


        adj.add(new ArrayList<Integer>());
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(1).add(4);


        adj.add(new ArrayList<Integer>());
        adj.get(2).add(0);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(2).add(4);


        adj.add(new ArrayList<Integer>());
        adj.get(3).add(1);
        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(3).add(5);


        adj.add(new ArrayList<Integer>());
        adj.get(4).add(1);
        adj.get(4).add(2);
        adj.get(4).add(6);
        adj.get(4).add(3);

        adj.add(new ArrayList<Integer>());
        adj.get(5).add(3);
        adj.get(5).add(6);

        adj.add(new ArrayList<Integer>());
        adj.get(6).add(5);
        adj.get(6).add(4);




        System.out.println("The Eulerian Circuit for the Graph is : ");

        printEulerianCircuit(adj);


    }

    static void printEulerianCircuit(List< List<Integer> > adj)
    {
        // adj represents the adjacency list of
        // the directed graph
        // edge represents the number of edges emerging from a vertex

        Map<Integer,Integer> edges=new HashMap<Integer,Integer>();

        for (int i=0; i<adj.size(); i++)
        {
            //find the count of edges to keep track of unused edges
            edges.put(i,adj.get(i).size());
        }

        // Maintain a stack to keep vertices
        Stack<Integer> curr_path = new Stack<Integer>();

        // vector to store final circuit
        List<Integer> circuit = new ArrayList<Integer>();

        // We start from vertex 0 TODO
        curr_path.push(0);

        // Current vertex
        int curr_v = 0;

        /*while (!curr_path.empty())
        {
            // If there's remaining edge
            if (edges.get(curr_v)>0)
            {
                // Push the vertex visited.
                curr_path.push(adj.get(curr_v).get(edges.get(curr_v) - 1));

                // and remove that edge or decrement the edge count.
                edges.put(curr_v, edges.get(curr_v) - 1);

                // remove gegenedges
                edges.put(edges.get(curr_v) - 1, curr_v);

                // Move to next vertex
                curr_v = curr_path.peek();
            }

            // back-track to find remaining circuit
            else
            {
                circuit.add(curr_path.peek());
                curr_v = curr_path.pop();
            }
        } */


        // After getting the circuit, now print it in reverse
        for (int i=circuit.size()-1; i>=0; i--)
        {
            System.out.print(circuit.get(i));

            if(i!=0)
                System.out.print(" -> ");
        }

    }

}

