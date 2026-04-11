package sumdu.edu.ua;

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

    /**
     * Відповідає за зчитування і валідацію імені та прізвища співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return коректне ім'я та прізвище співробітника
     */
    public String readValidNameSurname(String message) {
        while (true) {
            String nameSurname = readNonEmptyString(message);
            try {
                Employee.validateNameSurname(nameSurname);
                return nameSurname;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Відповідає за зчитування і валідацію посади співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return коректна посада співробітника
     */
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

    public int readValidAge(String message) {
        while (true) {
            int age = readInt(message);
            try {
                Employee.validateAge(age);
                return age;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public double readValidSalary(String message) {
        while (true) {
            double salary = readDouble(message);
            try {
                Employee.validateSalary(salary);
                return salary;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readValidYearsInCompany(String message) {
        while (true) {
            int years = readInt(message);
            try {
                FullTimeEmployee.validateYearsInCompany(years);
                return years;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Відповідає за зчитування і валідацію терміну дії контракту співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return кількість років дії контракту
     */
    public int readValidDurationOfContract(String message) {
        while (true) {
            int years = readInt(message);
            try {
                ContractEmployee.validateDurationOfContract(years);
                return years;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
