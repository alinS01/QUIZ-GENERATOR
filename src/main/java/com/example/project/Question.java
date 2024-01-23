package com.example.project;

import java.io.*;
import java.util.ArrayList;

public class Question {
    static String text;
    static String type;
    ArrayList<Answer> answer;
    int questionIdentifier;

    public Question(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int readUser(String my_username, String my_password) throws IOException { //functia  citeste din fisier user si parola si verifica daca exista user sau daca nume/parola sunt gresite
        File myFile = new File("src/main/java/com/example/project/users.csv");
        myFile.createNewFile();
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/users.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(my_username + " " + my_password)) {
                    br.close();
                    return 1;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static int readQuestionFile() throws IOException {//functia citeste din fisier argumentele primite
        File myFile2 = new File("src/main/java/com/example/project/question.csv");
        myFile2.createNewFile();
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/question.csv")))) {
            String line;
            boolean text_exist = false;
            while ((line = br.readLine()) != null) {
                if (line.equals(text + type)) {
                    text_exist = true;
                    br.close();
                }
                if (text_exist) {
                    br.close();
                    return -1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public void writeQuestionFile() { //functia scrie in fisierul question.csv argumentele primite
        try (FileWriter fw = new FileWriter("src/main/java/com/example/project/question.csv", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(text + type);
            // fw.close();
            // bw.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public static int verifyText() throws IOException {
        File myFile2 = new File("src/main/java/com/example/project/question.csv");
        myFile2.createNewFile();
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/question.csv")))) {
            String line;
            boolean text_provided = true;
            while ((line = br.readLine()) != null) {
                if (!line.equals(text + type)) {
                    text_provided = false;
                    br.close();
                }
                if (text_provided) {
                    br.close();
                    return -1;
                }
                //process the line

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }


}
