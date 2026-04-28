package sumdu.edu.ua.service.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;

public class DatabaseManager {
    private final DatabaseConnection databaseConnection;

    public DatabaseManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void insertCompany(Company company) {
        String sql = "INSERT INTO company(name) VALUES (?) RETURNING Company_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, company.getName());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                company.assignDatabaseId(resultSet.getLong("Company_id"));
            }

        } catch (SQLException e) {
            System.out.println("Помилка збереження компанії в БД: " + e.getMessage());
        }
    }

    public void insertEmployee(Employee employee, Company company) {
        if (company.getDatabaseId() == null) {
            System.out.println("Помилка: компанія ще не збережена в БД.");
            return;
        }

        String sql = """
                INSERT INTO employee(
                    Company_id,
                    Employee_type,
                    Name_surname,
                    Age,
                    Salary,
                    Position,
                    Years_in_company,
                    Duration_of_contract,
                    Hours_in_day,
                    University,
                    Internship_months
                )
                VALUES (?, ?::employee_type_enum, ?, ?, ?, ?::position_enum, ?, ?, ?, ?, ?)
                RETURNING Employee_id
                """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            Map<String, Object> fields = employee.getDbFields();

            statement.setLong(1, company.getDatabaseId());
            statement.setString(2, employee.getType().name());
            statement.setString(3, employee.getNameSurname());
            statement.setInt(4, employee.getAge());
            statement.setDouble(5, employee.getSalary());
            statement.setString(6, employee.getPosition().name());

            statement.setObject(7, fields.get("Years_in_company"));
            statement.setObject(8, fields.get("Duration_of_contract"));
            statement.setObject(9, fields.get("Hours_in_day"));
            statement.setObject(10, fields.get("University"));
            statement.setObject(11, fields.get("Internship_months"));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                employee.assignDatabaseId(resultSet.getLong("Employee_id"));
            }

        } catch (SQLException e) {
            System.out.println("Помилка збереження співробітника в БД: " + e.getMessage());
        }
    }
}
