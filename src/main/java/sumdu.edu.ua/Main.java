package sumdu.edu.ua;

import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

public class Main {
    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);
        EmployeeFactory empFactory = new EmployeeFactory();

        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Google"));

        showMainMenu(companies, input, empFactory);
    }

    private static void showMainMenu(List<Company> companies, ConsoleInput input, EmployeeFactory empFactory) {
        CompanyValidator companyValidator = new CompanyValidator();

        while (true) {
            System.out.println("Виберіть дію обравши її номер:");
            System.out.println("1. Вивести всі компанії.");
            System.out.println("2. Створити нову компанію.");
            System.out.println("3. Додати співробітників в конкретну компанію.");
            System.out.println("4. Зчитати інформацію про компанії з json.");
            System.out.println("5. Завершити програму.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");
            switch (opt) {
                case 1:
                    System.out.println(companies);
                    break;
                case 2:
                    String name = input.readValidName(companyValidator);
                    Company newCompany = new Company(name);
                    companies.add(newCompany);

                    System.out.println("Створення співробітників у нову компанію.");
                    showEmpCreationMenu(newCompany, input, empFactory);
                    break;
                case 3:
                    System.out.println(companies);
                    int id = input.readInt("Введіть ID компанії: ");
                    Company selectedCompany = CompanyUtils.findById(companies, id);
                    if (selectedCompany != null) {
                    showEmpCreationMenu(selectedCompany, input, empFactory);
                    }
                    else {
                        System.out.println("Компанії з таким ID немає.");
                    }
                    break;
                case 4:
                    CompanyJsonService companyJsonService = new CompanyJsonService();
                    companyJsonService.loadCompanies("companies.json", companies);
                    break;
                case 5:
                    CompaniesJsonWriter writer = new CompaniesJsonWriter(); 
                    writer.saveToFile("outputCompanies.json", companies);
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
                case 1: {
                    EmployeeDto dto = input.readFullTimeEmployeeDto();
                    company.addEmployee(empFactory.createEmployee(dto));
                    System.out.println();
                    break;
                }
                case 2: {
                    EmployeeDto dto = input.readContractEmployeeDto();
                    company.addEmployee(empFactory.createEmployee(dto));
                    System.out.println();
                    break;
                }
                case 3: {
                    EmployeeDto dto = input.readPartTimeEmployeeDto();
                    company.addEmployee(empFactory.createEmployee(dto));
                    System.out.println();
                    break;
                }
                case 4: {
                    EmployeeDto dto = input.readInternEmployeeDto();
                    company.addEmployee(empFactory.createEmployee(dto));
                    System.out.println();
                    break;
                }
                case 5:
                    return;
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }
        }
    }
}
