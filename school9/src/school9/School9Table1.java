package school9;
import java.sql.*;
public class School9Table1 {
    private static final String URL = "jdbc:mysql://localhost:3306/school9";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql123";

    public static void createStudentTable() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stm = conn.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS student_details (" +
                    "redno INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "class INT, " +
                    "sec INT, " +
                    "DOB DATE)";
            stm.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addStudent(int redno, String name, int studentClass, int section, String dob) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "INSERT INTO student_details (redno, name, class, sec, DOB) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, redno);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, studentClass);
            preparedStatement.setInt(4, section);
            preparedStatement.setDate(5, Date.valueOf(dob));
            preparedStatement.executeUpdate();
            System.out.println("one row of student details inserted successfully!!!!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayStudents() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery("SELECT * FROM student_details")) {

            while (resultSet.next()) {
                int redno = resultSet.getInt("redno");
                String name = resultSet.getString("name");
                int studentClass = resultSet.getInt("class");
                int section = resultSet.getInt("sec");
                Date dob = resultSet.getDate("DOB");
                System.out.println("Red No: " + redno + ", Name: " + name + ", Class: " + studentClass +
                        ", Section: " + section + ", DOB: " + dob);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(int redno, String newName, int newClass, int newSection, String newDob) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "UPDATE student_details SET name=?, class=?, sec=?, DOB=? WHERE redno=?")) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newClass);
            preparedStatement.setInt(3, newSection);
            preparedStatement.setDate(4, Date.valueOf(newDob));
            preparedStatement.setInt(5, redno);
            preparedStatement.executeUpdate();
            System.out.println("one row of student details updated successfully!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteStudent(int redno) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM student_details WHERE redno=?")) {
            preparedStatement.setInt(1, redno);
            preparedStatement.executeUpdate();
            System.out.println("one row of student details deleted successfully!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
