import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    // Make 1 instance of it to save system resources
    private Scanner scanner = new Scanner(System.in);
    

    public String getCommand() {
        System.out.println("> ");
        return scanner.nextLine();
    }

    public int inputStudentID() {
        while (true) {
            System.out.println("Enter Student ID: ");
            try {
                return scanner.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("Must input An Intager Value");
                e.printStackTrace();
                scanner.next();
            }   
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    public void helpCommand() {
        System.out.println("\n========================== COMMAND MENU ==========================");
        System.out.printf("%-18s | %-45s\n", "COMMAND", "DESCRIPTION");
        System.out.println("------------------------------------------------------------------");
        
        System.out.printf("%-18s | %-45s\n", "add-student", "Add new student to the database");
        System.out.printf("%-18s | %-45s\n", "get-student", "Search for a student by their ID");
        System.out.printf("%-18s | %-45s\n", "delete-student", "Remove a student from the system");
        
        System.out.printf("%-18s | %-45s\n", "add-grade", "Assign a grade to a student");
        System.out.printf("%-18s | %-45s\n", "get-grades", "View a student's full grade report");
        System.out.printf("%-18s | %-45s\n", "delete-grade", "Remove a specific grade entry");
        System.out.printf("%-18s | %-45s\n", "view-classes", "List all available classes");
        
        System.out.printf("%-18s | %-45s\n", "help", "Show this list of commands");
        System.out.printf("%-18s | %-45s\n", "exit", "Close the application");
        
        System.out.println("==================================================================\n");
    }
}
