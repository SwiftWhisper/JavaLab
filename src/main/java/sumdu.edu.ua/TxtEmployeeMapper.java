package sumdu.edu.ua;

public class TxtEmployeeMapper {

    public EmployeeDto map(String[] parts) {
        String type = getRequiredPart(parts, 0, "type");

        String nameSurname = getRequiredPart(parts, 1, "nameSurname");
        String age = getRequiredPart(parts, 2, "age");
        String salary = getRequiredPart(parts, 3, "salary");
        String position = getRequiredPart(parts, 4, "position");

        switch (type.toUpperCase()) {
            case "FULL_TIME":
                return new EmployeeDto(
                        type,
                        nameSurname,
                        age,
                        salary,
                        position,
                        getRequiredPart(parts, 5, "yearsInCompany"),
                        null,
                        null,
                        null,
                        null
                );

            case "CONTRACT":
                return new EmployeeDto(
                        type,
                        nameSurname,
                        age,
                        salary,
                        position,
                        null,
                        getRequiredPart(parts, 5, "durationOfContract"),
                        null,
                        null,
                        null
                );

            case "PART_TIME":
                return new EmployeeDto(
                        type,
                        nameSurname,
                        age,
                        salary,
                        position,
                        null,
                        null,
                        getRequiredPart(parts, 5, "hoursInDay"),
                        null,
                        null
                );

            case "INTERN":
                return new EmployeeDto(
                        type,
                        nameSurname,
                        age,
                        salary,
                        position,
                        null,
                        null,
                        null,
                        getRequiredPart(parts, 5, "internshipMonths"),
                        getRequiredPart(parts, 6, "university")
                );

            default:
                throw new IllegalArgumentException("Помилка TXT: невідомий тип працівника '" + type + "'.");
        }
    }

    private String getRequiredPart(String[] parts, int index, String fieldName) {
        if (index >= parts.length) {
            throw new IllegalArgumentException("Помилка TXT: відсутнє поле '" + fieldName + "'.");
        }

        String value = parts[index].trim();

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Помилка TXT: поле '" + fieldName + "' не може бути порожнім.");
        }

        return value;
    }
}
