package sumdu.edu.ua;

/**
 * Клас-фабрика для створення об'єктів співробітників.
 *
 * Використовує ConsoleInput для зчитування та валідації даних,
 * введених користувачем, після чого створює відповідні екземпляри
 * класів Employee та його похідних.
 */
public class EmployeeFactory {
    private final ConsoleInput input;

    public EmployeeFactory(ConsoleInput input) {
        this.input = input;
    }
    /**
     * Створює базового співробітника.
     *
     * Зчитує основні дані (ПІБ, вік, зарплата, посада)
     * та повертає об'єкт класу Employee.
     *
     * @return створений базовий співробітник
     */
    public Employee createEmployee() {
        String nameSurname = input.readValidNameSurname("Введіть ім'я та прізвище співробітника: ");
        int age = input.readValidAge("Введіть вік співробітника: ");
        double salary = input.readValidSalary("Введіть заробітню плату для співробітника: ");
        Position position = input.readPosition();

        return new Employee(nameSurname, age,salary, position); 
    }

    /**
     * Створює штатного співробітника (FullTimeEmployee).
     *
     * Спочатку створює базового співробітника, після чого
     * додає специфічне поле - кількість років роботи в компанії.
     *
     * @return створений штатний співробітник
     */
    public FullTimeEmployee createFullTimeEmployee() {
        Employee baseEmp = createEmployee();

        int years = input.readValidYearsInCompany("Введіть кількість років праці співробітника в компанії: ");

        return new FullTimeEmployee(baseEmp, years);
    }

    /**
     * Створює контрактного співробітника (ContractEmployee).
     *
     * Спочатку створює базового співробітника, після чого
     * додає специфічне поле - тривалість контракту.
     *
     * @return створений контрактний співробітник
     */
    public ContractEmployee createContractEmployee() {
        Employee baseEmp = createEmployee();

        int durationOfContract = input.readValidDurationOfContract("Введіть довжину контракту для співробітника: ");
        return new ContractEmployee(baseEmp, durationOfContract);
    }
}
