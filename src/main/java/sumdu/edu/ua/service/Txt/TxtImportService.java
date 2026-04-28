package sumdu.edu.ua.service.Txt;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.service.CompanyService;
import sumdu.edu.ua.service.EmployeeService;
import sumdu.edu.ua.validators.CompanyValidator;

public class TxtImportService {
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final TxtEmployeeMapper mapper;

    public TxtImportService (EmployeeService employeeService, CompanyService companyService, TxtEmployeeMapper mapper) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.mapper = mapper;
    }

    public void loadCompanies(String filePath, List<Company> companies) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            Company currentCompany = null;

            for (String line : lines) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("COMPANY:")) {
                    String name = line.substring(8).trim();
                    CompanyValidator.validateCompanyName(name);
                    currentCompany = companyService.createAndSaveCompany(name, companies);
                    continue;
                }

                if (!line.startsWith("EMPLOYEE:")) {
                    System.out.println("Невідомий формат: " + line);
                    continue;
                }

                if (currentCompany == null) {
                    System.out.println("Працівник без компанії: " + line);
                    continue;
                }

                try {
                    String employeeData = line.substring(9).trim();
                    String[] parts = employeeData.split(";");

                    EmployeeDto dto = mapper.map(parts);
                    employeeService.createAndSaveEmployee(dto, currentCompany);
                } catch (Exception e) {
                    System.out.println("Помилка в рядку: " + line);
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Помилка TXT: " + e.getMessage());
        }
    }
}
