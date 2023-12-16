package school9;
import java.sql.*;
public class School9Table2 {
    private static final String URL = "jdbc:mysql://localhost:3306/school9";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql123";

    public static void createMarksTable() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stm = conn.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS student_marks (" +
                    "redno INT PRIMARY KEY, " +
                    "english INT, " +
                    "telugu INT, " +
                    "tamil INT, " +
                    "marks INT)";
            stm.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addMarks(int redno, int english, int telugu, int tamil, int marks) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "INSERT INTO student_marks (redno, english, telugu, tamil, marks) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, redno);
            preparedStatement.setInt(2, english);
            preparedStatement.setInt(3, telugu);
            preparedStatement.setInt(4, tamil);
            preparedStatement.setInt(5, marks);
            preparedStatement.executeUpdate();
            System.out.println("one row of student marks inserted successfully!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void displayMarks() {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet resultSet = stm.executeQuery("SELECT * FROM student_marks")) {

            while (resultSet.next()) {
                int redno = resultSet.getInt("redno");
                int english = resultSet.getInt("english");
                int telugu = resultSet.getInt("telugu");
                int tamil = resultSet.getInt("tamil");
                int marks = resultSet.getInt("marks");
                System.out.println("Red No: " + redno + ", English: " + english + ", Telugu: " + telugu +
                        ", Tamil: " + tamil + ", Marks: " + marks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateMarks(int redno, int english, int telugu, int tamil, int marks) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "UPDATE student_marks SET english=?, telugu=?, tamil=?, marks=? WHERE redno=?")) {
            preparedStatement.setInt(1, english);
            preparedStatement.setInt(2, telugu);
            preparedStatement.setInt(3, tamil);
            preparedStatement.setInt(4, marks);
            preparedStatement.setInt(5, redno);
            preparedStatement.executeUpdate();
            System.out.println("one row of student marks updated successfully!!!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteMarks(int redno) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM student_marks WHERE redno=?")) {
            preparedStatement.setInt(1, redno);
            preparedStatement.executeUpdate();
            System.out.println("one row of student marks updated successfully!!!!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
