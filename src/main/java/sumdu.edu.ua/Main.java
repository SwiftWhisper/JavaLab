package sumdu.edu.ua;

import java.util.Scanner;

public class Main {
    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);
        EmployeeFactory empFactory = new EmployeeFactory(input);

        Company company = new Company("Google");

        showMainMenu(company, input, empFactory);
    }

    private static void showMainMenu(Company company, ConsoleInput input, EmployeeFactory empFactory) {
        while (true) {
            System.out.println("Виберіть дію обравши її номер:");
            System.out.println("1. Додати нового співробітника.");
            System.out.println("2. Вивести інформацію про компанію.");
            System.out.println("3. Завершити програму.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");
            switch (opt) {
                case 1:
                    showEmpCreationMenu(company, input, empFactory);
                    break;
                case 2:
                    System.out.println(company);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }
        }
    }

    private static void showEmpCreationMenu(Company company, ConsoleInput input, EmployeeFactory empFactory) {
        while (true) {
            System.out.println("Виберіть тип співробітника якого ви будете додавати:");
            System.out.println("1. Full time employee.");
            System.out.println("2. ContractEmployee");
            System.out.println("3. PartTimeEmployee");
            System.out.println("4. InternEmployee");
            System.out.println("5. Відмінити створення співробітника.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");

            switch (opt) {
                case 1:
                    company.addEmployee(empFactory.createFullTimeEmployee());
                    System.out.println();
                    break;
                case 2:
                    company.addEmployee(empFactory.createContractEmployee());
                    System.out.println();
                    break;
                case 3:
                    company.addEmployee(empFactory.createPartTimeEmployee());
                    System.out.println();
                    break;
                case 4:
                    company.addEmployee(empFactory.createInternEmployee());
                    System.out.println();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }
        }
    }
}
