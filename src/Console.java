/*
* Console.java - Meant to handle user input to be passed as an argument in the DatabaseAccessor.java
*
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    // Make 1 instance of it to save system resources
    private Scanner scanner = new Scanner(System.in);
    

    public String getCommand() {
        System.out.println("> ");
        return scanner.nextLine();
    }
    
    public int handleIntagerInput(String prompt) {
        while (true){
            System.out.println(prompt);
            try {
                int intagerValue =  scanner.nextInt();
                scanner.nextLine();
                return intagerValue;
            }catch(InputMismatchException e) {
                System.out.println("Must input An Integer Value");
                e.printStackTrace();
                scanner.nextLine();
            }
        }
    }

    public float handleFloatInput(String prompt) {
        while (true){
            System.out.println(prompt);
            try {
                 float floatValue =  scanner.nextFloat();
                 scanner.nextLine();
                 return floatValue;
            }catch(InputMismatchException e) {
                System.out.println("Must input An Float Value");
                e.printStackTrace();
                scanner.nextLine();
            }
        }
    }

    public int inputStudentID() {
        return handleIntagerInput("Enter Student ID: ");
    }

    public int inputClassID() {
        return handleIntagerInput("Enter Class ID: ");
    }

    public String[] studentInformation() {
        System.out.println("Enter Student Last Name");
        String lastName = scanner.nextLine();

        System.out.println("Enter Student First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter Student Email Address: ");
        String emailAddress = scanner.nextLine();
        
        String[] studentInfo = {lastName, firstName, emailAddress};

        return studentInfo;

    }

    public gradeEntry gradeInfo() {
        int studentID = handleIntagerInput("Enter Student ID: ");
        int classID = handleIntagerInput("Enter Class ID: ");
        float score = handleFloatInput("Enter Score: ");    
    
        System.out.println("Enter Feedback: ");
        String feedback = scanner.nextLine();

        return new gradeEntry(studentID, classID, score, feedback);
    }


    public void closeScanner() {
        scanner.close();
    }

    public void helpCommand() {
        System.out.println("\n========================== COMMAND MENU ==========================");
        System.out.printf("%-18s | %-45s\n", "COMMAND", "DESCRIPTION");
        System.out.println("------------------------------------------------------------------");
        
        System.out.printf("%-18s | %-45s\n", "add student", "Add new student to the database");
        System.out.printf("%-18s | %-45s\n", "get student", "Search for a student by their ID");
        System.out.printf("%-18s | %-45s\n", "delete student", "Remove a student from the system");
        
        System.out.printf("%-18s | %-45s\n", "add grades", "Assign a grade to a student");
        System.out.printf("%-18s | %-45s\n", "get grades", "View a student's full grade report");
        System.out.printf("%-18s | %-45s\n", "delete grades", "Remove a specific grade entry");
        System.out.printf("%-18s | %-45s\n", "get class", "List all available classes");
        
        System.out.printf("%-18s | %-45s\n", "help", "Show this list of commands");
        System.out.printf("%-18s | %-45s\n", "exit", "Close the application");
        
        System.out.println("==================================================================\n");
    }

    public record gradeEntry(int studentID, int classID, float grade, String feedback) {};
}
