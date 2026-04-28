package sumdu.edu.ua.service;

import java.util.Scanner;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.CompareType;
import sumdu.edu.ua.model.Position;
import sumdu.edu.ua.validators.CompanyValidator;
import sumdu.edu.ua.validators.ContractEmployeeValidator;
import sumdu.edu.ua.validators.EmployeeValidator;
import sumdu.edu.ua.validators.FullTimeEmployeeValidator;
import sumdu.edu.ua.validators.InternEmployeeValidator;
import sumdu.edu.ua.validators.PartTimeEmployeeValidator;

public class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            System.out.println("Помилка: введіть ціле число.");
            scanner.nextLine();
        }
    }

    public double readDouble(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }
            System.out.println("Помилка: введіть число.");
            scanner.nextLine();
        }
    }

    public String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Помилка: рядок не може бути порожнім.");
        }
    }

    public String readValidCompanyName() {
        String message = "Введіть назву компанії: ";
        while (true) {
            String name = readNonEmptyString(message);
            try {
                CompanyValidator.validateCompanyName(name);
                return name;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readValidNameSurname(EmployeeValidator validator) {
        String message = "Введіть ім'я та прізвище співробітника: ";
        while (true) {
            String nameSurname = readNonEmptyString(message);
            try {
                validator.validateNameSurname(nameSurname);
                return nameSurname;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Position readPosition() {
        Position[] positions = Position.values();
        while (true) {
            System.out.println("Оберіть посаду:");

            for (int i = 0; i < positions.length; i++) {
                System.out.println((i + 1) + ". " + positions[i]);
            }

            int choice = readInt("Ваш вибір: ");

            if (choice >= 1 && choice <= positions.length) {
                return positions[choice - 1];
            }

            System.out.println("Помилка: некоректний вибір посади.");
        }
    }

    public CompareType readCompareType() {
        CompareType[] compareTypes = CompareType.values();
        while (true) {
            System.out.println("Оберіть тип порівняння:");

            for (int i = 0; i < compareTypes.length; i++) {
                System.out.println((i + 1) + ". " + compareTypes[i]);
            }

            int choice = readInt("Ваш вибір: ");

            if (choice >= 1 && choice <= compareTypes.length) {
                return compareTypes[choice - 1];
            }

            System.out.println("Помилка: некоректний тип порівняння.");
        }
    }

    public int readValidAge(EmployeeValidator validator) {
        String message = "Введіть вік співробітника: ";
        while (true) {
            int age = readInt(message);
            try {
                validator.validateAge(age);
                return age;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public double readValidSalary(EmployeeValidator validator) {
        String message = "Введіть заробітню плату для співробітника";
        while (true) {
            double salary = readDouble(message);
            try {
                validator.validateSalary(salary);
                return salary;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readValidYearsInCompany(FullTimeEmployeeValidator validator) {
        String message = "Введіть кількість років праці співробітника в компанії: ";
        while (true) {
            int years = readInt(message);
            try {
                validator.validateYearsInCompany(years);
                return years;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readValidDurationOfContract(ContractEmployeeValidator validator) {
        String message = "Введіть довжину контракту для співробітника: ";
        while (true) {
            int years = readInt(message);
            try {
                validator.validateDurationOfContract(years);
                return years;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readValidHoursInDay(PartTimeEmployeeValidator validator) {
        String message = "Введіть скільки в день буде працювати цей співробітник (мінімум 4): ";
        while (true) {
            int hours = readInt(message);
            try {
                validator.validateHoursInDay(hours);
                return hours;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readValidInternshipMonths(InternEmployeeValidator validator) {
        String message = "Введіть кількість місяців стажування: ";
        while (true) {
            int months = readInt(message);
            try {
                validator.validateInternshipMonths(months);
                return months;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readValidUniversity(InternEmployeeValidator validator) {
        String message = "Введіть назву університету стажера: ";
        while (true) {
            String university = readNonEmptyString(message);
            try {
                validator.validateUniversity(university);
                return university;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public EmployeeDto readFullTimeEmployeeDto() {
        FullTimeEmployeeValidator validator = new FullTimeEmployeeValidator();

        String nameSurname = readValidNameSurname(validator);
        int age = readValidAge(validator);
        double salary = readValidSalary(validator);
        Position position = readPosition();
        int yearsInCompany = readValidYearsInCompany(validator);

        return new EmployeeDto(
                "FULL_TIME",
                nameSurname,
                String.valueOf(age),
                String.valueOf(salary),
                position.name(),
                String.valueOf(yearsInCompany),
                null,
                null,
                null,
                null
        );
    }

    public EmployeeDto readContractEmployeeDto() {
        ContractEmployeeValidator validator = new ContractEmployeeValidator();

        String nameSurname = readValidNameSurname(validator);
        int age = readValidAge(validator);
        double salary = readValidSalary(validator);
        Position position = readPosition();
        int durationOfContract = readValidDurationOfContract(validator);

        return new EmployeeDto(
                "CONTRACT",
                nameSurname,
                String.valueOf(age),
                String.valueOf(salary),
                position.name(),
                null,
                String.valueOf(durationOfContract),
                null,
                null,
                null
        );
    }

    public EmployeeDto readPartTimeEmployeeDto() {
        PartTimeEmployeeValidator validator = new PartTimeEmployeeValidator();

        String nameSurname = readValidNameSurname(validator);
        int age = readValidAge(validator);
        double salary = readValidSalary(validator);
        Position position = readPosition();
        int hoursInDay = readValidHoursInDay(validator);

        return new EmployeeDto(
                "PART_TIME",
                nameSurname,
                String.valueOf(age),
                String.valueOf(salary),
                position.name(),
                null,
                null,
                String.valueOf(hoursInDay),
                null,
                null
        );
    }

    public EmployeeDto readInternEmployeeDto() {
        InternEmployeeValidator validator = new InternEmployeeValidator();

        String nameSurname = readValidNameSurname(validator);
        int age = readValidAge(validator);
        double salary = readValidSalary(validator);
        Position position = readPosition();
        String university = readValidUniversity(validator);
        int internshipMonths = readValidInternshipMonths(validator);

        return new EmployeeDto(
                "INTERN",
                nameSurname,
                String.valueOf(age),
                String.valueOf(salary),
                position.name(),
                null,
                null,
                null,
                String.valueOf(internshipMonths),
                university
        );
    }
}
