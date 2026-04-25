package sumdu.edu.ua.model;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Company {
    private int id = 0;
    private static int nextId = 1;
    private String name;
    private List<Employee> employees;

    public Company() {
        this.id = nextId++;
        employees = new ArrayList<Employee>();
    }

    public Company(String name) {
        this();
        this.name = name;
    }   

    public Company(String name, Employee... employees) {
        this();
        this.name = name;
        this.employees = new ArrayList<>(Arrays.asList(employees)); 
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Company {\n");
        sb.append("  id = ").append(id).append("\n");
        sb.append("  name = '").append(name).append("'\n");
        sb.append("  employees = [\n");

        if (employees.isEmpty()) {
            sb.append("    (немає співробітників)\n");
        } else {
            for (Employee e : employees) {
                sb.append("    ").append(e).append("\n");
            }
        }

        sb.append("  ]\n");
        sb.append("}");

        return sb.toString();
    }
}



