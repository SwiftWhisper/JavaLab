package sumdu.edu.ua.repository;

import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.service.Database.DatabaseManager;

public class DatabaseEmployeeRepository implements EmployeeRepository {

    private final DatabaseManager db;

    public DatabaseEmployeeRepository(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public void save(Employee employee, Company company) {
        db.insertEmployee(employee, company);
    }
}
