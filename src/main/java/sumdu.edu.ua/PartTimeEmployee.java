package sumdu.edu.ua;

import sumdu.edu.ua.validators.EmployeeValidator;
import sumdu.edu.ua.validators.PartTimeEmployeeValidator;

public class PartTimeEmployee extends Employee {
    private static final PartTimeEmployeeValidator VALIDATOR = new PartTimeEmployeeValidator();

    private int hoursInDay;

    public PartTimeEmployee() {
        super();
    }

    public PartTimeEmployee(String nameSurname, int age, double salary, Position position, int hoursInDay) {
        super(nameSurname, age, salary, position);
        setHoursInDay(hoursInDay);
    }

    public PartTimeEmployee(Employee other, int hoursInDay) {
        super(other);
        setHoursInDay(hoursInDay);
    }

    public PartTimeEmployee(PartTimeEmployee other) {
        super(other);
        this.hoursInDay = other.hoursInDay;
    }

    public int getHoursInDay() {
        return hoursInDay;
    }

    public void setHoursInDay(int hoursInDay) {
        VALIDATOR.validateHoursInDay(hoursInDay);
        this.hoursInDay = hoursInDay;
    }

    @Override
    protected EmployeeValidator getValidator() {
        return VALIDATOR;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "id=" + id +
                ", nameSurname='" + nameSurname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", position=" + position +
                ", hoursInDay=" + hoursInDay +
                '}';
    }
}
