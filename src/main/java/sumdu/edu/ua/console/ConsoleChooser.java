package sumdu.edu.ua.console;

import java.util.List;

import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.service.CompanyUtils;
import sumdu.edu.ua.service.ConsoleInput;

public class ConsoleChooser {
    private final ConsoleInput input;

    public ConsoleChooser(ConsoleInput input) {
        this.input = input;
    }

    public Company chooseCompany(List<Company> companies) {
        if (companies.isEmpty()) {
            System.out.println("Список компаній порожній.");
            return null;
        }

        for (Company company : companies) {
            System.out.println(company);
        }

        int id = input.readInt("Введіть ID компанії, щоб обрати її: ");
        Company selectedCompany = CompanyUtils.findById(companies, id);

        if (selectedCompany != null) {
            return selectedCompany;
        }

        System.out.println("Компанії з таким ID немає.");
        return null;
    }

    public Employee chooseEmployee(Company company) {
        List<Employee> employees = company.getEmployees();

        if (employees.isEmpty()) {
            System.out.println("Список співробітників порожній.");
            return null;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        int id = input.readInt("Введіть ID співробітника, щоб обрати його: ");
        Employee selectedEmployee = company.findEmployeeById(id);

        if (selectedEmployee != null) {
            return selectedEmployee;
        }

        System.out.println("Співробітника з таким ID немає.");
        return null;
    }
}
