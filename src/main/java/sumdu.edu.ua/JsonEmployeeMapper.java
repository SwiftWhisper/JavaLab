package sumdu.edu.ua;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonEmployeeMapper {

    public EmployeeDto map(JsonNode node) {
        return new EmployeeDto(
                getTextOrNull(node, "type"),
                getTextOrNull(node, "nameSurname"),
                getTextOrNull(node, "age"),
                getTextOrNull(node, "salary"),
                getTextOrNull(node, "position"),
                getTextOrNull(node, "yearsInCompany"),
                getTextOrNull(node, "durationOfContract"),
                getTextOrNull(node, "hoursInDay"),
                getTextOrNull(node, "internshipMonths"),
                getTextOrNull(node, "university")
        );
    }

    private String getTextOrNull(JsonNode node, String fieldName) {
        JsonNode valueNode = node.get(fieldName);

        if (valueNode == null || valueNode.isNull()) {
            return null;
        }

        if (!valueNode.isValueNode()) {
            return null;
        }

        String value = valueNode.asText();

        if (value == null) {
            return null;
        }

        value = value.trim();

        if (value.isEmpty()) {
            return null;
        }

        return value;
    }
}
