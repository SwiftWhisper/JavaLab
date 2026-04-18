package sumdu.edu.ua;

import sumdu.edu.ua.validators.*;

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
     * Створює штатного співробітника (FullTimeEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * кількість років роботи в компанії.
     *
     * @return створений штатний співробітник
     */
    public FullTimeEmployee createFullTimeEmployee() {
        FullTimeEmployeeValidator validator = new FullTimeEmployeeValidator();

        EmployeeBaseData baseData = input.readBaseEmployeeData(validator);
        int years = input.readValidYearsInCompany("Введіть кількість років праці співробітника в компанії: ", validator);

        return new FullTimeEmployee(baseData.getNameSurname(), baseData.getAge(), baseData.getSalary(), baseData.getPosition(), years);
    }

    /**
     * Створює контрактного співробітника (ContractEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * тривалість контракту.
     *
     * @return створений контрактний співробітник
     */
    public ContractEmployee createContractEmployee() {
        ContractEmployeeValidator validator = new ContractEmployeeValidator();

        EmployeeBaseData baseData = input.readBaseEmployeeData(validator);
        int durationOfContract = input.readValidDurationOfContract("Введіть довжину контракту для співробітника: ", validator);

        return new ContractEmployee(baseData.getNameSurname(), baseData.getAge(), baseData.getSalary(), baseData.getPosition(), durationOfContract);
    }

    /**
     * Створює працівника з неповною зайнятістю (PartTimeEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * кількість робочих годин на день.
     *
     * @return створений працівник з неповною зайнятістю
     */
    public PartTimeEmployee createPartTimeEmployee() {
        PartTimeEmployeeValidator validator = new PartTimeEmployeeValidator();

        EmployeeBaseData baseData = input.readBaseEmployeeData(validator);
        int hoursInDay = input.readValidHoursInDay("Введіть скільки в день буде працювати цей співробітник (мінімум 4): ", validator);

        return new PartTimeEmployee(baseData.getNameSurname(), baseData.getAge(), baseData.getSalary(), baseData.getPosition(), hoursInDay);
    }
    /**
     * Створює стажера (InternEmployee).
     *
     * Зчитує основні дані співробітника та додатково
     * назву університету і тривалість стажування.
     *
     * @return створений стажер
     */
    public InternEmployee createInternEmployee() {
        InternEmployeeValidator validator = new InternEmployeeValidator();

        EmployeeBaseData baseData = input.readBaseEmployeeData(validator);
        String university = input.readValidUniversity("Введіть назву університета стажера: ", validator);
        int internshipMonths = input.readValidInternshipMonths("Введіть кількість місяців стажування: ", validator);

        return new InternEmployee(baseData.getNameSurname(), baseData.getAge(), baseData.getSalary(), baseData.getPosition(), university, internshipMonths);
    }
}
