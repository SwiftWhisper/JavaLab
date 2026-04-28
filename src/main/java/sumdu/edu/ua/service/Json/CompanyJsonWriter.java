package sumdu.edu.ua.service.Json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.Employee;

public class CompanyJsonWriter {

    private final ObjectMapper objectMapper;

    public CompanyJsonWriter (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void saveToFile(String filePath, List<Company> companies) {
        ArrayNode root = objectMapper.createArrayNode();

        for (Company company : companies) {
            ObjectNode companyNode = objectMapper.createObjectNode();
            companyNode.put("name", company.getName());

            ArrayNode employeesArray = objectMapper.createArrayNode();

            for (Employee e : company.getEmployees()) {
                ObjectNode employeeNode = objectMapper.createObjectNode();
                e.toJson(employeeNode);
                employeesArray.add(employeeNode);
            }

            companyNode.set("employees", employeesArray);
            root.add(companyNode);
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), root);

            System.out.println("Дані успішно збережено у файл.");

        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}
