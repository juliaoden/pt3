package org.example;

import java.util.Scanner;

public class Main {
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

        switch (problem) {
            // Problem: Straßen müssen her
            case 1:
                Dijkstra d = new Dijkstra();
                d.start(d);
                break;
            // Problem: Wasserversorgung
            case 2:
                break;
            // Problem: Stromversorgung
            case 3:
                break;
            // Problem: Historische Funde
            case 4:
                break;
            // Problem: Die Festhochzeit – das Verteilen der Einladungen
            case 5:
                break;
            // Problem: Wohin nur mit den Gästen?
            case 6:
                break;
            // Problem: Es gibt viel zu tun! Wer macht‘s?
            case 7:
                break;
        }

    }
}