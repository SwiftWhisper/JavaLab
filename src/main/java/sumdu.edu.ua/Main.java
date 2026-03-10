package sumdu.edu.ua;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int empNum = readEmpNum(scanner);
        System.out.println();

        List<Employee> employees = new ArrayList<Employee>();

        for(int i = 0; i < empNum; i++){
            employees.add(createEmployee(scanner, i+1));
            System.out.println();
        }
        for(Employee e : employees){
            System.out.println(e);
        }
    }

    private static Employee createEmployee(Scanner scanner, int i){
        String nameSurname = readNameSurname(scanner, i);
        int age = readAge(scanner, i);
        double salary = readSalary(scanner, i);
        String position = readPosition(scanner, i);

        return new Employee(nameSurname, age,salary, position); 
    }

    private static int readEmpNum(Scanner scanner) {
        while (true) {
            System.out.print("Ведіть кількість співробітників яких потрібно додати: ");
            String input = scanner.nextLine();

            try {
                int num = Integer.parseInt(input);

                if (num <= 0) {
                    System.out.println("Помилка: має бути мінімум 1 співробітник.");
                    continue;
                }

                return num;
            }
            catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число!");
            }
        }
    }
    
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
