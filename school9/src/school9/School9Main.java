package school9;
import java.sql.*;
import java.util.Scanner;
public class School9Main {
    private static final String URL = "jdbc:mysql://localhost:3306/school9";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql123";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stm = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            stm = conn.createStatement();
            handleTableChoice(conn, stm, scanner);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            try {
                if (stm != null) stm.close();	
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void handleTableChoice(Connection conn, Statement stm, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Choose a table:");
            System.out.println("1. Student Details");
            System.out.println("2. Student Marks");
            System.out.println("3. Exiting the program");
            int tableChoice = scanner.nextInt();
            switch (tableChoice) {
                case 1:
                    School9Table1.createStudentTable();
                    System.out.println("Student Details table created successfully.");
                    handleStudentDetails(conn, stm, scanner);
                    break;
                case 2:
                    School9Table2.createMarksTable();
                    System.out.println("Student Marks table created successfully.");
                    handleStudentMarks(conn, stm, scanner);
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void handleStudentDetails(Connection conn, Statement stm, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Choose an operation for Student Details:");
            System.out.println("1. Add Student(INSERT)");
            System.out.println("2. Display Students(READ)");
            System.out.println("3. Update Students(UPDATE)");
            System.out.println("4. Delete students(DELETE)");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter student Red No:");
                    int redno = scanner.nextInt();
                    System.out.println("Enter student name:");
                    String name = scanner.next();
                    System.out.println("Enter student class:");
                    int studentClass = scanner.nextInt();
                    System.out.println("Enter student section:");
                    int section = scanner.nextInt();
                    System.out.println("Enter student DOB (YYYY-MM-DD):");
                    String dob = scanner.next();
                    School9Table1.addStudent(redno, name, studentClass, section, dob);
                    break;
                case 2:
                    System.out.println("Student Details:");
                    School9Table1.displayStudents();
                    break;
                case 3:
                	System.out.println("Enter the Red No of the student to update:");
                    int updateRedno = scanner.nextInt();
                    System.out.println("Enter new student name:");
                    String newName = scanner.next();
                    System.out.println("Enter new student class:");
                    int newClass = scanner.nextInt();
                    System.out.println("Enter new student section:");
                    int newSection = scanner.nextInt();
                    System.out.println("Enter new student DOB (YYYY-MM-DD):");
                    String newDob = scanner.next();
                    School9Table1.updateStudent(updateRedno, newName, newClass, newSection, newDob);
                    System.out.println("Student details updated successfully.");
                    break;
                case 4:
                	System.out.println("Enter the Redgno to delete student row");
                	int del=scanner.nextInt();
                	School9Table1.deleteStudent(del);
                	break;
                case 5:
                    System.out.println("Exiting from the student details table.");
                    handleTableChoice(conn, stm, scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void handleStudentMarks(Connection conn, Statement stm, Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Choose an operation for Student Marks:");
            System.out.println("1. Add Marks(INSERT)");
            System.out.println("2. Display Marks(READ)");
            System.out.println("3. UPDATE Marks(UPDATE)");
            System.out.println("4. Delete Marks(DELETE)");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter student Red No:");
                    int marksRedno = scanner.nextInt();
                    System.out.println("Enter marks for English:");
                    int english = scanner.nextInt();
                    System.out.println("Enter marks for Telugu:");
                    int telugu = scanner.nextInt();
                    System.out.println("Enter marks for Tamil:");
                    int tamil = scanner.nextInt();
                    int totalMarks = english + telugu + tamil;
                    School9Table2.addMarks(marksRedno, english, telugu, tamil, totalMarks);
                    break;
                case 2:
                    System.out.println("Student Marks:");
                    School9Table2.displayMarks();
                    break;
                case 3:
                	System.out.println("Enter student Red No to update marks:");
                    int updateMarksRedno = scanner.nextInt();
                    System.out.println("Enter new marks for English:");
                    int newEnglish = scanner.nextInt();
                    System.out.println("Enter new marks for Telugu:");
                    int newTelugu = scanner.nextInt();
                    System.out.println("Enter new marks for Tamil:");
                    int newTamil = scanner.nextInt();
                    System.out.println("Enter new total marks:");
                    int newTotalMarks = newEnglish + newTelugu + newTamil;
                    School9Table2.updateMarks(updateMarksRedno, newEnglish, newTelugu, newTamil, newTotalMarks);
                    System.out.println("Marks updated successfully.");
                    break;
                case 4:
                	System.out.println("Enter the Redgno to delete student row");
                	int del=scanner.nextInt();
                	School9Table2.deleteMarks(del);
                	break;
                case 5:
                	  System.out.println("Exiting from the student marks table.");
                      handleTableChoice(conn, stm, scanner);
                      break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
