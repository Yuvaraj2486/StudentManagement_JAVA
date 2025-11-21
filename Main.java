import java.util.*;
import java.io.*;

class Student {
    int roll;
    String name;

    Student(int r, String n) {
        roll = r;
        name = n;
    }

    public String toString() {
        return roll + "," + name;
    }
}

public class Main {

    static String file = "students.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All");
            System.out.println("3. Search");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); 

            if (ch == 1) add(sc);
            else if (ch == 2) view();
            else if (ch == 3) search(sc);
            else if (ch == 4) {
                System.out.println("Bye!");
                break;
            } else System.out.println("Invalid!");
        }
    }

    static void add(Scanner sc) {
        try {
            System.out.print("Roll No: ");
            int r = sc.nextInt();
            sc.nextLine();
            System.out.print("Name: ");
            String n = sc.nextLine();

            Student s = new Student(r, n);
            FileWriter fw = new FileWriter(file, true);
            fw.write(s + "\n");
            fw.close();

            System.out.println("Saved!");
        } catch (Exception e) {
            System.out.println("Error adding!");
        }
    }

    static void view() {
        try {
            File f = new File(file);
            if (!f.exists()) {
                System.out.println("No records!");
                return;
            }

            Scanner fr = new Scanner(f);
            System.out.println("\n--- Students ---");

            while (fr.hasNextLine()) {
                String arr[] = fr.nextLine().split(",");
                System.out.println(arr[0] + " - " + arr[1]);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Error reading!");
        }
    }

    static void search(Scanner sc) {
        try {
            System.out.print("Roll To Search: ");
            int r = sc.nextInt();

            File f = new File(file);
            if (!f.exists()) {
                System.out.println("No records!");
                return;
            }

            Scanner fr = new Scanner(f);
            boolean found = false;

            while (fr.hasNextLine()) {
                String arr[] = fr.nextLine().split(",");
                if (Integer.parseInt(arr[0]) == r) {
                    System.out.println("Found: " + arr[1]);
                    found = true;
                    break;
                }
            }

            fr.close();

            if (!found) System.out.println("Not found!");

        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
} 