package hu.nyirszikszi;

import java.io.RandomAccessFile;
import java.util.ArrayList;

class Actions {
    static ArrayList<Nobel> readList(String fileName) {
        ArrayList<Nobel> list = new ArrayList<Nobel>();

        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            String row = raf.readLine();
            row = raf.readLine();
            String[] slice;
            String utf;

            while (row != null) {
                utf = new String(row.getBytes("ISO-8859-1"), "UTF-8");
                slice = utf.split(";");

                if (slice.length == 4) {
                    list.add(new Nobel(Integer.parseInt(slice[0]), slice[1], slice[2], slice[3]));
                }
                else {
                    list.add(new Nobel(Integer.parseInt(slice[0]), slice[1], slice[2], null));
                }

                row = raf.readLine();
            }

            raf.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    static String prizeOfABMcD(ArrayList<Nobel> list, String keresztnev, String vezeteknev) {
        for (Nobel nobel : list) {
            if (nobel.getKeresztnev().equals(keresztnev) && nobel.getVezeteknev().equals(vezeteknev)) {
                return nobel.getTipus();
            }
        }

        return "Nincs találat!";
    }

    static String winnerOfLiteratureAward2017(ArrayList<Nobel> list, int year, String type) {
        for (Nobel nobel : list) {
            if (nobel.getEvszam() == (year) && nobel.getTipus().equals(type)) {
                return nobel.getKeresztnev() + " " + nobel.getVezeteknev();
            }
        }

        return "Nincs találat!";
    }

    static ArrayList<String> winnersOfPeaceAwardOrganizations(ArrayList<Nobel> list, int from, String type) {
        ArrayList<String> winners = new ArrayList<>();

        for (Nobel nobel : list) {
            if (nobel.getEvszam() >= from && nobel.getTipus().equals(type) && nobel.getVezeteknev() == null) {
                winners.add(nobel.getEvszam() + ": " + nobel.getKeresztnev());
            }
        }

        return winners;
    }

    static ArrayList<String> winnersOfCurieFamily(ArrayList<Nobel> list) {
        ArrayList<String> winners = new ArrayList<>();

        for (Nobel nobel : list) {
            try {
                if (nobel.getVezeteknev().contains("Curie")) {
                    winners.add(nobel.getEvszam() + ": " + nobel.getKeresztnev() + " " + nobel.getVezeteknev() + " (" + nobel.getTipus() + ")");
                }
            }
            catch (Exception e) {
                //System.out.println(e.getMessage());
            }
        }

        return winners;
    }

    static ArrayList<String[]> pcsPerType(ArrayList<Nobel> list) {
        ArrayList<String[]> result = new ArrayList<>();
        String[] types = {"fizikai", "kémiai", "orvosi", "irodalmi", "béke", "közgazdaságtani"};
        int counter;

        for (String type : types) {
            counter = 0;

            for (Nobel nobel : list) {
                if (nobel.getTipus().equals(type)) {
                    counter++;
                }
            }

            result.add(new String[] {type, Integer.toString(counter)});
        }

        return result;
    }

    static void writeList(ArrayList<Nobel> list, String type, String fileName) {
        ArrayList<String> results = new ArrayList<>();

        for (Nobel nobel : list) {
            if (nobel.getTipus().equals(type)) {
                results.add(nobel.getEvszam() + ": " + nobel.getKeresztnev() + " " + nobel.getVezeteknev() + " (" + nobel.getTipus() + ")");
            }
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

            results.sort(String::compareTo);

            for (String result : results) {
                raf.writeBytes(result + "\r\n");
            }

            raf.close();

            System.out.println("8. feladat: " + fileName);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}