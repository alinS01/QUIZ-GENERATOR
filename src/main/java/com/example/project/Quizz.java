package com.example.project;

import java.io.*;

public class Quizz {

    static String name;
    int quizzIdentifier;//creste la fiecare instantiere a unui nou obiect

    public Quizz(String name) {
        this.name = name;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Quizz.name = name;
    }

    public int getQuizzIdentifier() {
        return quizzIdentifier;
    }

    public void setQuizzIdentifier(int quizzIdentifier) {
        this.quizzIdentifier = quizzIdentifier;
    }

    public static int readQuizzFile() throws IOException {//functia citeste din fisier argumentele primite
        File myFile3 = new File("src/main/java/com/example/project/quizz.csv");
        myFile3.createNewFile();
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/quizz.csv")))) {
            String line;
            boolean name_exist = false;
            while ((line = br.readLine()) != null) {
                if (line.equals(name)) {
                    name_exist = true;
                    br.close();
                }
                if (name_exist) {
                    br.close();
                    return -1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public void writeQuizzFile() { //functia scrie in fisierul question.csv argumentele primite
        try (FileWriter fw = new FileWriter("src/main/java/com/example/project/quizz.csv", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(name);
            // fw.close();
            // bw.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

}
