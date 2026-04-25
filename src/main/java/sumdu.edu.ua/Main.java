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
        Company selectedCompany;
        while (true) {
            System.out.println("Виберіть дію обравши її номер:");
            System.out.println("1. Вивести всі компанії.");
            System.out.println("2. Створити нову компанію.");
            System.out.println("3. Додати співробітників в конкретну компанію.");
            System.out.println("4. Пошук співробітників.");
            System.out.println("5. Зчитати інформацію про компанії з txt.");
            System.out.println("6. Зчитати інформацію про компанії з json.");
            System.out.println("7. Завершити програму.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");
            switch (opt) {
                case 1:
                    if (companies.isEmpty()) {
                        System.out.println("Список компаній порожній.");
                    } else {
                        System.out.println(companies);
                    }
                    break;
                case 2:
                    String name = input.readValidName(companyValidator);
                    Company newCompany = new Company(name);
                    companies.add(newCompany);

                    System.out.println("Створення співробітників у нову компанію.");
                    showEmpCreationMenu(newCompany, input, empFactory);
                    break;
                case 3:
                    selectedCompany = chooseCompany(companies, input);
                    if (selectedCompany != null) {
                        showEmpCreationMenu(selectedCompany, input, empFactory);
                    }
                    break;
                case 4:
                    selectedCompany = chooseCompany(companies, input);
                    if (selectedCompany != null) {
                        showEmpSearchCriteriaMenu(selectedCompany, input);
                    }
                    break;
                case 5:
                    TxtImportService txtImportService = new TxtImportService();
                    txtImportService.loadCompanies("companies.txt", companies);
                    break;
                case 6:
                    CompanyJsonService companyJsonService = new CompanyJsonService();
                    companyJsonService.loadCompanies("companies.json", companies);
                    break;
                case 7:
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

    private static void showEmpSearchCriteriaMenu(Company company, ConsoleInput input) {
        List<Employee> searchResult = new ArrayList<Employee>();
        while (true) {
            System.out.println("Виберіть критерій пошуку співробітників:");
            System.out.println("1. Знайти співробітнів за ім'ям.");
            System.out.println("2. Знайти співробітників за посадою");
            System.out.println("3. Знайти співробітників за віком");
            System.out.println("4. Відмінити пошук");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");

            switch (opt) {
                case 1: {
                    String searchedNameSurname = input.readNonEmptyString("Введіть ім'я або його частину для пошуку: ");
                    searchResult = searchByNameSurname(searchedNameSurname, company);
                    break;
                }
                case 2: {
                    Position searchedPosition = input.readPosition();
                    searchResult = searchByPosition(searchedPosition, company);
                    break;
                }
                case 3: {
                    CompareType compareType = input.readCompareType();
                    int searchedAge = input.readInt("Введіть значення віку для порівняння: ");
                    searchResult = searchByAge(searchedAge, compareType, company);
                    break;
                }
                case 4:{
                    return;
                }
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }
            if (searchResult.isEmpty()) {
                System.out.println("Співробітників, які відповідають заданому критерії немає у цій компанії.");
            } else {
                System.out.println("Знайдені співробітники: ");

                for (Employee e : searchResult) {
                    System.out.println("  - " + e);
                }
            }
            System.out.println();
        }
    }

    public static List<Employee> searchByNameSurname(String nameSurname, Company company) {
        List<Employee> result = new ArrayList<>();
        List<Employee> employees = company.getEmployees();
        String searchedNameSurname = nameSurname.toLowerCase();

        for(Employee e : employees) {
            if (e.getNameSurname().toLowerCase().contains(searchedNameSurname)) {
                result.add(e);
            }
        }
        return result;
    }

    public static List<Employee> searchByPosition(Position searchedPosition, Company company) {
        List<Employee> result = new ArrayList<>();
        List<Employee> employees = company.getEmployees();

        for(Employee e : employees) {
            if (e.getPosition().equals(searchedPosition)) {
                result.add(e);
            }
        }
        return result;
    }

    public static List<Employee> searchByAge(int searchedAge, CompareType type, Company company) {
        List<Employee> result = new ArrayList<>();
        List<Employee> employees = company.getEmployees();

        for(Employee e : employees) {
            switch (type) {
                case GREATER:
                    if (e.getAge() > searchedAge) result.add(e);
                    break;

                case LESS:
                    if (e.getAge() < searchedAge) result.add(e);
                    break;

                case EQUAL:
                    if (e.getAge() == searchedAge) result.add(e);
                    break;
            }
        }
        return result;
    }

    private static Company chooseCompany(List<Company> companies, ConsoleInput input) {
        if (companies.isEmpty()) {
            System.out.println("Список компаній порожній.");
            return null;
        } else {
            System.out.println(companies);
            int id = input.readInt("Введіть ID компанії, щоб обрати її: ");
            Company selectedCompany = CompanyUtils.findById(companies, id);

            if (selectedCompany != null) {
                return selectedCompany;
            } else {
                System.out.println("Компанії з таким ID немає.");
                return null;
            }
        }
    }

}
