package com.example.project;

import java.io.*;
import java.nio.file.*;

public class User {
    String my_username;
    String my_password;

    public String getMy_username() {
        return my_username;
    }

    public void setMy_username(String my_username) {
        this.my_username = my_username;
    }

    public String getMy_password() {
        return my_password;
    }

    public void setMy_password(String my_password) {
        this.my_password = my_password;
    }

    public User(String my_username, String my_password) {
        this.my_username = my_username;
        this.my_password = my_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "my_username='" + my_username + '\'' +
                ", my_password='" + my_password + '\'' +
                '}';
    }

    public int readFile() throws IOException {//functia citeste din fisier nume si parola
        File myFile = new File("src/main/java/com/example/project/users.csv");
        myFile.createNewFile();
        try (BufferedReader br = new BufferedReader(new FileReader(("src/main/java/com/example/project/users.csv")))) {
            String line;
            boolean user_exist = false; //variabila user_exist devine true atunci cand un nou obiect user se instantiaza si se scrie in fisier
            while ((line = br.readLine()) != null) {
                if (line.equals(my_username + " " + my_password)) {
                    user_exist = true;
                    br.close();
                }
                if (user_exist) {
                    br.close();
                    return -1;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    public void writeFile() { //functia scrie in fisierul users.csv numele si parola userului
        try (FileWriter fw = new FileWriter("src/main/java/com/example/project/users.csv", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(my_username + " " + my_password);
//            fw.close();
//            bw.close();
            out.close();
            System.out.println("{ 'status' : 'ok', 'message' : 'User created successfully'}");

        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void deleteFile() {//functia sterge un fisier de la calea data (am gasit functia pe net)
        Path path = FileSystems.getDefault().getPath("src/main/java/com/example/project/users.csv");
        Path path2 = FileSystems.getDefault().getPath("src/main/java/com/example/project/question.csv");
        Path path3 = FileSystems.getDefault().getPath("src/main/java/com/example/project/quizz.csv");

        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
            System.err.format("%s: no such" + " file or directory%n", path2);
            System.err.format("%s: no such" + " file or directory%n", path3);

        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
    
    


