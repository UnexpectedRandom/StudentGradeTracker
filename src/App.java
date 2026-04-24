import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection connectSever = DatabaseConnection.getConnection();
        boolean quit = false;

        Console console = new Console();
        DatabaseAccessor databaseManipulator = new DatabaseAccessor();

        while (!quit) {
            String command = console.getCommand();

            if (command.equalsIgnoreCase("quit")) {
                quit = true;
            }
            if (command.equalsIgnoreCase("help")) {
                console.helpCommand();
            }

            if (command.toLowerCase().startsWith("get ")) {
                if (command.substring(4).equalsIgnoreCase("student")) {
                    databaseManipulator.getStudent(console.inputStudentID(), connectSever);
                } else if (command.substring(4).equalsIgnoreCase("grades")) {
                    databaseManipulator.getGrades(console.inputStudentID(), connectSever);
                } else if(command.substring(4).equalsIgnoreCase("class")) {
                    databaseManipulator.getClassTable(connectSever);
                }
            }

            if (command.toLowerCase().startsWith("delete ")) {
                if(command.substring(7).equalsIgnoreCase("student")) {
                    databaseManipulator.deleteStudent(console.inputStudentID(), connectSever);
                } else if(command.substring(7).equalsIgnoreCase("grades")){
                    databaseManipulator.deleteGrade(connectSever, console.inputStudentID(), console.InputclassID());
                }
            }

            if (command.toLowerCase().startsWith("add ")) {
                if (command.substring(4).equalsIgnoreCase("student")) {
                    String[] studentInfo = console.studentInformation();
                    databaseManipulator.addStudent(connectSever, studentInfo[0], studentInfo[1], studentInfo[2]);
                } else if(command.substring(4).equalsIgnoreCase("grades")) {
                    
                    Console.gradeEntry gradeInfo = console.gradeInfo();
                    databaseManipulator.addGrade(connectSever, gradeInfo.studentID(),gradeInfo.feedback() , gradeInfo.grade(), gradeInfo.classID());
                }
            }
        }

        console.closeScanner();
        
    }
}
