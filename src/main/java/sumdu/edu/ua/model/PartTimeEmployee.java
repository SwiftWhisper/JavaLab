package sumdu.edu.ua.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

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
    public Map<String, Object> getDbFields() {
        Map<String, Object> map = new HashMap<>();
        map.put("Hours_in_day", this.getHoursInDay());
        return map;
    }

    @Override 
    public void toJson(ObjectNode node) {
        super.toJson(node);
        node.put("hoursInDay", hoursInDay);
    }

    @Override
    public EmployeeType getType() {
        return EmployeeType.PART_TIME;
    }

    @Override
    protected EmployeeValidator getValidator() {
        return VALIDATOR;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                baseToString() +
                ", hoursInDay=" + hoursInDay +
                '}';
    }
}
