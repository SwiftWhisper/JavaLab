package sumdu.edu.ua;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

public class CompaniesJsonWriter {

    public void saveToFile(String filePath, List<Company> companies) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode root = mapper.createArrayNode();

        for (Company company : companies) {
            ObjectNode companyNode = mapper.createObjectNode();
            companyNode.put("name", company.getName());

            ArrayNode employeesArray = mapper.createArrayNode();

            for (Employee e : company.getEmployees()) {
                ObjectNode employeeNode = mapper.createObjectNode();
                e.toJson(employeeNode);
                employeesArray.add(employeeNode);
            }

            companyNode.set("employees", employeesArray);
            root.add(companyNode);
        }
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), root);

            System.out.println("Дані успішно збережено у файл.");

        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}
