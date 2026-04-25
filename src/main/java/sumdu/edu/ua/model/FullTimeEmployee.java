package sumdu.edu.ua.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

import sumdu.edu.ua.validators.EmployeeValidator;
import sumdu.edu.ua.validators.FullTimeEmployeeValidator;

public class FullTimeEmployee extends Employee{
    private static final FullTimeEmployeeValidator VALIDATOR = new FullTimeEmployeeValidator();

    private int yearsInCompany;

    public FullTimeEmployee() {
        super();
    }
        
    public FullTimeEmployee(String nameSurname, int age, double salary, Position position, int yearsInCompany) {
        super(nameSurname, age, salary, position);
        setYearsInCompany(yearsInCompany);
    }

    public FullTimeEmployee(Employee other, int setYearsInCompany) {
        super(other);
        setYearsInCompany(yearsInCompany);
    }

    public FullTimeEmployee(FullTimeEmployee other) {
        super(other);
        this.yearsInCompany = other.yearsInCompany;
    }

    public int getYearsInCompany(){
        return yearsInCompany;
    }

    public void setYearsInCompany(int yearsInCompany){
        VALIDATOR.validateYearsInCompany(yearsInCompany);
        this.yearsInCompany = yearsInCompany;
    }

    @Override 
    public void toJson(ObjectNode node) {
        super.toJson(node);
        node.put("yearsInCompany", yearsInCompany);
    }
    
    @Override
    public EmployeeType getType() {
        return EmployeeType.FULL_TIME;
    }

    @Override
    protected EmployeeValidator getValidator() {
        return VALIDATOR;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
            baseToString() +
            ", yearsInCompany=" + yearsInCompany +
            '}';
    }

}
