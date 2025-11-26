import java.io.File;
import java.util.Scanner;

public class FileInputReader {
    public File input() {

        System.out.println("Enter the path to the file (click on enter to exit)");

        Scanner read = new Scanner(System.in);
        String name;
        name = read.nextLine();
        if (name.isEmpty()) {
            System.out.println("Exit...");
            System.exit(0);
            return null;
        }

        String dir = "C:\\JavaProjects\\OOP\\prac2\\";
        File file = new File(dir + name);
        if (file.exists()) {
            System.out.println("File path to processing: " + name);
            return file;
        } else
            System.out.println("File not found! Try again ");
        return null;
    }
}
