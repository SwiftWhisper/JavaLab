package sumdu.edu.ua.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

import sumdu.edu.ua.validators.EmployeeValidator;
import sumdu.edu.ua.validators.InternEmployeeValidator;

public class InternEmployee extends Employee {
    private static final InternEmployeeValidator VALIDATOR = new InternEmployeeValidator();

    private String university;
    private int internshipMonths;


    public InternEmployee() {
        super();
    }

    public InternEmployee(String nameSurname, int age, double salary, Position position,
                          String university, int internshipMonths) {
        super(nameSurname, age, salary, position);
        setUniversity(university);
        setInternshipMonths(internshipMonths);
    }

    public InternEmployee(Employee other, String university, int internshipMonths) {
        super(other);
        setUniversity(university);
        setInternshipMonths(internshipMonths);
    }

    public InternEmployee(InternEmployee other) {
        super(other);
        this.university = other.university;
        this.internshipMonths = other.internshipMonths;
    }

    public String getUniversity() {
        return university;
    }

    public int getInternshipMonths() {
        return internshipMonths;
    }

    public void setUniversity(String university) {
        VALIDATOR.validateUniversity(university);
        this.university = university;
    }

    public void setInternshipMonths(int internshipMonths) {
        VALIDATOR.validateInternshipMonths(internshipMonths);
        this.internshipMonths = internshipMonths;
    }

    @Override
    public Map<String, Object> getDbFields() {
        Map<String, Object> map = new HashMap<>();
        map.put("University", this.getUniversity());
        map.put("Internship_months", this.getInternshipMonths());
        return map;
    }

    @Override 
    public void toJson(ObjectNode node) {
        super.toJson(node);
        node.put("internshipMonths",internshipMonths);
        node.put("university", university);
    }

    @Override
    public EmployeeType getType() {
        return EmployeeType.INTERN;
    }

    @Override
    protected EmployeeValidator getValidator() {
        return VALIDATOR;
    }

    @Override
    public String toString() {
        return "InternEmployee{" +
                baseToString() +
                ", university='" + university + '\'' +
                ", internshipMonths=" + internshipMonths +
                '}';
    }
}
