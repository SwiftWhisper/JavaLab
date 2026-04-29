package sumdu.edu.ua.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sumdu.edu.ua.EmployeeDto;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.model.CompareType;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.model.Position;
import sumdu.edu.ua.service.CompanyUtils;
import sumdu.edu.ua.service.ConsoleInput;
import sumdu.edu.ua.service.EmployeeComparators;
import sumdu.edu.ua.service.EmployeeService;

public class Main {
    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args){
        AppBootstrap bootstrap = new AppBootstrap();
        AppContext app = bootstrap.initInMemory();

        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);


        List<Company> companies = new ArrayList<>();

        showMainMenu(companies, input, app);
    }

    private static void showMainMenu(List<Company> companies, ConsoleInput input, AppContext app) {
        Company selectedCompany;
        while (true) {
            System.out.println("Виберіть дію обравши її номер:");
            System.out.println("1. Вивести всі компанії.");
            System.out.println("2. Вивести компанію з відсортованими співробітниками."); 
            System.out.println("3. Створити нову компанію.");
            System.out.println("4. Додати співробітників в конкретну компанію.");
            System.out.println("5. Пошук співробітників.");
            System.out.println("6. Зчитати інформацію про компанії з txt.");
            System.out.println("7. Зчитати інформацію про компанії з json.");
            System.out.println("8. Завершити програму.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");
            switch (opt) {
                case 1:
                    if (companies.isEmpty()) {
                        System.out.println("Список компаній порожній.");
                    } else {
                        for (Company company : companies) {
                            System.out.println(company);
                        }
                    }
                    break;
                case 2:
                    selectedCompany = chooseCompany(companies, input);
                    if (selectedCompany != null) {
                    showEmpSortCriteriaMenu(selectedCompany, input);
                    }
                    break;                   
                case 3:
                    String companyName = input.readValidCompanyName();
                    Company newCompany = app.companyService.createAndSaveCompany(companyName, companies);

                    System.out.println("Створення співробітників у нову компанію.");
                    showEmpCreationMenu(newCompany, input, app.employeeService);
                    break;
                case 4:
                    selectedCompany = chooseCompany(companies, input);
                    if (selectedCompany != null) {
                        showEmpCreationMenu(selectedCompany, input, app.employeeService);
                    }
                    break;
                case 5:
                    selectedCompany = chooseCompany(companies, input);
                    if (selectedCompany != null) {
                        showEmpSearchCriteriaMenu(selectedCompany, input);
                    }
                    break;
                case 6:
                    app.txtImportService.loadCompanies("companies.txt", companies);
                    break;
                case 7:
                    app.companyJsonService.loadCompanies("companies.json", companies);
                    break;
                case 8:
                    app.companyJsonWriter.saveToFile("outputCompanies.json", companies);
                    return;
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }
        }
    }

    private static void showEmpCreationMenu(Company company, ConsoleInput input, EmployeeService employeeService) {
        while (true) {
            System.out.println("Виберіть тип співробітника якого ви будете додавати:");
            System.out.println("1. Full time employee.");
            System.out.println("2. ContractEmployee");
            System.out.println("3. PartTimeEmployee");
            System.out.println("4. InternEmployee");
            System.out.println("5. Відмінити створення співробітника.");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");

            EmployeeDto dto;
            switch (opt) {
                case 1: {
                    dto = input.readFullTimeEmployeeDto();
                    break;
                }
                case 2: {
                    dto = input.readContractEmployeeDto();
                    break;
                }
                case 3: {
                    dto = input.readPartTimeEmployeeDto();
                    break;
                }
                case 4: {
                    dto = input.readInternEmployeeDto();
                    break;
                }
                case 5:
                    return;
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
                    continue;
            }
            employeeService.createAndSaveEmployee(dto, company);
            System.out.println();
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
                    searchResult = company.searchByNameSurname(searchedNameSurname);

                    break;
                }
                case 2: {
                    Position searchedPosition = input.readPosition();
                    searchResult = company.searchByPosition(searchedPosition);
                    break;
                }
                case 3: {
                    CompareType compareType = input.readCompareType();
                    int searchedAge = input.readInt("Введіть значення віку для порівняння: ");
                    searchResult = company.searchByAge(searchedAge, compareType);
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

    private static void showEmpSortCriteriaMenu(Company company, ConsoleInput input) {
        while (true) {
            System.out.println("Виберіть критерій сортування співробітників:");
            System.out.println("1. Сортувати співробітнів за ім'ям.");
            System.out.println("2. Сортувати співробітників за віком");
            System.out.println("3. Сортувати співробітників за заробітньою платою");
            System.out.println("4. Відмінити сортування");
            System.out.println();

            int opt = input.readInt("Ваш вибір: ");

            switch (opt) {
                case 1: {
                    System.out.println(company.toFullStringEmpSorted(EmployeeComparators.BY_NAMESURNAME));
                    break;
                }
                case 2: {
                    System.out.println(company.toFullStringEmpSorted(EmployeeComparators.BY_AGE));
                    break;
                }
                case 3: {
                    System.out.println(company.toFullStringEmpSorted(EmployeeComparators.BY_SALARY));
                    break;
                }
                case 4:{
                    return;
                }
                default:
                    System.out.println("Такої опції немає, спробуйте ще раз.");
            }

            System.out.println();
        }
    }

    private static Company chooseCompany(List<Company> companies, ConsoleInput input) {
        if (companies.isEmpty()) {
            System.out.println("Список компаній порожній.");
            return null;
        } else {
            for (Company company : companies) {
                System.out.println(company);
            }
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
