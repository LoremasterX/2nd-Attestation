import edu.aitu.oop3.db.DatabaseConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        System.out.println("Connecting to database...");

        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connected successfully!");
        } catch (Exception e) {
            System.out.println("Connection failed:");
            e.printStackTrace();
        }
    }
}