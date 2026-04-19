/*
* Author: Mohammad Karim
* 
* Purpose: DatabaseAccessor.java is a list of functions that allow sql querys and updates to be ran.
*/

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseAccessor {

    public void handleSQLException(SQLException e, String Context) {
        System.out.println("\n====================================");
        System.out.println("Error in: " + Context);
        e.printStackTrace();
        System.out.println("====================================");
    }

    public void addStudent(Connection connection, String lastName, String firstName, String email) {
        String insertStudent = "INSERT INTO STUDENT (LastName, FirstName, Email) VALUES (?, ?, ?)";

        try (PreparedStatement connectionStatement = connection.prepareStatement(insertStudent)) {
            connectionStatement.setString(1, lastName);
            connectionStatement.setString(2, firstName);
            connectionStatement.setString(3, email);

            int rowsAdded = connectionStatement.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("\nSuccessfully Added To The Student Table");
            } else {
                System.out.println("Unable to add Student With Such Values");
            }

        } catch (SQLException se) {
            handleSQLException(se, "Inserting student values");
        }
    };

    public void deleteStudent(int studentId, Connection connection) {
        String deleteQuery = "Delete FROM Student WHERE StudentID = ?";
        
        try(PreparedStatement connectionStatment = connection.prepareStatement(deleteQuery)) {
            
            connectionStatment.setInt(1, studentId);

            int rowsAffected = connectionStatment.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\n========================================================");
                System.out.println("Student:" + studentId + " Has Been Successfully Removed From The Database");
                System.out.println("========================================================");
            } else {
                System.out.println("\n========================================================");
                System.out.println("No Students Found With ID: " + studentId);
                System.out.println("========================================================");
            }
            
        } catch(SQLException se) {
            handleSQLException(se, "deleting student: "+studentId);
        }
    }

    public void getStudent(int studentID, Connection connection) {
        String grabStudent = "SELECT * FROM STUDENT WHERE StudentID = ?";

        try(PreparedStatement connectionStatement = connection.prepareStatement(grabStudent)) {
            connectionStatement.setInt(1, studentID);
            
            // returns a stream of data but capture it and indexing it later in our code.
            ResultSet result = connectionStatement.executeQuery();
            String firstName = null;
            String lastName = null;
            String email = null;

            if (result.next()) {
                firstName = result.getString("firstName");
                lastName = result.getString("lastName");
                email = result.getString("email");
                System.out.println("\n==================== STUDENT INFORMATION ====================");
                System.out.printf("%-15s | %-15s | %-15s | %s%n", "STUDENT ID", "FIRST NAME", "LAST NAME", "EMAIL");
                System.out.println("-------------------------------------------------");
                System.out.printf("%-15d | %-15s | %-15s | %s%n", 
                      studentID, firstName, lastName, email);
                System.out.println("==============================================================");
            } else {
                System.out.println("\n==============================================================");
                System.out.println("Student with ID " + studentID + " not found");
                System.out.println("==============================================================");
            }

            

        } catch (SQLException se) {
            handleSQLException(se, "fetching student: "+ studentID);
        }
    }

    public void getGrades(int studentID, Connection connection) {

        // using inner joins and other operations to combine multiple data from the tables to be able to get a certain students grade
        // Link to sql joins: https://joins.spathon.com/
        String grabStudentGrade = "SELECT Student.FirstName, Classes.ClassName, Classes.Credits, Grades.Score, Grades.FeedBack " +
        "FROM Grades " + 
        "INNER JOIN  Student " + 
        "ON Grades.StudentID = Student.StudentID " + 
        "INNER JOIN Classes " + 
        "ON Grades.ClassID = Classes.ClassID " +
        "WHERE Student.StudentID = ?"; 


        try (PreparedStatement connectionStatement = connection.prepareStatement(grabStudentGrade)) {
            connectionStatement.setInt(1, studentID);

            ResultSet result = connectionStatement.executeQuery();

            String firstName = null;
            String className = null;
            String FeedBack = null;
            int credits = 0;
            float score = 0;
            
            System.out.println("\n==================== STUDENT GRADE REPORT ====================");
            System.out.printf("%-15s | %-15s | %-7s | %-7s | %s%n", "STUDENT", "CLASS", "CREDITS", "SCORE", "FEEDBACK");
            System.out.println("--------------------------------------------------------------");
            while (result.next()) {
                firstName = result.getString("Firstname");
                className = result.getString("ClassName");
                FeedBack = result.getString("FeedBack");
                credits = result.getInt("Credits");
                score = result.getFloat("Score");
                System.out.printf("%-15s | %-15s | %-7d | %-7.2f | %s%n", 
                      firstName, className, credits, score, FeedBack);
            }
            System.out.println("\n==============================================================\n");

            
            
        } catch (SQLException se) {
            handleSQLException(se, "Grabbing Student: "+studentID+" grade report");
        }

    }

    public void addGrade(Connection connection, int studentID, String feedBack, float score, int classID) {
        String addGradeQuery = "INSERT INTO Grades (StudentID, ClassID, Score, FeedBack) VALUES (?, ?, ?, ?);";
        
        try (PreparedStatement addGradeStatment = connection.prepareStatement(addGradeQuery)) {

            addGradeStatment.setInt(1, studentID);
            addGradeStatment.setInt(2, classID);
            addGradeStatment.setFloat(3, score);
            addGradeStatment.setString(4, feedBack);

            int rowsAdded = addGradeStatment.executeUpdate();

            if (rowsAdded > 0) {
                System.out.println("\nSuccesfully inserted data into the Grades tables");
            } else {
                System.out.println("\nError record might already exist in the Grades table");
            }


        } catch(SQLException es) {
            handleSQLException(es, "adding Grades for Student: "+ studentID);
        }

    }
    
    public void deleteGrade(Connection connection, int studentID, int classID) {
        String deleteGradeQuery = "DELETE FROM GRADES WHERE STUDENTID = ? AND CLASSID = ?";

        try(PreparedStatement connectionStatement = connection.prepareStatement(deleteGradeQuery)) {
            connectionStatement.setInt(1, studentID);
            connectionStatement.setInt(2, classID);

            int rowsAffected = connectionStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully deleted grade for StudentID: " + studentID + " and classID: " + classID);
            } else {
                System.out.println("Failed to delete grade for StudentID: "+ studentID + " and ClassID: " + classID);
                return;
            }

        } catch (SQLException es) {
            handleSQLException(es, "deleting Grades for Student: " + studentID);
        }
    }


    public void getClassTable(Connection connection) {
        String getClassQuery  = "SELECT * FROM Classes;";

        try(PreparedStatement getClassStatment = connection.prepareStatement(getClassQuery)) {

            
            ResultSet result = getClassStatment.executeQuery();
            
            int classID = 0;
            float credits = 0;
            String className = "";
            String classDescription = "";


            System.out.println("\n==================== CLASS TABLE INFO ====================");
            System.out.printf("%-10s | %-25s | %-10s | %-30s%n","CLASS ID", "CLASS NAME", "CREDITS", "CLASS DESCRIPTION");
            System.out.println("--------------------------------------------------------------");
            while(result.next()) {  
                classID = result.getInt("ClassID");
                className = result.getString("ClassName");
                classDescription = result.getString("ClassDescription");
                credits = result.getFloat("Credits");
                System.out.printf("%-10d | %-25s | %-10.2f | %-30s%n", classID, className, credits, classDescription);
            }
            System.out.println("============================================================");
            

        } catch (SQLException se) {
           handleSQLException(se, "Fetching Information From The Class Table");
        }



    }
}
