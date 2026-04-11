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

        Company company = new Company("Google");

        showMainMenu(company, input);
    }

    private static void showMainMenu(Company company, ConsoleInput input) {
        while (true) {
            System.out.println("Виберіть дію обравши її номер:");
            System.out.println("1. Додати нового співробітника.");
            System.out.println("2. Вивести інформацію про компанію.");
            System.out.println("3. Завершити програму.");
            System.out.println();

            
            int opt = input.readInt("Ваш вибір: ");
            switch (opt) {
                case 1:
                    showEmpCreationMenu(company,input);
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

    private static void showEmpCreationMenu(Company company, ConsoleInput input) {
        while (true) {
            System.out.println("Виберіть тип співробітника якого ви будете додавати:");
            System.out.println("1. Full time employee.");
            System.out.println("2. ContractEmployee");
            System.out.println("3. Відмінити створення співробітника.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");

            switch (opt) {
                case 1:
                    company.addEmployee(createFullTimeEmp(input));
                    System.out.println();
                    break;
                case 2:
                    company.addEmployee(createContractEmp(input));
                    System.out.println();
                    break;
                case 3:
                    return;

                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }

        }
    }

    /**
     * Відповідає за створення співробітника.
     *
     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return створений співробітник
     */
    private static Employee createEmployee(ConsoleInput input) {
        String nameSurname = input.readValidNameSurname("Введіть ім'я та прізвище співробітника: ");
        int age = input.readValidAge("Введіть вік співробітника: ");
        double salary = input.readValidSalary("Введіть заробітню плату для співробітника: ");
        Position position = input.readPosition();

        return new Employee(nameSurname, age,salary, position); 
    }

    /**
     * Відповідає за створення контрактного співробітника.
     *
     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return створений співробітник
     */
    private static ContractEmployee createContractEmp(ConsoleInput input) {
        String nameSurname = input.readValidNameSurname("Введіть ім'я та прізвище співробітника: ");
        int age = input.readValidAge("Введіть вік співробітника: ");
        double salary = input.readValidSalary("Введіть заробітню плату для співробітника: ");
        Position position = input.readPosition();        
        int yearDurationOfContract = input.readValidDurationOfContract("Введіть довжину контракту для співробітника: ");

        return new ContractEmployee(nameSurname, age,salary, position, yearDurationOfContract); 
    }

    /**
     * Відповідає за створення штатного співробітника.
     *
     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return створений співробітник
     */
    private static FullTimeEmployee createFullTimeEmp(ConsoleInput input){
        String nameSurname = input.readValidNameSurname("Введіть ім'я та прізвище співробітника: ");
        int age = input.readValidAge("Введіть вік співробітника: ");
        double salary = input.readValidSalary("Введіть заробітню плату для співробітника: ");
        Position position = input.readPosition();
        int yearsInCompany = input.readValidYearsInCompany("Введіть кількькість років праці в компанії для співробітника: ");

        return new FullTimeEmployee(nameSurname, age,salary, position, yearsInCompany); 
    }

}
