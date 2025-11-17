import java.util.Scanner;
public class ReadSearch {

    Scanner scanner;
    public ReadSearch(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getSearch(){
        String search;
        while(true){
            search = scanner.nextLine();
            if (search.isEmpty()){
                System.out.println("Its can't be empty. Please try again:");
            }
            else break;
        }
        return search;
    }

    public int intCheck(int size){
        while (true){
            try {
                String input = getSearch();
                int num = Integer.parseInt(input);
                if (num < 1 || num > size){
                    System.out.println("Number must be between 1 and " + size + "! Please try again:");
                }
                else return num;
            }
            catch (NumberFormatException e){
                System.out.println("Invalid choice! Please try again:");
            }
        }
    }
}