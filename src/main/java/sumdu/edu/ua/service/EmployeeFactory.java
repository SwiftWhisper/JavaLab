package sumdu.edu.ua.service;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.ContractEmployee;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.model.EmployeeType;
import sumdu.edu.ua.model.FullTimeEmployee;
import sumdu.edu.ua.model.InternEmployee;
import sumdu.edu.ua.model.PartTimeEmployee;
import sumdu.edu.ua.model.Position;
import sumdu.edu.ua.validators.ContractEmployeeValidator;
import sumdu.edu.ua.validators.FullTimeEmployeeValidator;
import sumdu.edu.ua.validators.InternEmployeeValidator;
import sumdu.edu.ua.validators.PartTimeEmployeeValidator;

/**
 * Клас-фабрика для створення об'єктів співробітників.
 *
 * Використовує ConsoleInput для зчитування та валідації даних,
 * введених користувачем, після чого створює відповідні екземпляри
 * класів Employee та його похідних.
 */
public class EmployeeFactory {

    public Employee createEmployee(EmployeeDto dto) {
        EmployeeType type = parseEmployeeType(dto.getType());

        return switch (type) {
            case FULL_TIME -> createFullTimeEmployee(dto);
            case CONTRACT -> createContractEmployee(dto);
            case PART_TIME -> createPartTimeEmployee(dto);
            case INTERN -> createInternEmployee(dto);
        };
    }

    /**
     * Створює штатного співробітника (FullTimeEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * кількість років роботи в компанії.
     *
     * @return створений штатний співробітник
     */
    public FullTimeEmployee createFullTimeEmployee(EmployeeDto dto) {
        FullTimeEmployeeValidator validator = new FullTimeEmployeeValidator();

        String name = dto.getNameSurname();
        int age = parseInt(dto.getAge(), "age");
        double salary = parseDouble(dto.getSalary(), "salary");
        Position position = parsePosition(dto.getPosition());
        int years = parseInt(dto.getYearsInCompany(), "yearsInCompany");

        validator.validateNameSurname(name);
        validator.validateAge(age);
        validator.validateSalary(salary);
        validator.validateYearsInCompany(years);

        return new FullTimeEmployee(name, age, salary, position, years);
    }

    /**
     * Створює контрактного співробітника (ContractEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * тривалість контракту.
     *
     * @return створений контрактний співробітник
     */
    public ContractEmployee createContractEmployee(EmployeeDto dto) {
        ContractEmployeeValidator validator = new ContractEmployeeValidator();

        String name = dto.getNameSurname();
        int age = parseInt(dto.getAge(), "age");
        double salary = parseDouble(dto.getSalary(), "salary");
        Position position = parsePosition(dto.getPosition());
        int duration = parseInt(dto.getDurationOfContract(), "durationOfContract");

        validator.validateNameSurname(name);
        validator.validateAge(age);
        validator.validateSalary(salary);
        validator.validateDurationOfContract(duration);

        return new ContractEmployee(name, age, salary, position, duration);
    }

    /**
     * Створює працівника з неповною зайнятістю (PartTimeEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * кількість робочих годин на день.
     *
     * @return створений працівник з неповною зайнятістю
     */
    public PartTimeEmployee createPartTimeEmployee(EmployeeDto dto) {
        PartTimeEmployeeValidator validator = new PartTimeEmployeeValidator();

        String name = dto.getNameSurname();
        int age = parseInt(dto.getAge(), "age");
        double salary = parseDouble(dto.getSalary(), "salary");
        Position position = parsePosition(dto.getPosition());
        int hours = parseInt(dto.getHoursInDay(), "hoursInDay");

        validator.validateNameSurname(name);
        validator.validateAge(age);
        validator.validateSalary(salary);
        validator.validateHoursInDay(hours);

        return new PartTimeEmployee(name, age, salary, position, hours);
    }
    /**
     * Створює стажера (InternEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * назву університету і тривалість стажування.
     *
     * @return створений стажер
     */
    public InternEmployee createInternEmployee(EmployeeDto dto) {
        InternEmployeeValidator validator = new InternEmployeeValidator();

        String name = dto.getNameSurname();
        int age = parseInt(dto.getAge(), "age");
        double salary = parseDouble(dto.getSalary(), "salary");
        Position position = parsePosition(dto.getPosition());
        String university = dto.getUniversity();
        int months = parseInt(dto.getInternshipMonths(), "internshipMonths");

        validator.validateNameSurname(name);
        validator.validateAge(age);
        validator.validateSalary(salary);
        validator.validateUniversity(university);
        validator.validateInternshipMonths(months);

        return new InternEmployee(name, age, salary, position, university, months);
    }
    // ========================
    // Parsing methods
    // ========================

    private int parseInt(String value, String fieldName) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Поле '" + fieldName + "' повинно бути цілим числом.");
        }
    }

    private double parseDouble(String value, String fieldName) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Поле '" + fieldName + "' повинно бути числом.");
        }
    }

    private EmployeeType parseEmployeeType(String value) {
        try {
            return EmployeeType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Некоректний тип працівника: " + value);
        }
    }

    private Position parsePosition(String value) {
        try {
            return Position.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Некоректна посада: " + value);
        }
    }
}
