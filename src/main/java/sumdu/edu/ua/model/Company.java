package sumdu.edu.ua.model;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Company {
    private int localId = 0;
    private Long databaseId;
    private static int nextId = 1;
    private String name;
    private List<Employee> employees;

    public Company() {
        this.localId = nextId++;
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

    public int getLocalId() {
        return localId;
    }

    public Long getDatabaseId() {
        return databaseId;
    }

    public void assignDatabaseId(Long databaseId) {
        if (databaseId == null) {
            throw new IllegalArgumentException("Database ID не може бути null.");
        }

        if (this.databaseId != null) {
            throw new IllegalStateException("Database ID вже встановлений.");
        }

        this.databaseId = databaseId;
    }
    
    public Long getDisplayId() {
        return (databaseId != null) ? databaseId : localId;
    }

    public List<Employee> searchByNameSurname(String nameSurname) {
        List<Employee> result = new ArrayList<>();
        String searchedNameSurname = nameSurname.toLowerCase();

        for(Employee e : employees) {
            if (e.getNameSurname().toLowerCase().contains(searchedNameSurname)) {
                result.add(e);
            }
        }
        return result;
    }
    
    public List<Employee> searchByAge(int searchedAge, CompareType type) {
        List<Employee> result = new ArrayList<>();

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

    public List<Employee> searchByPosition(Position searchedPosition) {
        List<Employee> result = new ArrayList<>();

        for(Employee e : employees) {
            if (e.getPosition() == searchedPosition) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Company {\n");
        sb.append("  id = ").append(getDisplayId()).append("\n");
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



