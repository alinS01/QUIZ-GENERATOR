package com.example.project;

import java.io.File;
import java.io.IOException;

public class Tema1 {

    public static void main(final String[] args) {
//		System.out.println(args[0]);
//		System.out.println(args[1]);
//		System.out.println(args[2]);
//		System.out.println(args[3]);
//		System.out.println(args[4]);
//		System.out.println(args[5]);
//		System.out.println(args[6]);
//		System.out.println(args[7]);
//		System.out.println(args[8]);


        if (args == null) {
            System.out.print("Hello world!");
            return;
        }
        //task 1
        if (args[0].equals("-create-user")) {
            if (args.length < 2) {// if-ul verifica daca lungimea argumentelor primite este mai mica decat 2(in cazul acesta lungimea este 1,argumentul primit fiind doar create user
                System.out.println("{ 'status' : 'error', 'message' : 'Please provide username'}");
                return;
            }
            if (args.length < 3) {//aici argumentele sunt doua, create user si -u username
                System.out.println("{ 'status' : 'error', 'message' : 'Please provide password'}");
                return;
            }
            User user = new User(args[1], args[2]); //se instantiaza un nou obiect de tip user cu args[1] si args[2] ca parametri,acestea fiind username si parola
            try {
                if (user.readFile() == -1) {
                    System.out.println("{ 'status' : 'error', 'message' : 'User already exists'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            user.writeFile();
        }
//task2
        if (args[0].equals("-create-question")) {
            if (args.length < 2) {//similar ca la user
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {//similar ca la user
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            try {
                if (Question.readUser(args[1], args[2]) == 0) {// daca argumentele sunt 0 inseamna ca nu exista user,deci login failed
                    System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (Question.readQuestionFile() == -1) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Question already exists'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Question question = new Question(args[3], args[4]);
            question.questionIdentifier++;
            question.writeQuestionFile();
            if (args.length < 6) { // argumentul answer nu se primeste ca input
                System.out.println("{ 'status' : 'error', 'message' : 'No answer provided'}");
                return;
            }
            if (args.length < 8) {
                System.out.println("{ 'status' : 'error', 'message' : 'Only one answer provided'}");
                return;
            }
            if (!Question.text.contains("-text")) {
                System.out.println("{ 'status' : 'error', 'message' : 'No question text provided'}");
                return;
            }
            for (int k = 5; k < args.length - 1; k += 2) {
                int answerIndex = (k - 5) / 2 + 1;
                if (args[k].contains("-is-correct")) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Answer " + answerIndex + " has no answer description'}");
                    return;
                }
                if (!args[k].contains("-is-correct")) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Answer " + answerIndex + " has no answer correct flag'}");
                    return;
                }
                String answer_description = args[k].split(" ")[1];//aici am extras doar descriptorul "Yes" din arg primit
                String answer_flag = args[k + 1].split(" ")[1];//aici doar flag-ul 0 sau 1 din arg primit
                Answer answer = new Answer(answer_description, Integer.parseInt(answer_flag.replace("'", "")));
                question.answer.add(answer);//se adauga raspunsurile in vectorul de raspunsuri
            }


        }
        //task3
        if (args[0].equals("-get-question-id-by-text")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            try {
                if (Question.readUser(args[1], args[2]) == 0) {// daca argumentele sunt 0 inseamna ca nu exista user,deci login failed
                    System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (Question.readQuestionFile() != -1) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Question does not exist'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//task4
        if (args[0].equals("-get-all-questions")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            try {
                if (Question.readUser(args[1], args[2]) == 0) {// daca argumentele sunt 0 inseamna ca nu exista user,deci login failed
                    System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
//task5
        if (args[0].equals("-create-quizz")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            try {
                if (Question.readUser(args[1], args[2]) == 0) {// daca argumentele sunt 0 inseamna ca nu exista user,deci login failed
                    System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                    return;
                }
                Quizz quizz = new Quizz(args[3]);
                quizz.quizzIdentifier++;//creste la fiecare instantiere
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (Quizz.readQuizzFile() != -1) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Quizz name already exists'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        //task6
        if (args[0].equals("-get-quizz-by-name")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            try {
                if (Question.readUser(args[1], args[2]) == 0) {// daca argumentele sunt 0 inseamna ca nu exista user,deci login failed
                    System.out.println("{ 'status' : 'error', 'message' : 'Login failed'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (Quizz.readQuizzFile() != -1) {
                    System.out.println("{ 'status' : 'error', 'message' : 'Quizz does not exist'}");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        //task7
        if (args[0].equals("-get-all-quizzes")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }

        }

        //task8
        if (args[0].equals("-get-quizz-details-by-id")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }

        }
        //task9
        if (args[0].equals("-submit-quizz")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }

        }
        //task10
        if (args[0].equals("-delete-quizz-by-id")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }

        }
        //task11
        if (args[0].equals("-get-my-solutions")) {
            if (args.length < 2) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }
            if (args.length < 3) {
                System.out.println("{ 'status' : 'error', 'message' : 'You need to be authenticated'}");
                return;
            }

        }

        if (args[0].equals("-cleanup-all")) {
            File myFile = new File("src/main/java/com/example/project/users.csv");
            File myFile2 = new File("src/main/java/com/example/project/question.csv");
            File myFile3 = new File("src/main/java/com/example/project/quizz.csv");

            myFile.delete();
            myFile2.delete();
            myFile3.delete();
        }


    }
}


