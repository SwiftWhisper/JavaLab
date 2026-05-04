package sumdu.edu.ua.gui;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.EmployeeType;
import sumdu.edu.ua.model.Position;

public class GuiEmployeeMapper {

    private GuiEmployeeMapper() {
    }

    public static EmployeeDto map(
            EmployeeType type,
            String nameSurname,
            String age,
            String salary,
            Position position,
            String extra1,
            String extra2
    ) {
        return new EmployeeDto(
                safe(type),
                safe(nameSurname),
                safe(age),
                safe(salary),
                safe(position),
                mapYearsInCompany(type, extra1),
                mapDurationOfContract(type, extra1),
                mapHoursInDay(type, extra1),
                mapInternshipMonths(type, extra2),
                mapUniversity(type, extra1)
        );
    }

    private static String mapYearsInCompany(EmployeeType type, String value) {
        if (type == EmployeeType.FULL_TIME) {
            return safe(value);
        }

        return null;
    }

    private static String mapDurationOfContract(EmployeeType type, String value) {
        if (type == EmployeeType.CONTRACT) {
            return safe(value);
        }

        return null;
    }

    private static String mapHoursInDay(EmployeeType type, String value) {
        if (type == EmployeeType.PART_TIME) {
            return safe(value);
        }

        return null;
    }

    private static String mapInternshipMonths(EmployeeType type, String value) {
        if (type == EmployeeType.INTERN) {
            return safe(value);
        }

        return null;
    }

    private static String mapUniversity(EmployeeType type, String value) {
        if (type == EmployeeType.INTERN) {
            return safe(value);
        }

        return null;
    }

    private static String safe(Object value) {
        if (value == null) {
            return null;
        }

        String text = value.toString().trim();

        if (text.isEmpty()) {
            return null;
        }

        return text;
    }
}
