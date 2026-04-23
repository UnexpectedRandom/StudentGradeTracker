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
        }

        console.closeScanner();
        
    }
}
