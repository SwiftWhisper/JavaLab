package sumdu.edu.ua.repository;

import sumdu.edu.ua.service.Database.DatabaseManager;
import sumdu.edu.ua.model.Company;

public class DatabaseCompanyRepository implements CompanyRepository {

    private final DatabaseManager db;

    public DatabaseCompanyRepository(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public void save(Company company) {
        db.insertCompany(company);
    }
}
