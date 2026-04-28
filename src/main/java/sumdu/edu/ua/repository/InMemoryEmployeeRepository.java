package sumdu.edu.ua.repository;

import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;

public class InMemoryEmployeeRepository implements EmployeeRepository {

    @Override
    public void save(Employee employee, Company company) {
        // нічого не робимо
    }
}
