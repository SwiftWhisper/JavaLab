package sumdu.edu.ua.app;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.console.ConsoleChooser;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.ContractEmployee;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.model.FullTimeEmployee;
import sumdu.edu.ua.model.InternEmployee;
import sumdu.edu.ua.model.PartTimeEmployee;
import sumdu.edu.ua.service.ConsoleInput;
import sumdu.edu.ua.service.EmployeeService;
import sumdu.edu.ua.validators.ContractEmployeeValidator;
import sumdu.edu.ua.validators.EmployeeValidator;
import sumdu.edu.ua.validators.FullTimeEmployeeValidator;
import sumdu.edu.ua.validators.InternEmployeeValidator;
import sumdu.edu.ua.validators.PartTimeEmployeeValidator;

public class EmployeeUpdateMenu {
    private final ConsoleInput input;
    private final EmployeeService employeeService;
    private final ConsoleChooser consoleChooser;
    public EmployeeUpdateMenu(ConsoleInput input, EmployeeService employeeService, ConsoleChooser consoleChooser) {
        this.input = input;
        this.employeeService = employeeService;
        this.consoleChooser = consoleChooser;
    }

    public void updateEmployee(Company company) {

        System.out.println("Оберіть співробітника для зміни:");
        Employee existingEmployee = consoleChooser.chooseEmployee(company);
        if (existingEmployee == null) {
            return;
        }

        EmployeeDto dto = EmployeeDto.fromEmployee(existingEmployee);

        while (true) {
            printUpdateEmployeeMenu(existingEmployee);

            int fieldChoice = input.readInt("Ваш вибір: ");

            if (fieldChoice == 0) {
                break;
            }
            EmployeeValidator employeeValidator = getValidatorForEmployee(existingEmployee);
            switch (fieldChoice) {
                case 1:
                    dto.setNameSurname(input.readValidNameSurname(employeeValidator));
                    break;

                case 2:
                    dto.setAge(String.valueOf(input.readValidAge(employeeValidator)));
                    break;

                case 3:
                    dto.setSalary(String.valueOf(input.readValidSalary(employeeValidator)));
                    break;

                case 4:
                    dto.setPosition(input.readPosition().name());
                    break;

                case 5:
                    updateSpecificField(existingEmployee, dto, input);
                    break;
                default:
                    System.out.println("Неправильний вибір атрибута.");
            }
        }

        Employee newEmployee;

        try {
            newEmployee = employeeService.createEmployee(dto);
        } catch (IllegalArgumentException e) {
            System.out.println("Не вдалося створити оновленого співробітника:");
            System.out.println(e.getMessage());
            return;
        }

        boolean updated = company.updateEmployee(existingEmployee, newEmployee);

        if (updated) {
            System.out.println("Співробітника успішно оновлено.");
        } else {
            System.out.println("Не вдалося оновити співробітника.");
        }
    }

    private void updateSpecificField(
            Employee existingEmployee,
            EmployeeDto dto,
            ConsoleInput input
    ) {
        if (existingEmployee instanceof FullTimeEmployee) {
            FullTimeEmployeeValidator validator = new FullTimeEmployeeValidator();

            dto.setYearsInCompany(
                    String.valueOf(input.readValidYearsInCompany(validator))
            );

        } else if (existingEmployee instanceof ContractEmployee) {
            ContractEmployeeValidator validator = new ContractEmployeeValidator();

            dto.setDurationOfContract(
                    String.valueOf(input.readValidDurationOfContract(validator))
            );

        } else if (existingEmployee instanceof PartTimeEmployee) {
            PartTimeEmployeeValidator validator = new PartTimeEmployeeValidator();

            dto.setHoursInDay(
                    String.valueOf(input.readValidHoursInDay(validator))
            );

        } else if (existingEmployee instanceof InternEmployee) {
            updateInternSpecificFields(dto, input);
        }
        return;
    }

    private void updateInternSpecificFields(EmployeeDto dto, ConsoleInput input) {
        InternEmployeeValidator validator = new InternEmployeeValidator();

        while (true) {
            System.out.println("\nСпецифічні поля стажера:");
            System.out.println("1. Університет");
            System.out.println("2. Місяців стажування");
            System.out.println("0. Назад");

            int choice = input.readInt("Ваш вибір: ");

            switch (choice) {
                case 1:
                    dto.setUniversity(input.readValidUniversity(validator));
                    break;

                case 2:
                    dto.setInternshipMonths(
                            String.valueOf(input.readValidInternshipMonths(validator))
                    );
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Неправильний вибір.");
            }
        }
    }

    private EmployeeValidator getValidatorForEmployee(Employee employee) {
        if (employee instanceof FullTimeEmployee) {
            return new FullTimeEmployeeValidator();
        }

        if (employee instanceof ContractEmployee) {
            return new ContractEmployeeValidator();
        }

        if (employee instanceof PartTimeEmployee) {
            return new PartTimeEmployeeValidator();
        }

        if (employee instanceof InternEmployee) {
            return new InternEmployeeValidator();
        }

        throw new IllegalArgumentException("Невідомий тип співробітника.");
    }

    private void printUpdateEmployeeMenu(Employee employee) {
        System.out.println("\nОберіть атрибут для зміни:");

        System.out.println("1. ПІБ");
        System.out.println("2. Вік");
        System.out.println("3. Зарплата");
        System.out.println("4. Посада");
        System.out.println("5. Спецефічні поля типу співробітника");
        System.out.println("0. Завершити редагування");
    }
}
