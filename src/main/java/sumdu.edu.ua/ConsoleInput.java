package sumdu.edu.ua;

import sumdu.edu.ua.validators.*;
import java.util.Scanner;

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

    public String readValidNameSurname(String message, EmployeeValidator validator) {
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

    public int readValidAge(String message, EmployeeValidator validator) {
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

    public double readValidSalary(String message, EmployeeValidator validator) {
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

    public int readValidYearsInCompany(String message, FullTimeEmployeeValidator validator) {
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

    public int readValidDurationOfContract(String message, ContractEmployeeValidator validator) {
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

    public int readValidHoursInDay(String message, PartTimeEmployeeValidator validator) {
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

    public int readValidInternshipMonths(String message, InternEmployeeValidator validator) {
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

    public String readValidUniversity(String message, InternEmployeeValidator validator) {
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

    public EmployeeBaseData readBaseEmployeeData(EmployeeValidator validator) {
        String nameSurname = readValidNameSurname("Введіть ім'я та прізвище співробітника: ", validator);
        int age = readValidAge("Введіть вік співробітника: ", validator);
        double salary = readValidSalary("Введіть заробітну плату для співробітника: ", validator);
        Position position = readPosition();

        return new EmployeeBaseData(nameSurname, age, salary, position);
    }
}
