package sumdu.edu.ua.repository;

import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;

public interface EmployeeRepository {
    void save(Employee employee, Company company);
}
