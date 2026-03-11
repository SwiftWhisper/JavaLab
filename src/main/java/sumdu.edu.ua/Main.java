package sumdu.edu.ua;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        List<Employee> employees = new ArrayList<Employee>();

        while (true) {
            int opt = readOpt(scanner);
            switch (opt) {
                case 1:
                    employees.add(createEmployee(scanner, employees.size() + 1));
                    System.out.println();

                    break;

                case 2:
                    if (employees.isEmpty()){ 
                        System.out.println("Список співробітників порожній.");
                        System.out.println();
                    } else{
                        for(Employee e : employees) {System.out.println(e);}
                        System.out.println();
                    }
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
    private static Employee createEmployee(Scanner scanner, int i){
        String nameSurname = readNameSurname(scanner, i);
        int age = readAge(scanner, i);
        double salary = readSalary(scanner, i);
        String position = readPosition(scanner, i);

        return new Employee(nameSurname, age,salary, position); 
    }

    /**
     * Відповідає за зчитування і валідацію вибора користувача у меню.

     * @param scanner об'єкт для читання інформації від користувача
     * @return вибрана користувачем опція меню
     */
    private static int readOpt(Scanner scanner) {
        while (true) {
            System.out.println("Виберіть дію обравши її номер:");
            System.out.println("1. Додати нового співробітника.");
            System.out.println("2. Вивести усіх співробітників.");
            System.out.println("3. Завершити програму.");
            System.out.println();

            String input = scanner.nextLine();

            try {
                int num = Integer.parseInt(input);

                return num;
            }
            catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число!");
            }
        }
    }
    
    /**
     * Відповідає за зчитування і валідацію віку співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return коректний вік співробітника
     */
    private static int readAge(Scanner scanner, int i) {
        while (true) {
            System.out.print("Введіть вік співробітника під номером " + i + ": ");
            String input = scanner.nextLine();

            try {
                int age = Integer.parseInt(input);

                if (age < 18) {
                    System.out.println("Співробітник має бути повнолітнім!");
                    continue;
                }

                return age;
            }
            catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число!");
            }
        }
    }

    /**
     * Відповідає за зчитування і валідацію заробітної плати співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return коректні заробітну плату співробітника
     */
    private static double readSalary(Scanner scanner, int i) {
        while (true) {
            System.out.print("Введіть заробітню плату для співробітника під номером " + i + ": ");
            String input = scanner.nextLine();

            try {
                double salary = Double.parseDouble(input);

                if (salary < 0) {
                    System.out.println("Помилка: заробітня плата не може бути від'ємною!");
                    continue;
                }

                return salary;
            }
            catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести число!");
            }
        }
    }

    /**
     * Відповідає за зчитування і валідацію імені та прізвища співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return коректне ім'я та прізвище співробітника
     */
    private static String readNameSurname(Scanner scanner, int i) {
        while (true) {
            System.out.print("Введіть ім'я та прізвище для співробітника під номером " + i + ": ");
            String input = scanner.nextLine().trim();

            if(input.isEmpty()) {
                System.out.println("Помилка: ви не ввели ім'я та прізвище співробітника.");
                continue;
            }

            if (!input.matches("[a-zA-Zа-яА-ЯіїєІЇЄ'\\- ]+")) {
                System.out.println("Помилка: ім'я може містити тільки букви.");
                continue;
            }

            return input;
        }
    }

    /**
     * Відповідає за зчитування і валідацію посади співробітника.

     * @param scanner об'єкт для читання інформації від користувача
     * @param i номер співробітника масиву, який створюється
     * @return коректна посада співробітника
     */
    private static String readPosition(Scanner scanner, int i) {
        while (true) {
            System.out.print("Введіть посаду для співробітника під номером " + i + ": ");
            String input = scanner.nextLine().trim();

            if(input.isEmpty()) {
                System.out.println("Помилка: ви не ввели посаду співробітника.");
                continue;
            }

            return input;
        }
    }
 
}
