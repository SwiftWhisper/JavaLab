package sumdu.edu.ua;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ведіть кількість співробітників яких потрібно додати: ");
        int empNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        List<Employee> employees = new ArrayList<Employee>();

        for(int i = 0; i<empNum; i++){
            employees.add(createEmployee(scanner, i+1));
            System.out.println();
        }
        for(Employee e : employees){
            System.out.println(e);
        }
    }
    private static Employee createEmployee(Scanner scanner, int i){
        System.out.print("Введіть ім'я та прізвище для співробітника під номером " + i + ": ");
        String nameSurname = scanner.nextLine();

        System.out.print("Введіть заробітню плату для співробітника під номером " + i + ": ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Введіть позицію для співробітника під номером " + i + ": ");
        String position = scanner.nextLine();

        return new Employee(nameSurname, salary, position); 
    }
}
