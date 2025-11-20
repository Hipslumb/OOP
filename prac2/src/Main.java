import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] a) {
        while (true) {

            System.out.println("Enter the path to the file (click on enter to exit)");

            Scanner read = new Scanner(System.in);
            String name;
            name = read.nextLine();
            if (name.isEmpty()) {
                System.out.println("Exit...");
                return;
            }

            String dir = "C:\\JavaProjects\\OOP\\prac2\\";
            File file = new File(dir + name);
            if (file.exists()) {
                System.out.println("File path to processing: " + name);
                break;
            }
            else
                System.out.println("File not found! Try again ");
        }
    }
}
