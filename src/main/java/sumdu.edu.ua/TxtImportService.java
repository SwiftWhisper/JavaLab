package sumdu.edu.ua;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class TxtImportService {
    private final CompanyValidator companyValidator = new CompanyValidator();

    public void loadCompanies(String filePath, List<Company> companies) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            TxtEmployeeMapper mapper = new TxtEmployeeMapper();
            EmployeeFactory factory = new EmployeeFactory();

            Company currentCompany = null;

            for (String line : lines) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.startsWith("COMPANY:")) {
                    String name = line.substring(8).trim();
                    companyValidator.validateName(name);
                    currentCompany = new Company(name);
                    companies.add(currentCompany);
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
                    Employee emp = factory.createEmployee(dto);

                    currentCompany.addEmployee(emp);

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
