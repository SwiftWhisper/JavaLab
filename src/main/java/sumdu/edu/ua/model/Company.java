package sumdu.edu.ua.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Company implements Identifiable {
    private UUID uuid = UUID.randomUUID();
    private String name;
    private List<Employee> employees;

    public Company() {
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

    @Override
    public UUID getUuid() {
        return uuid;
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

    public String toFullStringEmpSorted(Comparator<Employee> comparator) {
        StringBuilder sb = new StringBuilder();

        sb.append("Company {\n");
        sb.append("  id = ").append(uuid).append("\n");
        sb.append("  name = '").append(name).append("'\n");
        sb.append("  employees = [\n");

        if (employees.isEmpty()) {
            sb.append("    (немає співробітників)\n");
        } else {
            List<Employee> sorted = new ArrayList<>(employees);
            sorted.sort(comparator);
            for (Employee e : sorted) {
                sb.append("    ").append(e).append("\n");
            }
        }

        sb.append("  ]\n");
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toString() {
        return "ID: " + uuid + " | Назва: " + name;
    }
}



