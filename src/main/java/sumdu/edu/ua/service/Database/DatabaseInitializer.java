package sumdu.edu.ua.service.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    private final DatabaseConnection databaseConnection;

    public DatabaseInitializer(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void runSchema(String path) {
        try (Connection conn = databaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = Files.readString(Paths.get(path));

            stmt.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Не вдалося виконати schema.sql", e);
        }
    }
}
