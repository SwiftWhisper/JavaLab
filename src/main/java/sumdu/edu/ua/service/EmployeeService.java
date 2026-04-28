package sumdu.edu.ua.service;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.repository.EmployeeRepository;

public class EmployeeService {
    private final EmployeeFactory factory;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeFactory factory, EmployeeRepository employeeRepository) {
        this.factory = factory;
        this.employeeRepository = employeeRepository;
        
    }

    public Employee createAndSaveEmployee(EmployeeDto dto, Company company) {
        Employee employee = factory.createEmployee(dto);
        company.addEmployee(employee);
        employeeRepository.save(employee, company);
        return employee;
    }
}
