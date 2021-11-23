package com.company;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;

class FileContent extends Thread {
    Vector<String> Words;
    String fname;

    FileContent(String filename) {
        Words = new Vector<>();
        fname = filename;
    }

    public Vector<String> getArr1() {
        return Words;
    }

    public void setArr1(Vector<String> arr1) {
        this.Words = arr1;
    }

    public void run() {
        try {
            File myObj = new File(fname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer st = new StringTokenizer(data);
                while (st.hasMoreTokens()) {
                    this.Words.add(st.nextToken());
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}


class Vocabulary extends Thread {
    BST_class bst;
    Vocabulary(BST_class BT)
    {
        bst = BT;
    }

    public void run() {
        try {
            File myObj = new File("Vocabulary.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer st = new StringTokenizer(data);
                while (st.hasMoreTokens()) {
                    //insert data into BST
                    this.bst.insert(st.nextToken());
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

public class check {
    static int Menu() {
        System.out.println("---------------Menu---------------");
        System.out.println("1) Displaying BST build from Vocabulary File.");
        System.out.println("2) Displaying Vectors build from Input files.");
        System.out.println("3) Viewing Match words and its frequency.");
        System.out.println("4) Searching a query->It should display all the files query found in.");
        System.out.println("5) Enter 5 for Exiting.");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        return choice;
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        while(true)
        {
            int choice = Menu();
            if(choice == 5)
                break;
//        Printing Command Line
            System.out.println("Number of the Command Line Argument");
            System.out.println(args.length);
            System.out.println("Names of the Files taken from the Command Line Argument!! ");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Your " + i + " argument is: " + args[i]);
            }

            BST_class bst = new BST_class();
            Vocabulary object = new Vocabulary(bst);
            Thread voc = new Thread(object);
            voc.start();
            voc.join();
            FileContent f2 = new FileContent("file2.txt");
            Thread file_2 = new Thread(f2);
            file_2.start();
            file_2.join();

            FileContent f1 = new FileContent("file1.txt");
            Thread file_1 = new Thread(f1);
            file_1.start();
            file_1.join();

            if (choice == 1) {
                System.out.println("Printed Binary Search Tree!! ");
                bst.inorder();
            }
            else if (choice == 2) {

                System.out.println("Printing Content of File1!!");
                System.out.println(f1.Words);

                System.out.println("Printing Content of File2!!");
                System.out.println(f2.Words);
            }
            else if(choice == 3)
            {
                Vector<Word> instance_word = new Vector<>();
                Vector<Word> instance_word2 = new Vector<>();
                System.out.println("------------file1-----------");
                Iterator<String> w = f1.Words.iterator();
                while(w.hasNext()) {
                    System.out.println(w.next());
                    try{
                        System.out.println("INner BSST ");
                        bst.inorder();
                        System.out.println(bst.root.key);
                        System.out.println((bst.search(w.next())));
                        if(bst.search(w.next())){
                            Word wd = new Word();
                            System.out.println(w.next());
                            wd.setContent(w.next());
                            wd.frequency++;
                            instance_word.add(wd);
                        }
                    }
                    catch (Exception e)
                    { }

                }
                System.out.println(instance_word.size());
                for (int i = 0; i < instance_word.size(); i++) {
                    Word ww = instance_word.get(i);
                    System.out.println("Word: " + ww.content);
                    System.out.println("Frequency: " + ww.frequency);
                }

                System.out.println("------------file2-----------");
                Iterator<String> w2 = f2.Words.iterator();
                while(w2.hasNext()) {
                    try{
                        if(bst.search(w2.next())){
                            Word wd = new Word();
                            wd.setContent(w2.next());
                            wd.frequency++;
                            instance_word2.add(wd);
                        }
                    }
                    catch (Exception e)
                    { }

                }
                for (int i = 0; i < instance_word2.size(); i++) {
                    Word pr = instance_word2.get(i);
                    System.out.println("Word: " + pr.content);
                    System.out.println("Frequency: " + pr.frequency);
                }
            }
        }

    }
}
