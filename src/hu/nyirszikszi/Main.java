package hu.nyirszikszi;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Nobel> list = Actions.readList("nobel.csv");
        /*for (Nobel nobel: list) {
            System.out.println(nobel);
        }*/

        System.out.println("3. feladat:\n" + Actions.prizeOfABMcD(list, "Arthur B.", "McDonald"));

        System.out.println();

        System.out.println("4. feladat:\n" + Actions.winnerOfLiteratureAward2017(list, 2017, "irodalmi"));

        System.out.println();

        System.out.println("5. feladat:");
        for (String winner: Actions.winnersOfPeaceAwardOrganizations(list, 1990, "béke")) {
            System.out.println(winner);
        }

        System.out.println();

        System.out.println("6. feladat:");
        for (String winner: Actions.winnersOfCurieFamily(list)) {
            System.out.println(winner);
        }

        System.out.println();

        System.out.println("7. feladat:");
        for (String[] type: Actions.pcsPerType(list)) {
            System.out.println(type[0]  + ": " + type[1]);
        }

        System.out.println();

        Actions.writeList(list, "orvosi", "orvosi.txt");
    }
}