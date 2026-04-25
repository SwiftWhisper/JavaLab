package sumdu.edu.ua.service.Json;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.service.EmployeeFactory;
import sumdu.edu.ua.validators.CompanyValidator;

public class CompanyJsonService {
    private final JsonEmployeeMapper mapper = new JsonEmployeeMapper();
    private final EmployeeFactory factory = new EmployeeFactory();
    private final CompanyValidator companyValidator = new CompanyValidator();

    public void loadCompanies(String filePath, List<Company> companies) {
        try {
            JsonNode root = JsonLoader.loadJson(filePath);
            if (root == null || !root.isArray()) {
                throw new IllegalArgumentException("Корінь JSON повинен бути масивом компаній.");
            }

            int companyIndex = 0;

            for (JsonNode companyNode : root) {
                companyIndex++;
                processCompanyNode(companyNode, companyIndex, companies);
            }

            System.out.println("Завантаження завершено.");

        } catch (Exception e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }

    private void processCompanyNode(JsonNode companyNode, int companyIndex, List<Company> companies) {
        try {
            String companyName = readCompanyName(companyNode);
            Company company = new Company(companyName);

            JsonNode employeesNode = companyNode.get("employees");

            if (employeesNode == null || !employeesNode.isArray()) {
                throw new IllegalArgumentException("Поле employees повинно бути масивом.");
            }

            int employeeIndex = 0;
            for (JsonNode employeeNode : employeesNode) {
                employeeIndex++;
                processEmployeeNode(employeeNode, employeeIndex, company);
            }

            companies.add(company);

        } catch (IllegalArgumentException e) {
            String companyName = companyNode.has("name")
                    ? companyNode.get("name").asText()
                    : "невідомо";

            System.out.println("Помилка у компанії #" + companyIndex + " (" + companyName + "): " + e.getMessage());
        }
    }

    private String readCompanyName(JsonNode companyNode) {
        if (!companyNode.has("name")) {
            throw new IllegalArgumentException("Відсутнє поле name.");
        }

        String companyName = companyNode.get("name").asText();
        companyValidator.validateName(companyName);
        return companyName;
    }

    private void processEmployeeNode(JsonNode employeeNode, int index, Company company) {
        try {
            EmployeeDto dto = mapper.map(employeeNode);
            Employee employee = factory.createEmployee(dto);

            company.addEmployee(employee);

        } catch (IllegalArgumentException e) {
            String employeeName = employeeNode.has("nameSurname")
                    ? employeeNode.get("nameSurname").asText()
                    : "невідомо";

            System.out.println(
                    "Помилка у працівнику #" + index +
                    " (" + employeeName + "): " + e.getMessage()
            );
        }
    }
}
