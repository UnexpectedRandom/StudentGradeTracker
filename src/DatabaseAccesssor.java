import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseAccessor {

    public void addStudent() {

    };

    public void deleteStudent(String studentId, Connection connection) {
        String delete_query = "Delete FROM Student WHERE StudentID = ?";
        
        try(PreparedStatement connection_statment = connection.prepareStatement(delete_query);) {
            
            connection_statment.setString(1, studentId);

            // the number of rows that were changed by SQL statement
            int rowsAffected = connection_statment.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("No Students Found With ID: " + studentId);
            }
            
        } catch(SQLException se) {
            System.out.println("Error in Deleting Student");
            se.printStackTrace();
        }
    }

    public void getStudent(String studentID, Connection connection) {
        String getStudentQuery = "SELECT * FROM STUDENT WHERE StudentID = ?";

        try(PreparedStatement connection_Statement = connection.prepareStatement(getStudentQuery)) {
            connection_Statement.setString(1, studentID);
            
            ResultSet result = connection_Statement.executeQuery();
            String FirstName = null;
            String LastName = null;
            String Email = null;

            if (result.next()) {
                FirstName = result.getString("FirstName");
                LastName = result.getString("LastName");
                Email = result.getString("Email");
                String output = "ID: " + studentID + "\n" +  "First Name: " + FirstName + "\n" + "Last Name: " + LastName + "\n" + "Email: " + Email + "\n";
                System.out.println(output);
            } else {
                System.out.println("Student with ID " + studentID + " not found");
            }

            

        } catch (SQLException se) {
            System.out.println("Error in Fetching Student");
            se.printStackTrace();
        }
    }

}
